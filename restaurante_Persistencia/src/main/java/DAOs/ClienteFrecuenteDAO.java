/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import IDAOs.IClienteFrecuenteDAO;
import Conexion.Conexion;
import ENUM.EstadoComanda;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author katia
 */
public class ClienteFrecuenteDAO implements IClienteFrecuenteDAO{
    EntityManager em = Conexion.crearConexion();
    
    @Override
    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente cliente) throws PersistenciaException{
        try{
            if (em == null || !em.isOpen()){
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            obtenerGastoTotalAcumulado(cliente);
            obtenerConteoVisitas(cliente);
            obtenerPuntos(cliente);
            
            return cliente;
        } catch(Exception e){
            em.getTransaction().rollback();
            throw new PersistenciaException("No se pudo registrar el cliente.");
            
        } finally{
            em.close(); 
            Conexion.cerrarConexion();
        }
    }
    
    @Override
    public List<ClienteFrecuente> obtenerTodos() throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            List<ClienteFrecuente> clientes = em.createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente.class).getResultList();
            //return em.createQuery("SELECT c FROM ClienteFrecuente c", ClienteFrecuente.class).getResultList();
            for (ClienteFrecuente cliente: clientes){
                obtenerGastoTotalAcumulado(cliente);
                obtenerConteoVisitas(cliente);
                obtenerPuntos(cliente);
            }
            return clientes;
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron obtener los clientes frecuentes.");
        } finally {
            em.close();
            Conexion.cerrarConexion();
        }
    }
    
    @Override
    public List<ClienteFrecuente> filtrarClientesFrecuentes(String nombre, String telefono, String correo) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }

            String consulta = "SELECT c FROM ClienteFrecuente c WHERE 1=1";

            if (nombre != null && !nombre.trim().isEmpty()) {
                consulta += " AND (LOWER(c.nombre) LIKE LOWER(:nombre) OR LOWER(c.apellidoP) LIKE LOWER(:nombre) OR LOWER(c.apellidoM) LIKE LOWER(:nombre))";
            }
            if (telefono != null && !telefono.trim().isEmpty()) {
                consulta += " AND c.telefono = :telefono";
            }
            if (correo != null && !correo.trim().isEmpty()) {
                consulta += " AND c.correo = :correo";
            }

            TypedQuery<ClienteFrecuente> query = em.createQuery(consulta, ClienteFrecuente.class);

            if (nombre != null && !nombre.trim().isEmpty()) {
                query.setParameter("nombre", "%" + nombre.trim() + "%");
            }
            if (telefono != null && !telefono.trim().isEmpty()) {
                query.setParameter("telefono", telefono.trim());
            }
            if (correo != null && !correo.trim().isEmpty()) {
                query.setParameter("correo", correo.trim());
            }
            
            List<ClienteFrecuente> clientesFrecuentes = query.getResultList();
            
            for (ClienteFrecuente cliente : clientesFrecuentes){
                obtenerGastoTotalAcumulado(cliente);
                obtenerConteoVisitas(cliente);
                obtenerPuntos(cliente);
            }
            return clientesFrecuentes;
            
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo realizar la búsqueda de clientes frecuentes.");
        } finally {
            em.close();
            Conexion.cerrarConexion();
        }
    }
    
//    @Override
//    public void puntosVisitasGasto(ClienteFrecuente cliente) {
//        List<Comanda> comandas = cliente.getComandas();
//        Double totalGasto = 0.0;
//        int conteoVisitas = 0;
//
//        for (Comanda comanda : comandas) {
//            if (comanda.getEstado() == EstadoComanda.Entregado) {
//                totalGasto += comanda.getTotalVenta();
//                conteoVisitas++;
//            }
//        }
//
//        cliente.setGastoTotalAcumulado(totalGasto);
//        cliente.setConteoVisitas(conteoVisitas);
//        cliente.setPuntos((int) (totalGasto / 20)); 
//    }

    @Override
    public Double obtenerGastoTotalAcumulado(ClienteFrecuente cliente){
        List<Comanda> comandas = cliente.getComandas();
        Double total = 0.0;
        for (Comanda comanda : comandas){
            if (comanda.getEstado() == EstadoComanda.Entregado){
                total += comanda.getTotalVenta();
            }
        }
        cliente.setGastoTotalAcumulado(total);
        return total;
    }
    
    @Override
    public Integer obtenerConteoVisitas(ClienteFrecuente cliente){
        List<Comanda> comandas = cliente.getComandas();
        int conteo = 0;
        for (Comanda comanda: comandas){
            if (comanda.getEstado() == EstadoComanda.Entregado){
                conteo++;
            }
        }
        cliente.setConteoVisitas(conteo);
        return conteo;
    }
    
    @Override
    public Integer obtenerPuntos(ClienteFrecuente cliente){
        Double totalGasto = obtenerGastoTotalAcumulado(cliente);
        cliente.setGastoTotalAcumulado(totalGasto);
        return (int)(totalGasto/20);
    }
    
    
    @Override
    public ClienteFrecuente buscarPorId(Long id) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            return em.find(ClienteFrecuente.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo buscar el cliente por ID.");
        } finally {
            em.close();
            Conexion.cerrarConexion();
        }
    }
}
