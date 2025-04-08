/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import Entidades.Ingrediente;
import Excepciones.PersistenciaException;
import IDAOs.IIngredienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author jorge
 */
public class IngredienteDAO implements IIngredienteDAO {

     EntityManager em = Conexion.crearConexion();

    @Override
    public Ingrediente registrarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(ingrediente);
            em.getTransaction().commit();
            em.refresh(ingrediente);
            return ingrediente;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo registrar el ingrediente.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Ingrediente> obtenerIngredientes() throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            return em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron obtener los ingredientes.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Ingrediente buscarNombre(String nombre) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.nombre = :nombre", Ingrediente.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo encontrar el ingrediente con el nombre proporcionado.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Ingrediente buscarPorUnidad(Enum unidadMedida) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i WHERE i.unidadMedida = :unidad", Ingrediente.class);
            query.setParameter("unidad", unidadMedida);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo encontrar el ingrediente con la unidad de medida proporcionada.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Ingrediente eliminarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.remove(em.contains(ingrediente) ? ingrediente : em.merge(ingrediente));
            em.getTransaction().commit();
            return ingrediente;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo eliminar el ingrediente.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Ingrediente editarIngrediente(Ingrediente ingrediente) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            Ingrediente ingredienteActualizado = em.merge(ingrediente);
            em.getTransaction().commit();
            return ingredienteActualizado;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se pudo editar el ingrediente.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public List<Ingrediente> obtenerIngredientesPorID() throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            return em.createQuery("SELECT i FROM Ingrediente i ORDER BY i.id", Ingrediente.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron obtener los ingredientes por ID.");
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}