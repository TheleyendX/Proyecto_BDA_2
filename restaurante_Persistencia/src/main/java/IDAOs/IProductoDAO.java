/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.ProductoDTO;
import ENUM.EstadoProducto;
import ENUM.TipoProducto;
import Entidades.Ingrediente;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoDAO {
    public ProductoIngrediente getIngredientesNecesarios (Long id) throws PersistenciaException;
    
    public Producto editarProducto (Producto p) throws PersistenciaException;
    
    public Producto registraProducto(String nombre, TipoProducto tipo, EstadoProducto estado, Double precio) throws PersistenciaException;
    
    public void quitarIngrediente ( Long id, String nombre) throws PersistenciaException;
    
    public void agregarIngrediente (Long idProducto, Ingrediente ingrediente, Double cantidad) throws PersistenciaException;
    
    public EstadoProducto ModificarEstado (Long idProducto, EstadoProducto estado) throws PersistenciaException;
    
    public List<Producto> buscaProducto(TipoProducto tipo, String nombre)throws PersistenciaException;
}
