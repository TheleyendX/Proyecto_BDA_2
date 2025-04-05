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

    public Integer numero;

    public List<Comanda> comanda;

    public boolean estadoMesa;

    public MesaDTO() {
    }

    @Override
    public String toString() {
        return "MesaDTO{" + "numero=" + numero + ", comanda=" + comanda + ", estadoMesa=" + estadoMesa + '}';
    }

    public MesaDTO(Integer numero, List<Comanda> comanda, boolean estadoMesa) {
        this.numero = numero;
        this.comanda = comanda;
        this.estadoMesa = estadoMesa;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Comanda> getComanda() {
        return comanda;
    }

    public void setComanda(List<Comanda> comanda) {
        this.comanda = comanda;
    }

    public boolean isEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(boolean estadoMesa) {
        this.estadoMesa = estadoMesa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.numero);
        hash = 47 * hash + Objects.hashCode(this.comanda);
        hash = 47 * hash + (this.estadoMesa ? 1 : 0);
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
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return Objects.equals(this.comanda, other.comanda);
    }

}
