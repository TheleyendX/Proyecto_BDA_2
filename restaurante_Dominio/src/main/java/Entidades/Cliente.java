/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que representa a un cliente.
 * Los atributos incluyen información personal de este, como el nombre, teléfono,
 * correo.
 * @author mmax2
 */
@Entity
@Table(name = "Clientes")
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cliente", discriminatorType = DiscriminatorType.STRING)
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 70, nullable = false)
    private String nombre;

    @Column(name = "apellidoPaterno", length = 70, nullable = false)
    private String apellidoP;

    @Column(name = "apellidoMaterno", length = 70, nullable = true)
    private String apellidoM;

    @Column(name = "correo", length = 70, nullable = true)
    private String correo;

    @Column(name = "telefono", nullable = false, unique = true)
    private String telefono;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDate fechaRegistro;
    
    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.PERSIST})
    private List<Comanda> comandas = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Cliente() {
    }

    /**
     * Constructor con todos los atributos de la clase.
     * @param id El identificador único del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha en la que se registró el cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public Cliente(Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.comandas = comandas;
    }

    /**
     * Constructor sin el id.
     * 
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public Cliente(String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.comandas = comandas;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    /**
     * Obtiene el nombre completo del cliente, compuesto por el nombre y los apellidos.
     * 
     * @return El nombre completo del cliente.
     */
    public String getNombreCompleto() {
        return (nombre + " " + apellidoP + (apellidoM != null ? " " + apellidoM : "")).trim();
    }


    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", correo=" + correo + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + ", comandas=" + comandas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.nombre);
        hash = 41 * hash + Objects.hashCode(this.apellidoP);
        hash = 41 * hash + Objects.hashCode(this.apellidoM);
        hash = 41 * hash + Objects.hashCode(this.correo);
        hash = 41 * hash + Objects.hashCode(this.telefono);
        hash = 41 * hash + Objects.hashCode(this.fechaRegistro);
        hash = 41 * hash + Objects.hashCode(this.comandas);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.apellidoP, other.apellidoP)) {
            return false;
        }
        if (!Objects.equals(this.apellidoM, other.apellidoM)) {
            return false;
        }
        if (!Objects.equals(this.correo, other.correo)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fechaRegistro, other.fechaRegistro)) {
            return false;
        }
        return Objects.equals(this.comandas, other.comandas);
    }

}
