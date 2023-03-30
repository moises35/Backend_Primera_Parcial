package py.com.prueba.laboratorio.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "vencimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vencimiento.findAll", query = "SELECT v FROM Vencimiento v"),
    @NamedQuery(name = "Vencimiento.findByIdVencimiento", query = "SELECT v FROM Vencimiento v WHERE v.idVencimiento = :idVencimiento"),
    @NamedQuery(name = "Vencimiento.findByFechaInicio", query = "SELECT v FROM Vencimiento v WHERE v.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Vencimiento.findByFechaFin", query = "SELECT v FROM Vencimiento v WHERE v.fechaFin = :fechaFin"),
    @NamedQuery(name = "Vencimiento.findByDuracion", query = "SELECT v FROM Vencimiento v WHERE v.duracion = :duracion")})
public class Vencimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vencimiento")
    private Integer idVencimiento;
    
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-4")
    private Date fechaInicio;
    
     @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT-4")
    private Date fechaFin;
    @Column(name = "duracion")
    private Integer duracion;

    public Vencimiento() {
    }

    public Vencimiento(Integer idVencimiento) {
        this.idVencimiento = idVencimiento;
    }

    public Integer getIdVencimiento() {
        return idVencimiento;
    }

    public void setIdVencimiento(Integer idVencimiento) {
        this.idVencimiento = idVencimiento;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVencimiento != null ? idVencimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vencimiento)) {
            return false;
        }
        Vencimiento other = (Vencimiento) object;
        if ((this.idVencimiento == null && other.idVencimiento != null) || (this.idVencimiento != null && !this.idVencimiento.equals(other.idVencimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.fpuna.backend.domain.Vencimiento[ idVencimiento=" + idVencimiento + " ]";
    }

}
