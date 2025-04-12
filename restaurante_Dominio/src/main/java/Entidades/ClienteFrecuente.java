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
 * Clase que representa a un cliente frecuente, deriva de la clase
 * Cliente, contiene atributos adicionales.
 * @author mmax2
 */
@Entity
@DiscriminatorValue("ClienteFrecuente")
public class ClienteFrecuente extends Cliente implements Serializable {

    
    @Transient
    private Integer puntos;

    @Transient
    private Double gastoTotalAcumulado;

    @Transient
    private Integer conteoVisitas;

    @Transient
    private LocalDate ultimaFechaComanda;
    
    /**
     * Constructor por defecto.
     */
    public ClienteFrecuente() {
    }

    /**
     * Constructor para crear un ClienteFrecuente con puntos, gasto total acumulado y conteo de visitas.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado del cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     */
    public ClienteFrecuente(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    /**
     * Constructor para crear un ClienteFrecuente con los atributos del cliente base y los atributos específicos 
     * de cliente frecuente como puntos, gasto total acumulado y conteo de visitas.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado del cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param id El identificador único del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha en la que se registró el cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteFrecuente(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(id, nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    /**
     * Constructor para crear un ClienteFrecuente con los atributos específicos de cliente frecuente, incluyendo 
     * la última fecha de comanda.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado del cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param ultimaFechaComanda La última fecha de comanda realizada por el cliente.
     */
    public ClienteFrecuente(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, LocalDate ultimaFechaComanda) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
        this.ultimaFechaComanda = ultimaFechaComanda;
    }

    /**
     * Constructor para crear un ClienteFrecuente con todos los atributos, incluidos los del cliente base 
     * y los específicos del cliente frecuente.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado del cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param ultimaFechaComanda La última fecha de comanda realizada por el cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha en la que se registró el cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteFrecuente(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, LocalDate ultimaFechaComanda, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
        this.ultimaFechaComanda = ultimaFechaComanda;
    }

    public LocalDate getUltimaFechaComanda() {
        return ultimaFechaComanda;
    }

    public void setUltimaFechaComanda(LocalDate ultimaFechaComanda) {
        this.ultimaFechaComanda = ultimaFechaComanda;
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
        return "ClienteFrecuente{" + "puntos=" + puntos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + ", ultimaFechaComanda=" + ultimaFechaComanda + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.puntos);
        hash = 89 * hash + Objects.hashCode(this.gastoTotalAcumulado);
        hash = 89 * hash + Objects.hashCode(this.conteoVisitas);
        hash = 89 * hash + Objects.hashCode(this.ultimaFechaComanda);
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
        if (!Objects.equals(this.conteoVisitas, other.conteoVisitas)) {
            return false;
        }
        return Objects.equals(this.ultimaFechaComanda, other.ultimaFechaComanda);
    }
}
