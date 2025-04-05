/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import ENUM.EstadoComanda;
import Entidades.DetallesComanda;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class ComandaDTO {

    public EstadoComanda estado;

    public Date fechaHora;

    public String folio;

    public Double totalventa;

    public DetallesComanda detallesComanda;

    public ComandaDTO() {
    }

    public ComandaDTO(EstadoComanda estado, Date fechaHora, String folio, Double totalventa, DetallesComanda detallesComanda) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalventa = totalventa;
        this.detallesComanda = detallesComanda;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Double getTotalventa() {
        return totalventa;
    }

    public void setTotalventa(Double totalventa) {
        this.totalventa = totalventa;
    }

    public DetallesComanda getDetallesComanda() {
        return detallesComanda;
    }

    public void setDetallesComanda(DetallesComanda detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.estado);
        hash = 23 * hash + Objects.hashCode(this.fechaHora);
        hash = 23 * hash + Objects.hashCode(this.folio);
        hash = 23 * hash + Objects.hashCode(this.totalventa);
        hash = 23 * hash + Objects.hashCode(this.detallesComanda);
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
        final ComandaDTO other = (ComandaDTO) obj;
        if (!Objects.equals(this.folio, other.folio)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.fechaHora, other.fechaHora)) {
            return false;
        }
        if (!Objects.equals(this.totalventa, other.totalventa)) {
            return false;
        }
        return Objects.equals(this.detallesComanda, other.detallesComanda);
    }

    @Override
    public String toString() {
        return "ComandaDTO{" + "estado=" + estado + ", fechaHora=" + fechaHora + ", folio=" + folio + ", totalventa=" + totalventa + ", detallesComanda=" + detallesComanda + '}';
    }

}
