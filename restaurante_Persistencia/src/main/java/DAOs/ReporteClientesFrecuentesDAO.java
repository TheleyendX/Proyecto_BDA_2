/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import ENUM.EstadoComanda;
import Encriptador.Encriptador;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author katia
 */
public class ReporteClientesFrecuentesDAO extends ClienteFrecuenteDAO{
    
    public List<ClienteFrecuente> obtenerClientesFrecuentesPorFiltro(String nombre, Integer visitasMinimas) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            List<ClienteFrecuente> clientes = em.createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente.class).getResultList();
            List<ClienteFrecuente> resultado = new ArrayList<>();

            for (ClienteFrecuente cliente : clientes) {                
                cliente.setConteoVisitas(obtenerConteoVisitas(cliente));
                cliente.setGastoTotalAcumulado(obtenerGastoTotalAcumulado(cliente));
                cliente.setPuntos(obtenerPuntos(cliente));
                cliente.setUltimaFechaComanda(obtenerUltimaFechaComanda(cliente));

                boolean coincideNombre = true;
                boolean coincideVisitas = true;

                if (nombre != null && !nombre.trim().isEmpty()) {
                    String nombreCompleto = (cliente.getNombre() + " " + cliente.getApellidoP());
                    if (cliente.getApellidoM() != null && !cliente.getApellidoM().trim().isEmpty()) {
                    nombreCompleto += " " + cliente.getApellidoM(); 
                    }
                    nombreCompleto = nombreCompleto.toLowerCase();
                    coincideNombre = nombreCompleto.contains(nombre.trim().toLowerCase());
                }

                if (visitasMinimas != null) {
                    coincideVisitas = cliente.getConteoVisitas() >= visitasMinimas;
                }

                if (coincideNombre && coincideVisitas) {
                    resultado.add(cliente);
                }
            }

            return resultado;
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron obtener los clientes frecuentes filtrados.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public LocalDate obtenerUltimaFechaComanda(ClienteFrecuente cliente) {
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<LocalDateTime> query = em.createQuery(
                "SELECT MAX(c.fechaHora) FROM Comanda c WHERE c.cliente.id = :clienteId AND c.estado = :estado",
                LocalDateTime.class
            );
            query.setParameter("clienteId", cliente.getId());
            query.setParameter("estado", EstadoComanda.Entregado);

            LocalDateTime ultima = query.getSingleResult();
            return ultima != null ? ultima.toLocalDate() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public Integer obtenerConteoVisitas(ClienteFrecuente cliente){
        EntityManager em = Conexion.crearConexion();
        try {
            TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(c) FROM Comanda c WHERE c.cliente.id = :clienteId AND c.estado = :estado",
                Long.class
            );
            query.setParameter("clienteId", cliente.getId());
            query.setParameter("estado", EstadoComanda.Entregado);
            Long conteo = query.getSingleResult();
            cliente.setConteoVisitas(conteo.intValue());
            return conteo.intValue();
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        } finally {
            if (em != null && em.isOpen()){
                em.close();
            }
        }
    }
    
    public Integer obtenerPuntos(ClienteFrecuente cliente){
        Double totalGasto = obtenerGastoTotalAcumulado(cliente);
        Integer puntos = (int)(totalGasto / 20);
        cliente.setPuntos(puntos);
        return puntos;
    }
    
    public Double obtenerGastoTotalAcumulado(ClienteFrecuente cliente){
        EntityManager em = Conexion.crearConexion();
        Double total = 0.0;
        try {
            TypedQuery<Double> query = em.createQuery(
                "SELECT SUM(c.totalVenta) FROM Comanda c WHERE c.cliente.id = :clienteId AND c.estado = :estado",
                Double.class
            );
            query.setParameter("clienteId", cliente.getId());
            query.setParameter("estado", EstadoComanda.Entregado);
            total = query.getSingleResult();
            total = total != null ? total : 0.0;
            cliente.setGastoTotalAcumulado(total);
            return total;
        } catch (Exception e){
            e.printStackTrace();
            return 0.0;
        } finally {
            if (em != null && em.isOpen()){
                em.close();
            }
        }
    }

}
