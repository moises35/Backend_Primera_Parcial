package py.com.prueba.laboratorio.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import py.com.prueba.laboratorio.ejb.BolsaDao;
import py.com.prueba.laboratorio.ejb.ReglaAsignacionDao;
import py.com.prueba.laboratorio.ejb.VencimientoDao;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cliente;


@Stateless
public class BolsaServiceImpl implements BolsaServiceRemote, BolsaService {

    @Inject
    private BolsaDao bolsaDao;
    @Inject
    private ReglaAsignacionDao reglaDao;
    @Inject
    private VencimientoDao vencimientoDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Bolsa> listarBolsas() {
        return bolsaDao.findAllBolsas();
    }

    public List<Bolsa> listarBolsasRangoFecha(Date fecha1, Date fecha2) {
        return bolsaDao.findAllBolsasRangoFecha(fecha1, fecha2);
    }

    @Override
    public Bolsa encontrarBolsaPorId(Bolsa bolsa) {
        return bolsaDao.findBolsaById(bolsa);
    }

    @Override
    public List<Bolsa> encontrarBolsaPorCliente(Cliente cliente) {
        return bolsaDao.findBolsaByCliente(cliente);
    }

    @Override
    public void registrarBolsa(Bolsa bolsa) {
        bolsaDao.insertBolsa(bolsa);
    }

    @Override
    public void modificarBolsa(Bolsa bolsa) {
        bolsaDao.updateBolsa(bolsa);
    }

    @Override
    public void modificarBolsaSaldoCducados() {
        bolsaDao.updateBolsaSaldoCaducados();
    }

    @Override
    public void eliminarBolsa(Bolsa bolsa) {
        bolsaDao.deleteBolsa(bolsa);
    }

    @Override
    public Integer puntajeAsignado(Integer monto) {

        return reglaDao.findReglaByMonto(monto);
    }

    @Override
    public Integer encontrarDiasDuracion(Date fechaActual) {

        Integer duracion = vencimientoDao.findVencimientoByDuracion(fechaActual);
        return duracion;

    }

    @Override
    public List<Cliente> encontrarClienteConBolsaPorVencer(Integer dias) {
        
        return bolsaDao.findClienteVnecmientoBolsa(dias);
    }

    @Override
    public List<Bolsa> listarBolsasRangoPunto(Integer punto1, Integer punto2) {
        return bolsaDao.findAllBolsasRangoPunto(punto1, punto2);
    }

}
