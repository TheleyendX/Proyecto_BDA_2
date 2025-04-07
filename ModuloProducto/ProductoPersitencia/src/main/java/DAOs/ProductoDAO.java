/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Conexion.Conexion;
import DTOs.ProductoDTO;
import Entidades.Ingrediente;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import Exception.PersitenciaException;
import Interafaces.IProductoDAO;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author mmax2
 */
public class ProductoDAO implements IProductoDAO {
    
    public static ProductoDAO instanceProductoDAO;
    
    public static ProductoDAO getInstanceDAO (){
        if (instanceProductoDAO == null){
            instanceProductoDAO = new ProductoDAO();
        }
        return instanceProductoDAO;
    }

    @Override
    public ProductoIngrediente getIngredientesNecesarios(Long id) throws PersitenciaException {
        
        return null;
        
    }

    @Override
    public Producto editarProducto(Producto producto) throws PersitenciaException {
        EntityManager em = Conexion.crearConexion();
        try{
            return em.createQuery("UPDATE "))
        }
    }

    @Override
    /**
     * Este metodo se encarga de registrar un porducto en la base de datos
     * 
     * @return El producto registrado en la base de datos
     * @throws PersteneciaException si ocurre un error durante la transaccion
     */
    public Producto registrarProducto(Producto producto) throws PersitenciaException {
        EntityManager em = Conexion.crearConexion();
        try{
            em.getTransaction().begin();
            
            em.persist(producto);
            
            em.getTransaction().commit();
            
            if(producto.getId() == null){
                throw new PersitenciaException("Error, no se genero un id para el producto");
            }
            return producto;
            
        }catch (Exception e) {
            em.getTransaction().rollback();
            throw new PersitenciaException("No se pudo registrar el producto");
        }finally{
            em.close();
        }
    }

    

    @Override
    public void quitarIngrediente(Ingrediente ingrediente) throws PersitenciaException {    }

    @Override
    public void agregarIngrediente(Ingrediente ingrediente, Double cantidad) throws PersitenciaException {
    }
    
}
