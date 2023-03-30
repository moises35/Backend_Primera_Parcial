package py.com.prueba.laboratorio.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "regla_asignacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReglaAsignacion.findAll", query = "SELECT r FROM ReglaAsignacion r"),
    @NamedQuery(name = "ReglaAsignacion.findByIdReglaAsignacion", query = "SELECT r FROM ReglaAsignacion r WHERE r.idReglaAsignacion = :idReglaAsignacion"),
    @NamedQuery(name = "ReglaAsignacion.findByLimiteInferior", query = "SELECT r FROM ReglaAsignacion r WHERE r.limiteInferior = :limiteInferior"),
    @NamedQuery(name = "ReglaAsignacion.findByLimiteSuperior", query = "SELECT r FROM ReglaAsignacion r WHERE r.limiteSuperior = :limiteSuperior"),
    @NamedQuery(name = "ReglaAsignacion.findByMontoEquivalencia", query = "SELECT r FROM ReglaAsignacion r WHERE r.montoEquivalencia = :montoEquivalencia")
})
public class ReglaAsignacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_regla_asignacion")
    private Integer idReglaAsignacion;
    @Column(name = "limite_inferior")
    private Integer limiteInferior;
    @Column(name = "limite_superior")
    private Integer limiteSuperior;
    @Column(name = "monto_equivalencia")
    private Integer montoEquivalencia;

    public ReglaAsignacion() {
    }

    public ReglaAsignacion(Integer idReglaAsignacion) {
        this.idReglaAsignacion = idReglaAsignacion;
    }

    public Integer getIdReglaAsignacion() {
        return idReglaAsignacion;
    }

    public void setIdReglaAsignacion(Integer idReglaAsignacion) {
        this.idReglaAsignacion = idReglaAsignacion;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReglaAsignacion != null ? idReglaAsignacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReglaAsignacion)) {
            return false;
        }
        ReglaAsignacion other = (ReglaAsignacion) object;
        if ((this.idReglaAsignacion == null && other.idReglaAsignacion != null) || (this.idReglaAsignacion != null && !this.idReglaAsignacion.equals(other.idReglaAsignacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.fpuna.backend.domain.ReglaAsignacion[ idReglaAsignacion=" + idReglaAsignacion + " ]";
    }
    
}
