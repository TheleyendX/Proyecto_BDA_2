/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class ClienteDTO {
    public String nombre;
    
    public String apellidoP;
    
    public String apellidoM;
    
    public String correo;
    
    public String telefono;
    
    public Date fechaRegistro;

    public ClienteDTO() {
    }

    public ClienteDTO(String nombre, String apellidoP, String apelldioM, String correo, String telefono, Date fechaRegistro) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apelldioM;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" + "nombre="
                + nombre + ", apellidoP=" 
                + apellidoP + ", apelldioM=" 
                + apellidoM + ", correo=" 
                + correo + ", telefono=" 
                + telefono 
                + ", fechaRegistro=" 
                + fechaRegistro + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nombre);
        hash = 23 * hash + Objects.hashCode(this.apellidoP);
        hash = 23 * hash + Objects.hashCode(this.apellidoM);
        hash = 23 * hash + Objects.hashCode(this.correo);
        hash = 23 * hash + Objects.hashCode(this.telefono);
        hash = 23 * hash + Objects.hashCode(this.fechaRegistro);
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
        return Objects.equals(this.fechaRegistro, other.fechaRegistro);
    }
    
    
}
