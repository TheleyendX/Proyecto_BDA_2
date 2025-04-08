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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Producto editarProducto(Producto p) throws PersitenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            if (nombre == null || nombre.trim().isEmpty()){
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
        }catch (Exception e){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
        
    }

    @Override
    public void quitarIngrediente(Ingrediente ingrediente) throws PersitenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarIngrediente(Ingrediente ingrediente, Double cantidad) throws PersitenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EstadoProducto ModificarEstado(EstadoProducto estado) throws PersitenciaException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
