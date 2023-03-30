package py.com.prueba.laboratorio.modelo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    @Id
    @Column(name = "id_persona")
    @Basic(optional = false)
    @GeneratedValue(generator = "personaSec")
    @SequenceGenerator(name = "personaSec",sequenceName = "persona_sec",allocationSize = 0)
    private Integer idPersona;
    @Column(name = "nombre",length = 50)
    @Basic(optional = false)
    private String nombre;
    @Column(name = "apellido",length = 50)
    @Basic(optional = false)
    private String apellido;

    @OneToMany(mappedBy = "persona")
    private List<Agenda> listaAgendas;

    public Persona(){

    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Agenda> getListaAgendas() {
        return listaAgendas;
    }

    public void setListaAgendas(List<Agenda> listaAgendas) {
        this.listaAgendas = listaAgendas;
    }

    @Override
    public String toString() {
        return nombre+ " "+apellido;
    }
}
