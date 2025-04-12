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
import IDAOs.IReporteClientesFrecuentesDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Clase que contiene los métodos necesarios para obtener, y filtrar los datos 
 * relacionados a clientes frecuentes.
 * @author katia
 */
public class ReporteClientesFrecuentesDAO implements IReporteClientesFrecuentesDAO{
    
    /**
     * Obtiene una lista de clientes frecuentes filtrada por nombre y mínimo de visitas. 
     * (Los filtros son opcionales).
     * @param nombre Nombre completo o parcial del cliente que se desea obtener.
     * @param visitasMinimas Cantidad de visitas mínimas que deben tener los clientes
     * que se desea obtener.
     * @return
     * @throws PersistenciaException
     */
    @Override
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
    
    /**
     * Obtiene la última fecha de comanda registrada que tenga un cliente frecuente.
     * @param cliente Cliente al que se consulta su historial de comanda.s
     * @return Última fecha de comanda del cliente.
     */
    @Override
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
    
    /**
     * Método encargado de calcular la cantidad de visitas realizadas por un
     * cliente al restaurante.
     * @param cliente Cliente frecuente cuyo número de visitas se consulta.
     * @return Número total de visitas realizadas por el cliente, se regresa
     * un 0 en caso de que no tenga.
     */    
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
    
    /**
     * Método que calcula los puntos de un cliente frecuente basado en el gasto
     * total acumulado que este tenga.
     * @param cliente Cliente cuyo puntaje se consulta.
     * @return Cantidad de puntos acumulados por el cliente.
     */
    public Integer obtenerPuntos(ClienteFrecuente cliente){
        Double totalGasto = obtenerGastoTotalAcumulado(cliente);
        Integer puntos = (int)(totalGasto / 20);
        cliente.setPuntos(puntos);
        return puntos;
    }
    
    /**
     * Calcula el gasto total acumulado de un cliente frecuente, en base al
     * total de la venta de cada comanda que tenga asociada.
     * @param cliente Cliente frecuente cuyo gasto total se consulta.
     * @return Cantidad que ha gastado en el restaurante, o 0.0 en caso que no tenga.
     */
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
    
    //PARA LAS PRUEBAS UNITARIAS
    /**
     * MÉTODO UTILIZADO SOLAMENTE PARA PRUEBAS
     * Método que registra un nuevo cliente frecuente en la bd.
     * @param cliente Cliente frecuente que se desea registrar.
     * @return El cliente ya registrado, con su ID asignado.
     * @throws PersistenciaException En caso de que ocurra algún error durante 
     * el registro.
     */
    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente cliente) throws PersistenciaException{
        EntityManager em = Conexion.crearConexion();
        try{
            if (em == null || !em.isOpen()){
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            
            cliente.setFechaRegistro(LocalDate.now());
            
            String telefonoEncriptado = Encriptador.encrypt(cliente.getTelefono());
            cliente.setTelefono(telefonoEncriptado);
            
            em.persist(cliente);
            em.getTransaction().commit();
            em.refresh(cliente);
            obtenerGastoTotalAcumulado(cliente);
            obtenerConteoVisitas(cliente);
            obtenerPuntos(cliente);
             
            return cliente;
        } catch(Exception e){
            if (em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo registrar el cliente.");
            
        } finally{
            if (em != null && em.isOpen()){
                em.close();
            }
        }
    }
    
    /**
     * MÉTODO UTILIZADO SOLAMENTE PARA PRUEBAS.
     * Se encarga de persistir una comanda en la base de datos.
     * @param comanda Comanda que se desea guardar.
     * @throws PersistenciaException Por si ocurre un error durante el registro
     * de la comanda.
     */
    public void persistirComanda(Comanda comanda) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try{
            if (em == null || !em.isOpen()){
                    em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(comanda);
            em.getTransaction().commit();

        }catch (Exception e){
                em.getTransaction().rollback();
                throw new PersistenciaException("No se pudo persistir la comanda.");

            } finally{
            if (em != null && em.isOpen()){
                em.close();
            }
            }
    }

}
