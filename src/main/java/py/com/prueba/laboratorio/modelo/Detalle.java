package py.com.prueba.laboratorio.modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detalle.findAll", query = "SELECT d FROM Detalle d"),
    @NamedQuery(name = "Detalle.findByIdDetalle", query = "SELECT d FROM Detalle d WHERE d.idDetalle = :idDetalle"),
    @NamedQuery(name = "Detalle.findByPuntajeUtilizado", query = "SELECT d FROM Detalle d WHERE d.puntajeUtilizado = :puntajeUtilizado")
})
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_detalle")
    private Integer idDetalle;
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;
    @JoinColumn(name = "id_bolsa", referencedColumnName = "id_bolsa")
    @ManyToOne
    private Bolsa idBolsa;
    @JoinColumn(name = "id_cabecera", referencedColumnName = "id_cabecera")
    @ManyToOne
    private Cabecera idCabecera;

    public Detalle() {
    }

    public Detalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Bolsa getIdBolsa() {
        return idBolsa;
    }

    public void setIdBolsa(Bolsa idBolsa) {
        this.idBolsa = idBolsa;
    }

    public Cabecera getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(Cabecera idCabecera) {
        this.idCabecera = idCabecera;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetalle != null ? idDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalle)) {
            return false;
        }
        Detalle other = (Detalle) object;
        if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && !this.idDetalle.equals(other.idDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.fpuna.backend.domain.Detalle[ idDetalle=" + idDetalle + " ]";
    }
    
}
