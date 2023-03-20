package py.com.progweb.backend.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "premios")
public class Premio {
    // Atributos
    @Id
    @Basic(optional = false)
    @Column(name = "id_premio")
    @GeneratedValue(generator="premioSec")
    @SequenceGenerator(name="premioSec",sequenceName="premio_seq",allocationSize=0)
    private Integer idPremio;
    
    @Basic(optional = false)
    @Column(name = "descripcion",length = 100)
    private String descripcion;
    
    @Basic(optional = false)
    @Column(name = "puntos_req")
    private Integer puntosRequeridos;
    
    // Constructor
    public Premio() {
    }
    
    // Getters and Setters
    public Integer getIdPremio() {
        return idPremio;
    }

    public void setIdPremio(Integer idPremio) {
        this.idPremio = idPremio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
    
}
