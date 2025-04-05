/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author mmax2
 */
@Entity
public class DetallesComanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double precioPorProducto;

    @Column
    private String comentarios;

    @Column(nullable = false)
    private Integer cantidadRequerida;

    @Column(nullable = false)
    private Double importeTotal;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
    
    @ManyToOne
    @JoinColumn (name = "comanda_id")
    private Comanda comanda;

    public DetallesComanda() {
    }

    public DetallesComanda(Long id, Double precioPorProducto, String comentarios, int cantidadRequerida, Comanda comanda, Double importeTotal, Producto producto) {
        this.id = id;
        this.precioPorProducto = precioPorProducto;
        this.comentarios = comentarios;
        this.cantidadRequerida = cantidadRequerida;
        this.importeTotal = importeTotal;
        this.producto = producto;
        this.comanda = comanda;
    }

    public DetallesComanda(Double precioPorProducto, String comentarios, int cantidadRequerida, Comanda comanda , Double importeTotal, Producto producto) {
        this.precioPorProducto = precioPorProducto;
        this.comentarios = comentarios;
        this.cantidadRequerida = cantidadRequerida;
        this.importeTotal = importeTotal;
        this.producto = producto;
        this.comanda = comanda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioPorProducto() {
        return precioPorProducto;
    }

    public void setPrecioPorProducto(Double precioPorProducto) {
        this.precioPorProducto = precioPorProducto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Integer cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    @Override
    public String toString() {
        return "DetallesComanda{" + "id=" + id + ", precioPorProducto=" + precioPorProducto + ", comentarios=" + comentarios + ", cantidadRequerida=" + cantidadRequerida + ", importeTotal=" + importeTotal + ", producto=" + producto + ", comanda=" + comanda + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.precioPorProducto);
        hash = 29 * hash + Objects.hashCode(this.comentarios);
        hash = 29 * hash + Objects.hashCode(this.cantidadRequerida);
        hash = 29 * hash + Objects.hashCode(this.importeTotal);
        hash = 29 * hash + Objects.hashCode(this.producto);
        hash = 29 * hash + Objects.hashCode(this.comanda);
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
        final DetallesComanda other = (DetallesComanda) obj;
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.precioPorProducto, other.precioPorProducto)) {
            return false;
        }
        if (!Objects.equals(this.cantidadRequerida, other.cantidadRequerida)) {
            return false;
        }
        if (!Objects.equals(this.importeTotal, other.importeTotal)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return Objects.equals(this.comanda, other.comanda);
    }

    
  

}
