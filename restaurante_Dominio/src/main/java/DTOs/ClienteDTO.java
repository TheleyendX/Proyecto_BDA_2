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
 *
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

    public ClienteDTO() {
    }

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

    public ClienteDTO(String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
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
    
    public String getNombreCompleto() {
        return (nombre + " " + apellidoP + (apellidoM != null ? " " + apellidoM : "")).trim();
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "id=" + id + ", nombre=" + nombre + ", apellidoP=" + apellidoP + ", apellidoM=" + apellidoM + ", correo=" + correo + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + ", comandas=" + comandas + '}';
    }

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
