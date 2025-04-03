/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM.TipoProducto;
import java.awt.image.TileObserver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author mmax2
 */
@Entity
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoProducto tipo;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private Double precio;
    
    @OneToMany(mappedBy = "producto")
    private List<Ingrediente> productoIngredientes;

    // hacerlo unidireccional
    @OneToMany(mappedBy = "producto")
    private List<DetallesComanda> detallesComanda;
    
    public Producto() {
        this.productoIngredientes = new ArrayList<>();
        this.detallesComanda = new ArrayList<>();
    }

    public Producto(Long id, TipoProducto tipo, String nombre, Double precio, List<Ingrediente> productoIngredientes, List<DetallesComanda> detallesComanda) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        this.productoIngredientes = productoIngredientes;
        this.detallesComanda = detallesComanda;
    }

    public Producto(TipoProducto tipo, String nombre, Double precio, List<Ingrediente> productoIngredientes, List<DetallesComanda> detallesComanda) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.precio = precio;
        this.productoIngredientes = productoIngredientes;
        this.detallesComanda = detallesComanda;
    }

    public Long getId() {
        return id;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public List<Ingrediente> getProductoIngredientes() {
        return productoIngredientes;
    }

    public List<DetallesComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public void setProductoIngredientes(List<Ingrediente> productoIngredientes) {
        this.productoIngredientes = productoIngredientes;
    }

    public void setDetallesComanda(List<DetallesComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", precio=" + precio + ", productoIngredientes=" + productoIngredientes + ", detallesComanda=" + detallesComanda + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.tipo);
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.precio);
        hash = 67 * hash + Objects.hashCode(this.productoIngredientes);
        hash = 67 * hash + Objects.hashCode(this.detallesComanda);
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
        final Producto other = (Producto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        if (!Objects.equals(this.productoIngredientes, other.productoIngredientes)) {
            return false;
        }
        return Objects.equals(this.detallesComanda, other.detallesComanda);
    }

    
}
