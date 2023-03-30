package py.com.prueba.laboratorio.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cabecera")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cabecera.findAll", query = "SELECT c FROM Cabecera c"),
    @NamedQuery(name = "Cabecera.findByIdCabecera", query = "SELECT c FROM Cabecera c WHERE c.idCabecera = :idCabecera"),
    @NamedQuery(name = "Cabecera.findByPuntosUtilizado", query = "SELECT c FROM Cabecera c WHERE c.puntosUtilizado = :puntosUtilizado"),
    @NamedQuery(name = "Cabecera.findByFecha", query = "SELECT c FROM Cabecera c WHERE c.fecha = :fecha")}
)
public class Cabecera implements Serializable {
    @OneToMany(mappedBy = "idCabecera")
    private Collection<Detalle> detalleCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cabecera")
    private Integer idCabecera;
    @Column(name = "puntos_utilizado")
    private Integer puntosUtilizado;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-4")
    private Date fecha;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    @ManyToOne
    private Cliente idCliente;
    @JoinColumn(name = "concepto_de_uso", referencedColumnName = "id_concepto")
    @ManyToOne
    private Concepto conceptoDeUso;

    public Cabecera() {
    }

    public Cabecera(Integer idCabecera) {
        this.idCabecera = idCabecera;
    }

    public Integer getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(Integer idCabecera) {
        this.idCabecera = idCabecera;
    }

    public Integer getPuntosUtilizado() {
        return puntosUtilizado;
    }

    public void setPuntosUtilizado(Integer puntosUtilizado) {
        this.puntosUtilizado = puntosUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public Concepto getConceptoDeUso() {
        return conceptoDeUso;
    }

    public void setConceptoDeUso(Concepto conceptoDeUso) {
        this.conceptoDeUso = conceptoDeUso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCabecera != null ? idCabecera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabecera)) {
            return false;
        }
        Cabecera other = (Cabecera) object;
        if ((this.idCabecera == null && other.idCabecera != null) || (this.idCabecera != null && !this.idCabecera.equals(other.idCabecera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.fpuna.backend.domain.Cabecera[ idCabecera=" + idCabecera + " ]";
    }

    @XmlTransient
    public Collection<Detalle> getDetalleCollection() {
        return detalleCollection;
    }

    public void setDetalleCollection(Collection<Detalle> detalleCollection) {
        this.detalleCollection = detalleCollection;
    }
    
}
