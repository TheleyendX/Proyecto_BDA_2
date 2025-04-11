/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import DTOs.ProductoDTO;
import ENUM.EstadoProducto;
import ENUM.TipoProducto;
import Encriptador.Encriptador;
import Entidades.Ingrediente;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import Exception.PersitenciaException;
import Interafaces.IProductoDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author mmax2
 */
public class ProductoDAO implements IProductoDAO {

    private EntityManager em = Conexion.crearConexion();

    public static ProductoDAO instanceProductoDAO;

    public static ProductoDAO getInstanceDAO() {
        if (instanceProductoDAO == null) {
            instanceProductoDAO = new ProductoDAO();
        }
        return instanceProductoDAO;
    }

    @Override

    public ProductoIngrediente getIngredientesNecesarios(Long id) throws PersitenciaException {
        try {
            Producto producto = em.find(Producto.class, id);
            if (producto == null) {
                throw new PersitenciaException("Producto no encontrado con ID: " + id);
            }

            // Retorna la lista de ingredientes. Si quieres todos, puedes regresar la lista completa
            // o hacer una búsqueda específica si se desea por otra condición.
            return producto.getIngrediente().isEmpty() ? null : producto.getIngrediente().get(0);
        } catch (Exception e) {
            throw new PersitenciaException("Error al obtener ingredientes: " + e.getMessage());
        }
    }

    @Override
    public Producto editarProducto(Producto p) throws PersitenciaException {
        try {
            em.getTransaction().begin();
            Producto actualizado = em.merge(p);
            em.getTransaction().commit();
            return actualizado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersitenciaException("Error al editar el producto: " + e.getMessage());
        }
    }

    /**
     * Registra un producto en la base de datos con su nombre encriptado.
     *
     * @param nombre El nombre del producto.
     * @param tipo El tipo de producto.
     * @param estado El estado del producto.
     * @param precio El precio del producto.
     * @return El producto registrado con el nombre encriptado.
     * @throws PersistenciaException Si ocurre un error al guardar el producto.
     */
    @Override
    public Producto registraProducto(String nombre, TipoProducto tipo, EstadoProducto estado, Double precio) throws PersitenciaException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new PersitenciaException("El nombre ingresado no puede estar vacio");
            }

            Producto producto = new Producto();
            producto.setNombre(nombre);  // Guardamos el nombre encriptado
            producto.setTipo(tipo);
            producto.setEstado(estado);
            producto.setPrecio(precio);

            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();

            return producto;
        } catch (Exception e) {
            em.getTransaction().rollback();
            return null;
        } finally {
            em.close();
        }

    }

    @Override
    public void quitarIngrediente(Long idProducto, String nombre) throws PersitenciaException {
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, idProducto);
            if (producto == null) {
                throw new PersitenciaException("Producto no encontrado");
            }

            List<ProductoIngrediente> lista = producto.getIngrediente();
            ProductoIngrediente objetivo = null;

            for (ProductoIngrediente pi : lista) {
                if (pi.getIngrediente().getNombre().equalsIgnoreCase(nombre)) {
                    objetivo = pi;
                    break;
                }
            }

            if (objetivo != null) {
                lista.remove(objetivo);
                em.remove(em.contains(objetivo) ? objetivo : em.merge(objetivo));
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersitenciaException("Error al quitar ingrediente: " + e.getMessage());
        }
    }

    @Override
    public void agregarIngrediente(Long idProducto, Ingrediente ingrediente, Double cantidad) throws PersitenciaException {
        try {
            em.getTransaction().begin();

            Producto producto = em.find(Producto.class, idProducto);
            if (producto == null) {
                throw new PersitenciaException("Producto no encontrado");
            }

            ProductoIngrediente nuevo = new ProductoIngrediente(producto, ingrediente, cantidad);
            em.persist(nuevo);

            producto.getIngrediente().add(nuevo);
            em.merge(producto);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersitenciaException("Error al agregar ingrediente: " + e.getMessage());
        }
    }

    @Override
    public EstadoProducto ModificarEstado(Long idProducto, EstadoProducto estado) throws PersitenciaException {
        try {
            em.getTransaction().begin();
            Producto producto = em.find(Producto.class, idProducto);
            if (producto == null) {
                throw new PersitenciaException("Producto no encontrado");
            }

            producto.setEstado(estado);
            em.merge(producto);
            em.getTransaction().commit();
            return estado;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersitenciaException("Error al modificar estado: " + e.getMessage());
        }
    }

    @Override
    public List<Producto> buscaProducto(TipoProducto tipo, String nombre) throws PersitenciaException {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> query = cb.createQuery(Producto.class);
        Root<Producto> producto = query.from(Producto.class);

        Predicate predicate = cb.conjunction();

        if (tipo != null) {
            predicate = cb.and(predicate, cb.equal(producto.get("tipo"), tipo));
        }

        if (nombre != null) {
            predicate = cb.and(predicate, cb.like(cb.lower(producto.get("nombre")), "%" + nombre.toLowerCase() + "%"));
        }

        query.where(predicate);

        return em.createQuery(query).getResultList();
    }
}
