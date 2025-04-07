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
import javax.persistence.OneToOne;
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

    @OneToMany(mappedBy = "comanda", orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<DetallesComanda> detallesComanda;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)  
    private Cliente cliente;
    
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;
    
    public Comanda() {
        this.detallesComanda = new ArrayList<>();
    }

    public Comanda(Long id, EstadoComanda estado, LocalDateTime fechaHora, String folio, Double totalVenta, List<DetallesComanda> detallesComanda, Cliente cliente, Mesa mesa) {
        this.id = id;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public Comanda(EstadoComanda estado, LocalDateTime fechaHora, String folio, Double totalVenta, List<DetallesComanda> detallesComanda, Cliente cliente, Mesa mesa) {
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalVenta;
        this.detallesComanda = detallesComanda;
        this.cliente = cliente;
        this.mesa = mesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

    public List<DetallesComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setDetallesComanda(List<DetallesComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.estado);
        hash = 59 * hash + Objects.hashCode(this.fechaHora);
        hash = 59 * hash + Objects.hashCode(this.folio);
        hash = 59 * hash + Objects.hashCode(this.totalVenta);
        hash = 59 * hash + Objects.hashCode(this.detallesComanda);
        hash = 59 * hash + Objects.hashCode(this.cliente);
        hash = 59 * hash + Objects.hashCode(this.mesa);
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
        if (!Objects.equals(this.detallesComanda, other.detallesComanda)) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        return Objects.equals(this.mesa, other.mesa);
    }

    @Override
    public String toString() {
        return "Comanda{" + "id=" + id + ", estado=" + estado + ", fechaHora=" + fechaHora + ", folio=" + folio + ", totalVenta=" + totalVenta + ", detallesComanda=" + detallesComanda + ", cliente=" + cliente + ", mesa=" + mesa + '}';
    }
    
    

    
}
