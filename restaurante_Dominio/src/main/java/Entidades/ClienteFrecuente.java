/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
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

    @Column(name = "puntos", nullable = true)
    private Long puntos;

    @Column(name = "gastosTotalesAcumulados", nullable = false)
    private Double gastoTotalAcumulado;

    @Column(name = "visitasTotales", nullable = false)
    private Long visitasTotales;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Long puntos, Double gastoTotalAcumulado, Long visitasTotales) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.visitasTotales = visitasTotales;
    }

    public ClienteFrecuente(Long puntos, Double gastoTotalAcumulado, Long visitasTotales, Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, Date fechaRegistro) {
        super(id, nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.visitasTotales = visitasTotales;
    }

    public Long getPuntos() {
        return puntos;
    }

    public void setPuntos(Long puntos) {
        this.puntos = puntos;
    }

    public Double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    public void setGastoTotalAcumulado(Double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public Long getVisitasTotales() {
        return visitasTotales;
    }

    public void setVisitasTotales(Long visitasTotales) {
        this.visitasTotales = visitasTotales;
    }

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "puntos=" + puntos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", visitasTotales=" + visitasTotales + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.puntos);
        hash = 89 * hash + Objects.hashCode(this.gastoTotalAcumulado);
        hash = 89 * hash + Objects.hashCode(this.visitasTotales);
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
        return Objects.equals(this.visitasTotales, other.visitasTotales);
    }

}
