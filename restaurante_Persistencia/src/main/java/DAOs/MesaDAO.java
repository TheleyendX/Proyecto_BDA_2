/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import Entidades.Mesa;
import Excepciones.PersistenciaException;
import IDAOs.IMesaDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author mmax2
 */
public class MesaDAO implements IMesaDAO {

    EntityManager em = Conexion.crearConexion();

    /**
     * Este metodo realiza un insert de 20 mesas en la base de datos
     *
     * @return Devuelve la lista de 20 mesas
     * @throws Excepciones.PersistenciaException
     */
    @Override
    public List<Mesa> registraMesas() throws PersistenciaException {
        List<Mesa> lMesas = new ArrayList<>();
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            for (int i = 0; i < 20; i++) {
                Mesa mesa = new Mesa();
                mesa.setEstadoMesa(false);
                mesa.setNumero(i + 1);
                em.persist(mesa);
                lMesas.add(mesa);
            }
            em.getTransaction().commit();
            return lMesas;
        } finally {
            if (em != null || em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Metodo que se encarga de genera la actualizacion del estado de una mesa
     * 
     * @param id este es el numero identificador de la mesa que se desea modificar
     * @param estado es es el estado que se desea dar a la mesa
     * @throws PersistenciaException esta es una excepcion personalizada de persistencia
     */
    @Override
    public Mesa actualizaEstado(Long id, int estado) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }

            em.getTransaction().begin();

            Mesa mesa = em.find(Mesa.class, id);
            if (mesa == null) {
                throw new PersistenciaException("No se encontró la mesa con ID: " + id);
            }

            mesa.setEstadoMesa(estado == 1);
            em.merge(mesa);

            em.getTransaction().commit();

            return mesa;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("Error al actualizar estado de la mesa con ID: " + id, e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

}
