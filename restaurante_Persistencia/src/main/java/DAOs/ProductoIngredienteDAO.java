package DAOs;

import Conexion.Conexion;
import Entidades.ProductoIngrediente;
import Excepciones.PersistenciaException;
import IDAOs.IProductoIngredienteDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class ProductoIngredienteDAO implements IProductoIngredienteDAO {

    private EntityManager em = Conexion.crearConexion();

    @Override
    public ProductoIngrediente registrar(ProductoIngrediente pi) throws PersistenciaException {
        try {
            em.getTransaction().begin();
            em.persist(pi);
            em.getTransaction().commit();
            return pi;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al registrar ProductoIngrediente: " + e.getMessage());
        }
    }

    @Override
    public ProductoIngrediente editar(ProductoIngrediente pi) throws PersistenciaException {
        try {
            em.getTransaction().begin();
            ProductoIngrediente actualizado = em.merge(pi);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al editar ProductoIngrediente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long idProductoIngrediente) throws PersistenciaException {
        try {
            em.getTransaction().begin();
            ProductoIngrediente pi = em.find(ProductoIngrediente.class, idProductoIngrediente);
            if (pi != null) {
                em.remove(pi);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersistenciaException("Error al eliminar ProductoIngrediente: " + e.getMessage());
        }
    }

    @Override
    public List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws PersistenciaException {
        try {
            TypedQuery<ProductoIngrediente> query = em.createQuery(
                    "SELECT pi FROM ProductoIngrediente pi WHERE pi.producto.id = :idProducto",
                    ProductoIngrediente.class
            );
            query.setParameter("idProducto", idProducto);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener ingredientes del producto: " + e.getMessage());
        }
    }

    @Override
    public ProductoIngrediente obtenerPorProductoEIngrediente(Long idProducto, Long idIngrediente) throws PersistenciaException {
        try {
            TypedQuery<ProductoIngrediente> query = em.createQuery(
                    "SELECT pi FROM ProductoIngrediente pi WHERE pi.producto.id = :idProducto AND pi.ingrediente.id = :idIngrediente",
                    ProductoIngrediente.class
            );
            query.setParameter("idProducto", idProducto);
            query.setParameter("idIngrediente", idIngrediente);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("No se encontró la relación Producto-Ingrendiente: " + e.getMessage());
        }
    }

    @Override
    public boolean existeRelacion(Long idProducto, Long idIngrediente) throws PersistenciaException {
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(pi) FROM ProductoIngrediente pi WHERE pi.producto.id = :idProducto AND pi.ingrediente.id = :idIngrediente",
                    Long.class
            );
            query.setParameter("idProducto", idProducto);
            query.setParameter("idIngrediente", idIngrediente);
            return query.getSingleResult() > 0;
        } catch (Exception e) {
            throw new PersistenciaException("Error al verificar la existencia de la relación: " + e.getMessage());
        }
    }

    @Override
    public List<ProductoIngrediente> obtenerTodos() throws PersistenciaException {
        try {
            return em.createQuery("SELECT pi FROM ProductoIngrediente pi", ProductoIngrediente.class).getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener todos los ProductoIngrediente: " + e.getMessage());
        }
    }
}
