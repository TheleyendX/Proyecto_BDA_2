/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Ingrediente;
import Entidades.Producto;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class ProductoIngredienteDTO {

    public Producto producto;

    public Ingrediente ingrediente;

    public Double cantidadRequerida;

    public ProductoIngredienteDTO() {
    }

    public ProductoIngredienteDTO(Producto producto, Ingrediente ingrediente, Double cantidadRequerida) {
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantidadRequerida = cantidadRequerida;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.producto);
        hash = 89 * hash + Objects.hashCode(this.ingrediente);
        hash = 89 * hash + Objects.hashCode(this.cantidadRequerida);
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
        final ProductoIngredienteDTO other = (ProductoIngredienteDTO) obj;
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        if (!Objects.equals(this.ingrediente, other.ingrediente)) {
            return false;
        }
        return Objects.equals(this.cantidadRequerida, other.cantidadRequerida);
    }

    @Override
    public String toString() {
        return "ProductoIngredienteDTO{" + "producto=" + producto + ", ingrediente=" + ingrediente + ", cantidadRequerida=" + cantidadRequerida + '}';
    }

}
