/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 *
 * @author mmax2
 */
@Entity
@DiscriminatorValue("ClienteFrecuente")
public class ClienteFrecuente extends Cliente implements Serializable {

    // no guardar atributos derivados en la bd, usar @transient
    // entidades con atributos calculados solo get
    // q solo dao pueda asignar el valor
    @Transient
    private Integer puntos;

    @Transient
    private Double gastoTotalAcumulado;

    @Transient
    private Integer conteoVisitas;

    public ClienteFrecuente() {
    }

    public ClienteFrecuente(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    public ClienteFrecuente(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(id, nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public Double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    public Integer getConteoVisitas() {
        return conteoVisitas;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public void setGastoTotalAcumulado(Double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    public void setConteoVisitas(Integer conteoVisitas) {
        this.conteoVisitas = conteoVisitas;
    }
    
    

    @Override
    public String toString() {
        return "ClienteFrecuente{" + "puntos=" + puntos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.puntos);
        hash = 97 * hash + Objects.hashCode(this.gastoTotalAcumulado);
        hash = 97 * hash + Objects.hashCode(this.conteoVisitas);
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
        final ClienteFrecuente other = (ClienteFrecuente) obj;
        if (!Objects.equals(this.puntos, other.puntos)) {
            return false;
        }
        if (!Objects.equals(this.gastoTotalAcumulado, other.gastoTotalAcumulado)) {
            return false;
        }
        return Objects.equals(this.conteoVisitas, other.conteoVisitas);
    }
}
