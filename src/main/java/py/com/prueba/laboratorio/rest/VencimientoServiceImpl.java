package py.com.prueba.laboratorio.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.prueba.laboratorio.ejb.BolsaDao;
import py.com.prueba.laboratorio.ejb.VencimientoDao;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Vencimiento;


@Stateless
public class VencimientoServiceImpl implements VencimientoService, VencimientoServiceRemote {

    @Inject
    private VencimientoDao vencimientoDao;

    @Inject
    private BolsaDao bolsaDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Vencimiento> listarVencimientos() {
        return vencimientoDao.findAllVencimientos();
    }

    @Override
    public Vencimiento encontrarVencimientoPorId(Vencimiento vencimiento) {
        return vencimientoDao.findVencimientoById(vencimiento);
    }

    @Override
    public void registrarVencimiento(Vencimiento vencimiento) {
        vencimientoDao.insertVencimiento(vencimiento);
    }

    @Override
    public void modificarVencimiento(Vencimiento vencimiento) {
        try {
            vencimientoDao.updateVencimiento(vencimiento);
        } catch (Throwable t) {
            contexto.setRollbackOnly();
            t.printStackTrace(System.out);

        }
    }

    @Override
    public void eliminarVencimiento(Vencimiento vencimiento) {
        vencimientoDao.deleteVencimiento(vencimiento);
    }

    //Actualiza el saldo de las bolsas afectadas por una nueva regla de vencimiento
    private void actualizarSaldoBolsaPorVencimiento(Vencimiento vencimiento) {
        List<Bolsa> bolsas = bolsaDao.findAllBolsasRangoFecha(vencimiento.getFechaInicio(), vencimiento.getFechaFin());
        Date fechaHoy = new Date();

        for (Bolsa bolsa : bolsas) {
            Date fechaAsignacion = bolsa.getFechaAsignacion();
            Integer diasDuracion = vencimientoDao.findVencimientoByDuracion(fechaAsignacion);

            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaAsignacion);
            cal.add(Calendar.DATE, diasDuracion);

            //Para que deje usar los puntos hasta el final del día inclusive
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);

            Date fechaCaducidadNueva = cal.getTime();
            bolsa.setFechaCaducidad(fechaCaducidadNueva);

            if (fechaCaducidadNueva.before(fechaHoy)) { //Si la fecha de caducidad  ya paso la fecha actual
                bolsa.setSaldoPuntos(0); //Puntos vencidos
            } else {
                bolsa.setSaldoPuntos(bolsa.getPuntajeAsignado() - bolsa.getPuntajeUtilizado());
            }

            bolsaDao.updateBolsa(bolsa);
        }
    }

}
