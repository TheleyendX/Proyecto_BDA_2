/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Comanda;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class MesaDTO {

    private Long id;
    private Integer numero;

    //public List<Comanda> comanda;

    private boolean estadoMesa;

    public MesaDTO() {
    }

    public MesaDTO(Long id, Integer numero, boolean estadoMesa) {
        this.id = id;
        this.numero = numero;
        this.estadoMesa = estadoMesa;
    }

    public MesaDTO(Integer numero, boolean estadoMesa) {
        this.numero = numero;
        this.estadoMesa = estadoMesa;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    public boolean isEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(boolean estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    @Override
    public String toString() {
        return "MesaDTO{" + "id=" + id + ", numero=" + numero + ", estadoMesa=" + estadoMesa + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.numero);
        hash = 83 * hash + (this.estadoMesa ? 1 : 0);
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
        final MesaDTO other = (MesaDTO) obj;
        if (this.estadoMesa != other.estadoMesa) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.numero, other.numero);
    }

    

}
