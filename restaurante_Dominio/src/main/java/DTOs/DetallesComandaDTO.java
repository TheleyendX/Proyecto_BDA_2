/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Comanda;
import Entidades.Producto;
import java.util.Objects;

/**
 *
 * @author katia
 */
public class DetallesComandaDTO {
    public Double precioPorPorducto;
    
    public String comentarios;
    
    public Integer cantidadRequerida;
    
    public Double importeTotal;
    
    public Producto producto;
    
    public Comanda comanda;

    public DetallesComandaDTO() {
    }

    public DetallesComandaDTO(Double precioPorPorducto, String comentarios, Integer cantidadRequerida, Double importeTotal, Producto producto, Comanda comanda) {
        this.precioPorPorducto = precioPorPorducto;
        this.comentarios = comentarios;
        this.cantidadRequerida = cantidadRequerida;
        this.importeTotal = importeTotal;
        this.producto = producto;
        this.comanda = comanda;
    }

    public Double getPrecioPorPorducto() {
        return precioPorPorducto;
    }

    public void setPrecioPorPorducto(Double precioPorPorducto) {
        this.precioPorPorducto = precioPorPorducto;
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
        return "DetallesComandaDTO{" + "precioPorPorducto=" + precioPorPorducto + ", comentarios=" + comentarios + ", cantidadRequerida=" + cantidadRequerida + ", importeTotal=" + importeTotal + ", producto=" + producto + ", comanda=" + comanda + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.precioPorPorducto);
        hash = 37 * hash + Objects.hashCode(this.comentarios);
        hash = 37 * hash + Objects.hashCode(this.cantidadRequerida);
        hash = 37 * hash + Objects.hashCode(this.importeTotal);
        hash = 37 * hash + Objects.hashCode(this.producto);
        hash = 37 * hash + Objects.hashCode(this.comanda);
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
        final DetallesComandaDTO other = (DetallesComandaDTO) obj;
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.precioPorPorducto, other.precioPorPorducto)) {
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
