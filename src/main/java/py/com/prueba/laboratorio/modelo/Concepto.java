package py.com.prueba.laboratorio.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "concepto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Concepto.findAll", query = "SELECT c FROM Concepto c"),
    @NamedQuery(name = "Concepto.findByIdConcepto", query = "SELECT c FROM Concepto c WHERE c.idConcepto = :idConcepto"),
    @NamedQuery(name = "Concepto.findByDescripcion", query = "SELECT c FROM Concepto c WHERE c.descripcion = :descripcion"),
    @NamedQuery(name = "Concepto.findByPuntosRequeridos", query = "SELECT c FROM Concepto c WHERE c.puntosRequeridos = :puntosRequeridos")})
public class Concepto implements Serializable {

    @Column(name = "descripcion", length = 100)
    private String descripcion;
    @OneToMany(mappedBy = "conceptoDeUso")
    private Collection<Cabecera> cabeceraCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_concepto")
    private Integer idConcepto;
    @Column(name = "puntos_requeridos")
    private Integer puntosRequeridos;

    public Concepto() {
    }

    public Concepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }


    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConcepto != null ? idConcepto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Concepto)) {
            return false;
        }
        Concepto other = (Concepto) object;
        if ((this.idConcepto == null && other.idConcepto != null) || (this.idConcepto != null && !this.idConcepto.equals(other.idConcepto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.fpuna.backend.domain.Concepto[ idConcepto=" + idConcepto + " ]";
    }


    @XmlTransient
    public Collection<Cabecera> getCabeceraCollection() {
        return cabeceraCollection;
    }

    public void setCabeceraCollection(Collection<Cabecera> cabeceraCollection) {
        this.cabeceraCollection = cabeceraCollection;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
