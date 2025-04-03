/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author mmax2
 */
@Entity
public class ClienteFrecuente extends Cliente implements Serializable {

    @Column(name = "puntos")
    private int puntos;

    @Column(name = "gastoTotalAcumulado")
    private Double gastoTotalAcumulado;

    @Column(name = "conteoVisitas")
    private int conteoVisitas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(int puntos, Double gastoTotalAcumulado, int conteoVisitas) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    public ClienteFrecuente(int puntos, Double gastoTotalAcumulado, int conteoVisitas, Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro) {
        super(id, nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }
    

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    public void setGastoTotalAcumulado(Double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public int getConteoVisitas() {
        return conteoVisitas;
    }

    public void setConteoVisitas(int conteoVisitas) {
        this.conteoVisitas = conteoVisitas;
    }

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "puntos=" + puntos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.puntos);
        hash = 89 * hash + Objects.hashCode(this.gastoTotalAcumulado);
        hash = 89 * hash + Objects.hashCode(this.conteoVisitas);
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
        final ClienteFrecuente other = (ClienteFrecuente) obj;
        if (!Objects.equals(this.puntos, other.puntos)) {
            return false;
        }
        if (!Objects.equals(this.gastoTotalAcumulado, other.gastoTotalAcumulado)) {
            return false;
        }
        return Objects.equals(this.conteoVisitas, other.conteoVisitas);
    }

}
