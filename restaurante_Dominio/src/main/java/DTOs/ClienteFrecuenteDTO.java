/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import Entidades.Comanda;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Clase DTO para representar a un cliente frecuente.
 * Extiende la clase ClienteDTO e incluye información adicional.
 * @author mmax2
 */
public class ClienteFrecuenteDTO extends ClienteDTO {
    private Integer puntos;
    
    private Double gastoTotalAcumulado;
    
    private Integer conteoVisitas;
    
    private LocalDate ultimaFechaComanda;

    /**
     * Constructor vacío.
     */
    public ClienteFrecuenteDTO() {
    }

    /**
     * Constructor con todos los atributos incluyendo los heredados.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado por el cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param id El ID del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(id, nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    /**
     * Constructor sin el ID (para cuando se crea un nuevo cliente frecuente).
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado por el cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    /**
     * Constructor con fecha de la última comanda realizada por el cliente.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado por el cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param ultimaFechaComanda La última fecha en la que el cliente realizó una comanda.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, LocalDate ultimaFechaComanda, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
        this.ultimaFechaComanda = ultimaFechaComanda;
    }

    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, LocalDate ultimaFechaComanda) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
        this.ultimaFechaComanda = ultimaFechaComanda;
    }

    /**
     * Constructor con todos los atributos.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado por el cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     * @param ultimaFechaComanda La última fecha en la que el cliente realizó una comanda.
     * @param id El ID del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidoP El apellido paterno del cliente.
     * @param apellidoM El apellido materno del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param fechaRegistro La fecha de registro del cliente.
     * @param comandas La lista de comandas asociadas al cliente.
     */
    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas, LocalDate ultimaFechaComanda, Long id, String nombre, String apellidoP, String apellidoM, String correo, String telefono, LocalDate fechaRegistro, List<Comanda> comandas) {
        super(id, nombre, apellidoP, apellidoM, correo, telefono, fechaRegistro, comandas);
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
        this.ultimaFechaComanda = ultimaFechaComanda;
    }
    
    
    /**
     * Constructor con puntos, gasto total acumulado y conteo de visitas.
     * 
     * @param puntos El número de puntos acumulados por el cliente.
     * @param gastoTotalAcumulado El gasto total acumulado por el cliente.
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     */
    public ClienteFrecuenteDTO(Integer puntos, Double gastoTotalAcumulado, Integer conteoVisitas) {
        this.puntos = puntos;
        this.gastoTotalAcumulado = gastoTotalAcumulado;
        this.conteoVisitas = conteoVisitas;
    }

    /**
     * Obtiene la última fecha de comanda realizada por el cliente.
     * 
     * @return La última fecha de comanda.
     */
    public LocalDate getUltimaFechaComanda() {
        return ultimaFechaComanda;
    }
    
    /**
     * Establece la última fecha de comanda realizada por el cliente.
     * 
     * @param ultimaFechaComanda La última fecha de comanda.
     */
    public void setUltimaFechaComanda(LocalDate ultimaFechaComanda) {
        this.ultimaFechaComanda = ultimaFechaComanda;
    }
    
    /**
     * Obtiene los puntos acumulados por el cliente.
     * 
     * @return Los puntos acumulados por el cliente.
     */
    public Integer getPuntos() {
        return puntos;
    }

    /**
     * Establece los puntos acumulados por el cliente.
     * 
     * @param puntos Los puntos acumulados por el cliente.
     */
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    /**
     * Obtiene el gasto total acumulado por el cliente.
     * 
     * @return gasto total acumulado por el cliente.
     */
    public Double getGastoTotalAcumulado() {
        return gastoTotalAcumulado;
    }

    /**
     * Establece el gasto total acumulado por el cliente.
     * 
     * @param gastoTotalAcumulado El gasto total acumulado por el cliente.
     */
    public void setGastoTotalAcumulado(Double gastoTotalAcumulado) {
        this.gastoTotalAcumulado = gastoTotalAcumulado;
    }

    /**
     * Obtiene el número de visitas realizadas por el cliente.
     * 
     * @return El número de visitas realizadas por el cliente.
     */
    public Integer getConteoVisitas() {
        return conteoVisitas;
    }

    /**
     * Establece el número de visitas realizadas por el cliente.
     * 
     * @param conteoVisitas El número de visitas realizadas por el cliente.
     */
    public void setConteoVisitas(Integer conteoVisitas) {
        this.conteoVisitas = conteoVisitas;
    }

    /**
     * Devuelve una representación en cadena de texto del objeto ClienteFrecuenteDTO.
     * 
     * @return Una cadena que describe al cliente frecuente.
     */
    @Override
    public String toString() {
        return "ClienteFrecuenteDTO{" + "puntos=" + puntos + ", gastoTotalAcumulado=" + gastoTotalAcumulado + ", conteoVisitas=" + conteoVisitas + '}';
    }

    /**
     * Genera un código hash único para el objeto ClienteFrecuenteDTO.
     * 
     * @return El código hash generado.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.puntos);
        hash = 43 * hash + Objects.hashCode(this.gastoTotalAcumulado);
        hash = 43 * hash + Objects.hashCode(this.conteoVisitas);
        hash = 43 * hash + Objects.hashCode(this.ultimaFechaComanda);
        return hash;
    }

    /**
     * Compara el objeto actual con otro ClienteFrecuenteDTO para determinar si son equivalentes.
     * Dos objetos ClienteFrecuenteDTO se consideran iguales si tienen los mismos valores
     * @param obj El objeto con el que se desea comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
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
        if (!Objects.equals(this.conteoVisitas, other.conteoVisitas)) {
            return false;
        }
        return Objects.equals(this.ultimaFechaComanda, other.ultimaFechaComanda);
    }
}
