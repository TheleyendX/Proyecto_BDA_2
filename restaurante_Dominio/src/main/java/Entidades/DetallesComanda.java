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
import javax.persistence.OneToOne;

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
    
    // detallecomanda solo tiene 1
    @OneToOne
    @JoinColumn(name = "comanda_id", nullable = false)
    private Comanda comanda;
    
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public DetallesComanda() {
    }

    public DetallesComanda(Long id, Double precioPorProducto, String comentarios, int cantidadRequerida, Double importeTotal, Comanda comanda, Producto producto) {
        this.id = id;
        this.precioPorProducto = precioPorProducto;
        this.comentarios = comentarios;
        this.cantidadRequerida = cantidadRequerida;
        this.importeTotal = importeTotal;
        this.comanda = comanda;
        this.producto = producto;
    }

    public DetallesComanda(Double precioPorProducto, String comentarios, int cantidadRequerida, Double importeTotal, Comanda comanda, Producto producto) {
        this.precioPorProducto = precioPorProducto;
        this.comentarios = comentarios;
        this.cantidadRequerida = cantidadRequerida;
        this.importeTotal = importeTotal;
        this.comanda = comanda;
        this.producto = producto;
    }

    public Long getId() {
        return id;
    }

    public Double getPrecioPorProducto() {
        return precioPorProducto;
    }

    public String getComentarios() {
        return comentarios;
    }

    public int getCantidadRequerida() {
        return cantidadRequerida;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrecioPorProducto(Double precioPorProducto) {
        this.precioPorProducto = precioPorProducto;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public void setCantidadRequerida(int cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public String toString() {
        return "DetallesComanda{" + "id=" + id + ", precioPorProducto=" + precioPorProducto + ", comentarios=" + comentarios + ", cantidadRequerida=" + cantidadRequerida + ", importeTotal=" + importeTotal + ", comanda=" + comanda + ", producto=" + producto + '}';
    }

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.precioPorProducto);
        hash = 97 * hash + Objects.hashCode(this.comentarios);
        hash = 97 * hash + Objects.hashCode(this.cantidadRequerida);
        hash = 97 * hash + Objects.hashCode(this.importeTotal);
        hash = 97 * hash + Objects.hashCode(this.comanda);
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
        return Objects.equals(this.comanda, other.comanda);
    }
    
    
}
