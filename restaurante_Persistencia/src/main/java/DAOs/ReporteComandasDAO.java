/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import Encriptador.Encriptador;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author katia
 */
public class ReporteComandasDAO {
    EntityManager em = Conexion.crearConexion();
    
    
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
    
    
    // PARA LAS PRUEBAS
    
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
