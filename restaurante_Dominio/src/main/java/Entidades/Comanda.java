/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM.EstadoComanda;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author mmax2
 */
@Entity
public class Comanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoComanda estado;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(unique = true, nullable = false)
    private String folio;

    // derivado pero si se guarda
    private Double totalVenta;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // ver que cascada poner, no ALL
    @OneToMany(mappedBy = "comanda",  orphanRemoval = true)
    private List<DetallesComanda> detallesComanda;
    

    public Comanda() {
        this.detallesComanda = new ArrayList<>();
    }

    public Comanda(Long id, EstadoComanda estado, LocalDateTime fechaHora, String folio, Double totalVenta, Cliente cliente, List<DetallesComanda> detallesComanda) {
        this.id = id;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalVenta;
        this.cliente = cliente;
        this.detallesComanda = detallesComanda;
    }

    public Comanda(EstadoComanda estado, LocalDateTime fechaHora, String folio, Double totalVenta, Cliente cliente, List<DetallesComanda> detallesComanda) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalVenta;
        this.cliente = cliente;
        this.detallesComanda = detallesComanda;
    }

    public Long getId() {
        return id;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getFolio() {
        return folio;
    }

    public Double getTotalVenta() {
        return totalVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<DetallesComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setDetallesComanda(List<DetallesComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", estado=" + estado + ", fechaHora=" + fechaHora + ", folio=" + folio + ", totalVenta=" + totalVenta + ", cliente=" + cliente + ", detallesComanda=" + detallesComanda + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.estado);
        hash = 23 * hash + Objects.hashCode(this.fechaHora);
        hash = 23 * hash + Objects.hashCode(this.folio);
        hash = 23 * hash + Objects.hashCode(this.totalVenta);
        hash = 23 * hash + Objects.hashCode(this.cliente);
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
        final Comanda other = (Comanda) obj;
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
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return Objects.equals(this.detallesComanda, other.detallesComanda);
    }
    
    

    
}
