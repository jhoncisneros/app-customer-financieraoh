package pe.com.financieraoh.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Size(min = 3, message = "Nombre debe tener minimo 3 caracteres")
    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Size(min = 3, message = "Apellido debe tener minimo 3 caracteres")
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Size(min = 3, max = 150, message = "Dirección debe tener minimo 3 caracteres")
    @Column(name = "direccion", nullable = true, length = 150)
    private String direccion;

    @Email(message = "Email tiene formato incorrecto")
    @Column(name = "email", nullable = true, length = 100)
    private String email;

    @Size(min = 9, max = 9, message = "Celular debe tener 9 caracteres")
    @Digits(fraction = 0, integer = 9, message ="Celular no es un numero")
    @Column(name = "celular", nullable = true, length = 9)
    private String celular;

    @Column(name = "estado", nullable = true)
    private Integer estado;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }
}
