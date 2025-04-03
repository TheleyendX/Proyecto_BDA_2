/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author mmax2
 */
@Entity
public class DetallesComanda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double precioPorProducto;
    
    private String comentario;
    
    private Double cantidadRequerida;
    
    private Double importeTotal;
    
    @ManyToOne
    private Comanda comanda;
    
    @ManyToOne
    private Producto producto;

    public DetallesComanda() {
    }

    public DetallesComanda(Long id, Double precioPorProducto, String comentario, Double cantidadRequerida, Double importeTotal, Comanda comanda) {
        this.id = id;
        this.precioPorProducto = precioPorProducto;
        this.comentario = comentario;
        this.cantidadRequerida = cantidadRequerida;
        this.importeTotal = importeTotal;
        this.comanda = comanda;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getCantidadRequerida() {
        return cantidadRequerida;
    }

    public void setCantidadRequerida(Double cantidadRequerida) {
        this.cantidadRequerida = cantidadRequerida;
    }

    public Double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Double importeTotal) {
        this.importeTotal = importeTotal;
    }

    @Override
    public String toString() {
        return "DetallesComanda{" + "id=" + id + ", precioPorProducto=" + precioPorProducto + ", comentario=" + comentario + ", cantidadRequerida=" + cantidadRequerida + ", importeTotal=" + importeTotal + ", comanda=" + comanda + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.precioPorProducto);
        hash = 97 * hash + Objects.hashCode(this.comentario);
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
        if (!Objects.equals(this.comentario, other.comentario)) {
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
