/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import ENUM.*;
import Entidades.Ingrediente;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class ProductoDTO {

    public TipoProducto tipoProducto;

    public EstadoProducto estadoProducto;

    public String nombre;

    public Double precio;

    public List<Ingrediente> productoIngrediente;

    public ProductoDTO() {
        this.productoIngrediente = new ArrayList<>();
    }

    public ProductoDTO(TipoProducto tipoProducto, EstadoProducto estadoProducto, String nombre, Double precio, List<Ingrediente> productoIngrediente) {
        this.tipoProducto = tipoProducto;
        this.estadoProducto = estadoProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.productoIngrediente = productoIngrediente;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public List<Ingrediente> getProductoIngrediente() {
        return productoIngrediente;
    }

    public void setProductoIngrediente(List<Ingrediente> productoIngrediente) {
        this.productoIngrediente = productoIngrediente;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.tipoProducto);
        hash = 71 * hash + Objects.hashCode(this.estadoProducto);
        hash = 71 * hash + Objects.hashCode(this.nombre);
        hash = 71 * hash + Objects.hashCode(this.precio);
        hash = 71 * hash + Objects.hashCode(this.productoIngrediente);
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
        final ProductoDTO other = (ProductoDTO) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (this.tipoProducto != other.tipoProducto) {
            return false;
        }
        if (this.estadoProducto != other.estadoProducto) {
            return false;
        }
        if (!Objects.equals(this.precio, other.precio)) {
            return false;
        }
        return Objects.equals(this.productoIngrediente, other.productoIngrediente);
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "tipoProducto=" + tipoProducto + ", estadoProducto=" + estadoProducto + ", nombre=" + nombre + ", precio=" + precio + ", productoIngrediente=" + productoIngrediente + '}';
    }

}
