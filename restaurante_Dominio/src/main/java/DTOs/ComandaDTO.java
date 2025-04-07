/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import ENUM.EstadoComanda;
import Entidades.DetallesComanda;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class ComandaDTO {
    private Long id;

    private EstadoComanda estado;

    private LocalDateTime fechaHora;

    private String folio;

    private Double totalVenta;

    private DetallesComandaDTO detallesComanda;
    
    private ClienteDTO cliente;
    
    private MesaDTO mesa;

    public ComandaDTO() {
    }

    public ComandaDTO(Long id, EstadoComanda estado, LocalDateTime fechaHora, String folio, Double totalventa, DetallesComandaDTO detallesComanda, ClienteDTO cliente, MesaDTO mesa) {
        this.id = id;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalventa;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public ComandaDTO(EstadoComanda estado, LocalDateTime fechaHora, String folio, Double totalventa, DetallesComandaDTO detallesComanda, ClienteDTO cliente, MesaDTO mesa) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalventa;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public DetallesComandaDTO getDetallesComanda() {
        return detallesComanda;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public MesaDTO getMesa() {
        return mesa;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setDetallesComanda(DetallesComandaDTO detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public void setMesa(MesaDTO mesa) {
        this.mesa = mesa;
    }

    @Override
    public String toString() {
        return "ComandaDTO{" + "id=" + id + ", estado=" + estado + ", fechaHora=" + fechaHora + ", folio=" + folio + ", totalventa=" + totalVenta + ", detallesComanda=" + detallesComanda + ", cliente=" + cliente + ", mesa=" + mesa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.estado);
        hash = 67 * hash + Objects.hashCode(this.fechaHora);
        hash = 67 * hash + Objects.hashCode(this.folio);
        hash = 67 * hash + Objects.hashCode(this.totalVenta);
        hash = 67 * hash + Objects.hashCode(this.detallesComanda);
        hash = 67 * hash + Objects.hashCode(this.cliente);
        hash = 67 * hash + Objects.hashCode(this.mesa);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.fechaHora, other.fechaHora)) {
            return false;
        }
        if (!Objects.equals(this.totalVenta, other.totalVenta)) {
            return false;
        }
        if (!Objects.equals(this.detallesComanda, other.detallesComanda)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return Objects.equals(this.mesa, other.mesa);
    }

    
}
