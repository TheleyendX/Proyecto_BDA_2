/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM.UnidadMedida;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author mmax2
 */
@Entity
public class Ingrediente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double stock;

    @Column(nullable = false)
    private String nombre;

    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;

    // no bidireccional
    // hacerlo unidireccional, al ingrediente no le importa donde esta siendo utilizado
    @OneToMany(mappedBy = "ingrediente")
    private List<ProductoIngrediente> productosIngredientes;

    public Ingrediente() {
    }

    public Ingrediente(Long id, Double stock, String nombre, UnidadMedida unidadMedida, List<ProductoIngrediente> productosIngredientes) {
        this.id = id;
        this.stock = stock;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.productosIngredientes = productosIngredientes;
    }

    public Ingrediente(Double stock, String nombre, UnidadMedida unidadMedida, List<ProductoIngrediente> productosIngredientes) {
        this.stock = stock;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.productosIngredientes = productosIngredientes;
    }

    public Long getId() {
        return id;
    }

    public double getStock() {
        return stock;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public List<ProductoIngrediente> getProductosIngredientes() {
        return productosIngredientes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void setProductosIngredientes(List<ProductoIngrediente> productosIngredientes) {
        this.productosIngredientes = productosIngredientes;
    }

    @Override
    public String toString() {
        return "Ingrediente{" + "id=" + id + ", stock=" + stock + ", nombre=" + nombre + ", unidadMedida=" + unidadMedida + ", productosIngredientes=" + productosIngredientes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.stock);
        hash = 83 * hash + Objects.hashCode(this.nombre);
        hash = 83 * hash + Objects.hashCode(this.unidadMedida);
        hash = 83 * hash + Objects.hashCode(this.productosIngredientes);
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
        final Ingrediente other = (Ingrediente) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        if (this.unidadMedida != other.unidadMedida) {
            return false;
        }
        return Objects.equals(this.productosIngredientes, other.productosIngredientes);
    }
}
