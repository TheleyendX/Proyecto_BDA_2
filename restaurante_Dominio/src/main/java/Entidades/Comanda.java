/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM.Estado;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author mmax2
 */
@Entity
public class Comanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaHora;

    private String folio;

    private Double totalVenta;

    private Cliente cliente;

    @OneToMany
    private List<DetallesComanda> detalles;

    public Comanda() {
        this.detalles = new ArrayList<>();
    }

    public Comanda(Long id, Estado estado, Date fechaHora, String folio, Double totalVenta, Cliente cliente, List<DetallesComanda> detalles) {
        this.id = id;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.folio = folio;
        this.totalVenta = totalVenta;
        this.cliente = cliente;
        this.detalles = detalles;
        this.detalles = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
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

    public Double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(Double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetallesComanda> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallesComanda> detalles) {
        this.detalles = detalles;
    }
}
