/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.Objects;

/**
 *
 * @author mmax2
 */
public class ClienteFrecuenteDTO {
    public Integer puntos;
    
    public Double gastoTotalAcumulado;
    
    public Integer conteoVisitas;

    public ClienteFrecuenteDTO() {
    }

    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public Double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    public void setGastoTotalAcumulado(Double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public Integer getConteoVisitas() {
        return conteoVisitas;
    }

    public void setConteoVisitas(Integer conteoVisitas) {
        this.conteoVisitas = conteoVisitas;
    }

    @Override
    public String toString() {
        return "ClienteFrecuenteDTO{" + "puntos=" + puntos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.puntos);
        hash = 41 * hash + Objects.hashCode(this.gastoTotalAcumulado);
        hash = 41 * hash + Objects.hashCode(this.conteoVisitas);
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
        final ClienteFrecuenteDTO other = (ClienteFrecuenteDTO) obj;
        if (!Objects.equals(this.puntos, other.puntos)) {
            return false;
        }
        if (!Objects.equals(this.gastoTotalAcumulado, other.gastoTotalAcumulado)) {
            return false;
        }
        return Objects.equals(this.conteoVisitas, other.conteoVisitas);
    }
    
    
}
