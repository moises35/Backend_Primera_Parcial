package py.com.prueba.laboratorio.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "bolsa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bolsa.findAll", query = "SELECT b FROM Bolsa b"),
    @NamedQuery(name = "Bolsa.findByIdBolsa", query = "SELECT b FROM Bolsa b WHERE b.idBolsa = :idBolsa"),
    @NamedQuery(name = "Bolsa.findByFechaAsignacion", query = "SELECT b FROM Bolsa b WHERE b.fechaAsignacion = :fechaAsignacion"),
    @NamedQuery(name = "Bolsa.findByFechaCaducidad", query = "SELECT b FROM Bolsa b WHERE b.fechaCaducidad = :fechaCaducidad"),
    @NamedQuery(name = "Bolsa.findByPuntajeAsignado", query = "SELECT b FROM Bolsa b WHERE b.puntajeAsignado = :puntajeAsignado"),
    @NamedQuery(name = "Bolsa.findByPuntajeUtilizado", query = "SELECT b FROM Bolsa b WHERE b.puntajeUtilizado = :puntajeUtilizado"),
    @NamedQuery(name = "Bolsa.findBySaldoPuntos", query = "SELECT b FROM Bolsa b WHERE b.saldoPuntos = :saldoPuntos"),
    @NamedQuery(name = "Bolsa.findByMontoOperacion", query = "SELECT b FROM Bolsa b WHERE b.montoOperacion = :montoOperacion"),
    @NamedQuery(name = "Bolsa.findByEstado", query = "SELECT b FROM Bolsa b WHERE b.estado = :estado"),
    @NamedQuery(name = "Bolsa.findAllSortAsc", query = "SELECT b FROM Bolsa b ORDER BY b.fechaCaducidad asc"),
})
public class Bolsa implements Serializable {
    @OneToMany(mappedBy = "idBolsa")
    private Collection<Detalle> detalleCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_bolsa")
    private Integer idBolsa;
    @Column(name = "fecha_asignacion")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAsignacion;
    @Column(name = "fecha_caducidad")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaCaducidad;
    @Column(name = "puntaje_asignado")
    private Integer puntajeAsignado;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @Column(name = "saldo_puntos")
    private Integer saldoPuntos;
    @Column(name = "monto_operacion")
    private Integer montoOperacion;
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente idCliente;

    public Bolsa() {
    }

    public Bolsa(Integer idBolsa) {
        this.idBolsa = idBolsa;
    }

    public Integer getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(Integer idBolsa) {
        this.idBolsa = idBolsa;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Integer getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBolsa != null ? idBolsa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bolsa)) {
            return false;
        }
        Bolsa other = (Bolsa) object;
        if ((this.idBolsa == null && other.idBolsa != null) || (this.idBolsa != null && !this.idBolsa.equals(other.idBolsa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.fpuna.backend.domain.Bolsa[ idBolsa=" + idBolsa + " ]";
    }

    @XmlTransient
    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }
    
}
