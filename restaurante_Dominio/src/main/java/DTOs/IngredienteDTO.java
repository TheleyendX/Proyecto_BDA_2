/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import ENUM.UnidadMedida;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class IngredienteDTO {

    public Double stock;

    public String nombre;

    public UnidadMedida undadMedida;

    public IngredienteDTO() {
    }

    public IngredienteDTO(Double stock, String nombre, UnidadMedida undadMedida) {
        this.stock = stock;
        this.nombre = nombre;
        this.undadMedida = undadMedida;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedida getUndadMedida() {
        return undadMedida;
    }

    public void setUndadMedida(UnidadMedida undadMedida) {
        this.undadMedida = undadMedida;
    }

    @Override
    public String toString() {
        return "IngredienteDTO{" + "stock=" + stock + ", nombre=" + nombre + ", undadMedida=" + undadMedida + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.stock);
        hash = 79 * hash + Objects.hashCode(this.nombre);
        hash = 79 * hash + Objects.hashCode(this.undadMedida);
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
        final IngredienteDTO other = (IngredienteDTO) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return this.undadMedida == other.undadMedida;
    }

}
