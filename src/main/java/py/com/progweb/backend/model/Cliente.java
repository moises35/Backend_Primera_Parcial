
package py.com.progweb.backend.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente {
    // Atributos
    @Id
    @Basic(optional = false)
    @Column(name = "id_cliente")
    @GeneratedValue(generator="clienteSec")
    @SequenceGenerator(name="clienteSec",sequenceName="cliente_seq",allocationSize=0)
    private Integer idCliente;
    
    @Basic(optional = false)
    @Column(name = "nombre",length = 50)
    private String nombre;
    
    @Basic(optional = false)
    @Column(name = "apellido",length = 50)
    private String apellido;
    
    @Basic(optional = false)
    @Column(name = "num_documento",length = 15)
    private String numDocumento;
    
    @Basic(optional = false)
    @Column(name = "tipo_documento",length = 50)
    private String tipoDocumento;
    
    @Basic(optional = false)
    @Column(name = "nacionalidad",length = 50)
    private String nacionalidad;
    
    @Basic(optional = false)
    @Column(name = "email",length = 50)
    private String email;
    
    @Basic(optional = false)
    @Column(name = "telefono",length = 50)
    private String telefono;
    
    @Basic(optional = false)
    @Column(name = "fecha_nacimiento",length = 50)
    private String fecha_nacimiento;

    // Constructor
    public Cliente() {
    }
    
    // Getters and Setters
    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
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

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
}
