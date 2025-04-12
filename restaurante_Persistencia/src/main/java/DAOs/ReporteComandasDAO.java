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
import IDAOs.IReporteComandasDAO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Clase utilizada para obtener datos de las comandas desde la base de datos.
 * Permite filtrarlas por un rango de fecha y calcular el total acumulado.
 * @author katia
 */
public class ReporteComandasDAO implements IReporteComandasDAO{
    EntityManager em = Conexion.crearConexion();
    
    /**
     * Obtiene una lista de comandas que coincidan con los filtros de búsqueda
     * proporcionados por el usuario. (Filtros opcionales).
     * @param inicio Fecha y hora de inicio del rango de búsqueda.
     * @param fin Fecha y hora de fin del rango de búsqueda.
     * @return Lista de comandas que coincidan con el rango de fechas
     * proporcionados.
     * @throws PersistenciaException Por si sucede algún error.
     */
    @Override
    public List<Comanda> obtenerComandasFiltros(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException {
        if (em == null || !em.isOpen()) {
            em = Conexion.crearConexion();
        }    
        try {
            String consulta = "SELECT c FROM Comanda c WHERE 1=1";
            if (inicio != null) {
                consulta += " AND c.fechaHora >= :inicio";
            }
            if (fin != null) {
                consulta += " AND c.fechaHora <= :fin";
            }
            consulta += " ORDER BY c.fechaHora";

            TypedQuery<Comanda> query = em.createQuery(consulta, Comanda.class);

            if (inicio != null) {
                query.setParameter("inicio", inicio);
            }
            if (fin != null) {
                query.setParameter("fin", fin);
            }

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener comandas filtradas.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    /**
     * Obtiene el total acumulado de ventas dentro del rango de fechas que se
     * proporcione.
     * @param inicio Fecha y hora de inicio del rango de búsqueda.
     * @param fin Fecha y hora de fin del rango de búsqueda.
     * @return El total acumulado de ventas dentro del rango de fechas.
     * @throws PersistenciaException En caso de error.
     */
    @Override
    public Double obtenerTotalAcumuladoVentas(LocalDateTime inicio, LocalDateTime fin) throws PersistenciaException {
        if (em == null || !em.isOpen()) {
            em = Conexion.crearConexion();
        }

        try {
            String consulta = "SELECT SUM(c.totalVenta) FROM Comanda c WHERE c.estado = :estado";

            if (inicio != null) {
                consulta += " AND c.fechaHora >= :inicio";
            }
            if (fin != null) {
                consulta += " AND c.fechaHora <= :fin";
            }

            TypedQuery<Double> query = em.createQuery(consulta, Double.class);
            query.setParameter("estado", EstadoComanda.Entregado);

            if (inicio != null) {
                query.setParameter("inicio", inicio);
            }
            if (fin != null) {
                query.setParameter("fin", fin);
            }

            Double totalAcumulado = query.getSingleResult();
            return totalAcumulado != null ? totalAcumulado : 0.0;

        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener el total acumulado de ventas.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    
    // PARA LAS PRUEBAS
    /**
     * MÉTODO USADO PARA REALIZAR PRUEBAS.
     * Registra un nuevo cliente frecuente en la base de datos.
     * @param cliente Cliente que se desea registrar.
     * @return Cliente registrado.
     * @throws PersistenciaException En caso de error durante el registro del
     * cliente.
     */
    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente cliente) throws PersistenciaException{
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
     * MÉTODO USADO PARA REALIZAR PRUEBAS.
     * Persiste una nueva comanda en la base de datos.
     * @param comanda La comanda que se quiere guardar en la bd.
     * @throws PersistenciaException En caso de error al guardar la comanda.
     */
    public void persistirComanda(Comanda comanda) throws PersistenciaException {
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
