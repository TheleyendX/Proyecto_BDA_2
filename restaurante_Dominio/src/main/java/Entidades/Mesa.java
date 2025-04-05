/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author mmax2
 */
@Entity
public class Mesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "numero", nullable = false, unique = true)
    private Integer numero;
    
    private boolean estadoMesa;
    
    // quitar 
    @OneToMany(mappedBy = "mesa")
    private List<Comanda> comandas;

    public Mesa() {
    }

    public Mesa(Long id, Integer numero, List<Comanda> comandas, boolean estadoMesa) {
        this.id = id;
        this.numero = numero;
        this.comandas = comandas;
        this.estadoMesa = estadoMesa;
    }

    public Mesa(Integer numero, List<Comanda> comandas, boolean estadoMesa) {
        this.numero = numero;
        this.comandas = comandas;
        this.estadoMesa = estadoMesa;
    }

    public boolean isEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(boolean estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }
    

    @Override
    public String toString() {
        return "Mesa{" + "id=" + id + ", numero=" + numero + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.numero);
        hash = 53 * hash + Objects.hashCode(this.comandas);
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
        final Mesa other = (Mesa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.comandas, other.comandas);
    }

    
    
}
