package py.com.prueba.laboratorio.rest;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import py.com.prueba.laboratorio.ejb.BolsaDao;
import py.com.prueba.laboratorio.ejb.CabeceraDao;
import py.com.prueba.laboratorio.ejb.ConceptoDao;
import py.com.prueba.laboratorio.ejb.DetalleDao;
import py.com.prueba.laboratorio.modelo.Bolsa;
import py.com.prueba.laboratorio.modelo.Cabecera;
import py.com.prueba.laboratorio.modelo.Cliente;
import py.com.prueba.laboratorio.modelo.Concepto;
import py.com.prueba.laboratorio.modelo.Detalle;

@Stateless
public class UsoPuntosServiceImpl implements UsoPuntosService, UsoPuntosServiceRemote {

    @Inject
    private BolsaDao bolsaDao;
    @Inject
    private ConceptoDao conceptoDao;
    @Inject
    private DetalleDao detalleDao;
    @Inject
    private CabeceraDao cabeceraDao;

    @Resource
    private SessionContext contexto;

    @Override
    public void guardarDetalle(Detalle detalle) {
        detalleDao.insertDetalle(detalle);
    }

    @Override
    public Cabecera guardarCabecera(Cabecera cabecera) {
        return cabeceraDao.insertCabecera(cabecera);
    }

    @Override
    public List<Bolsa> buscarBolsaCliente(Integer idCliente) {
        List<Bolsa> bolsa = bolsaDao.findBolsaByCliente(new Cliente(idCliente));

        bolsa.sort((b1, b2) -> b1.getFechaCaducidad().compareTo(b2.getFechaCaducidad()));

        return bolsa;
    }

    @Override
    public Concepto buscarConcepto(Integer idConcepto) {
        return conceptoDao.findConceptoById(new Concepto(idConcepto));
    }

    @Override
    /* aca debo ver como verificar la bolsa con el concepto*/
    public Bolsa verificarPuntoRequerido(Concepto concepto, List<Bolsa> bolsa) {

        try {

            for (Bolsa b : bolsa) {
                if (b.getSaldoPuntos() >= concepto.getPuntosRequeridos()) {
                    return b;
                }
            }

        } catch (NoResultException e) {
            return null;
        }
        return null;
    }

    @Override
    public void actualizarBolsa(Bolsa bolsa) {
        bolsaDao.updateBolsa(bolsa);
    }

    @Override
    public List<Detalle> listarDetalle() {
        return detalleDao.findAllDetalle();
    }

    @Override
    public List<Detalle> listarDetallePorConcepto(Concepto concepto) {
        return detalleDao.findAllByIdConcepto(concepto);
    }

    @Override
    public List<Detalle> listarDetallePorCliente(Cliente cliente) {
        return detalleDao.findAllByIdCliente(cliente);
    }

    @Override
    public List<Detalle> listarDetallePorFecha(Date fecha) {
        return detalleDao.findAllByIdFecha(fecha);
    }

}
