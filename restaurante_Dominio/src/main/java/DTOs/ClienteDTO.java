/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Comanda;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Clase DTO para representar un cliente.
 * Se utiliza para transferir los datos del cliente entre las distintas capas.
 * @author mmax2
 */
public class ClienteDTO {
    private Long id;
    
    private String nombre;
    
    private String apellidoP;
    
    private String apellidoM;
    
    private String correo;
    
    private String telefono;
    
    private LocalDate fechaRegistro;
    
    private List<Comanda> comandas;

    /**
     * Constructor vacío
     */
    public ClienteDTO() {
    }

    /**
     * Constructor con todos los atributos.
     * @param id El ID del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono Número de teléfono del cliente.
     * @param fechaRegistro Fecha de registro del cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteDTO(Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
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
     * Constructor sin ID (cuando se crea un nuevo cliente).
     * @param nombre nombre del cliente.
     * @param apellidoP apellido paterno del cliente.
     * @param apellidoM apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono número de teléfono del cliente.
     * @param fechaRegistro fecha de registro del cliente.
     * @param comandas  lista de comandas asociadas al cliente.
     */
    public ClienteDTO(String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
        this.comandas = comandas;
    }

    /**
     * Obtiene el id del cliente
     * @return ID del cliente
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el id del cliente
     * @param id   id del ciente
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del cliente
     * @return nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido paterno del cliente.
     * @return El apellido paterno del cliente.
     */
    public String getApellidoP() {
        return apellidoP;
    }

    /**
     * Establece el apellido paterno del cliente.
     * @param apellidoP El apellido paterno del cliente.
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /**
     * Obtiene el apellido materno del cliente.
     * @return El apellido materno del cliente.
     */
    public String getApellidoM() {
        return apellidoM;
    }

    /**
     * Establece el apellido materno del cliente.
     * @param apellidoM El apellido materno del cliente.
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /**
     * Obtiene el correo electrónico del cliente.
     * @return El correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo electrónic del cliente.
     * @param correo Correo del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene el número de teléfono del cliente.
     * @return El número de teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del cliente.
     * @param telefono El número de teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la fecha de registro del cliente.
     * @return La fecha de registro del cliente.
     */
    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     */
    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    /**
     * Obtiene el nombre completo del cliente concatenando su nombre y apellidos.
     * @return El nombre completo del cliente.
     */
    public String getNombreCompleto() {
        return (nombre + " " + apellidoP + (apellidoM != null ? " " + apellidoM : "")).trim();
    }

    /**
     * Obtiene la lista de comandas asociadas al cliente.
     * @return La lista de comandas del cliente.
     */
    public List<Comanda> getComandas() {
        return comandas;
    }

    /**
     * Establece la lista de comandas asociadas al cliente.
     * @param comandas La lista de comandas del cliente.
     */
    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    /**
     * Devuelve una representación en cadena de texto del objeto ClienteDTO.
     * @return Una cadena que describe al cliente.
     */
    @Override
    public String toString() {
        return "ClienteDTO{" + "id=" + id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", correo=" + correo + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + ", comandas=" + comandas + '}';
    }

    /**
     * Genera un código hash único para este objeto ClienteDTO.
     * @return Un valor hash que representa a este ClienteDTO.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + Objects.hashCode(this.apellidoP);
        hash = 71 * hash + Objects.hashCode(this.apellidoM);
        hash = 71 * hash + Objects.hashCode(this.correo);
        hash = 71 * hash + Objects.hashCode(this.telefono);
        hash = 71 * hash + Objects.hashCode(this.fechaRegistro);
        hash = 71 * hash + Objects.hashCode(this.comandas);
        return hash;
    }

    /**
     * Compara si este objeto es igual a otro objeto ClienteDTO.
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false de lo contrario.
     */
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
        final ClienteDTO other = (ClienteDTO) obj;
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
