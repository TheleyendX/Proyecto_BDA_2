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
 * @author katia
 */
@Entity
public class ProductoIngrediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    @ManyToOne
    @JoinColumn(name = "id_ingrediente", nullable = false)
    private Ingrediente ingrediente;
    
    @Column(nullable = false)
    private double cantidadRequerida;

    public ProductoIngrediente() {
    }

    public ProductoIngrediente(Long id, Producto producto, Ingrediente ingrediente, double cantidadRequerida) {
        this.id = id;
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantidadRequerida = cantidadRequerida;
    }

    public ProductoIngrediente(Producto producto, Ingrediente ingrediente, double cantidadRequerida) {
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantidadRequerida = cantidadRequerida;
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setCantidadRequerida(double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    @Override
    public String toString() {
        return "ProductoIngrediente{" + "id=" + id + ", producto=" + producto + ", ingrediente=" + ingrediente + ", cantidadRequerida=" + cantidadRequerida + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.producto);
        hash = 89 * hash + Objects.hashCode(this.ingrediente);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.cantidadRequerida) ^ (Double.doubleToLongBits(this.cantidadRequerida) >>> 32));
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
        final ProductoIngrediente other = (ProductoIngrediente) obj;
        if (Double.doubleToLongBits(this.cantidadRequerida) != Double.doubleToLongBits(other.cantidadRequerida)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        return Objects.equals(this.ingrediente, other.ingrediente);
    }
    
    
}
