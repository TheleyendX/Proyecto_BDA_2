/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interafaces;

import DTOs.ProductoDTO;
import ENUM.EstadoProducto;
import ENUM.TipoProducto;
import Entidades.Ingrediente;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import Exception.PersitenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoDAO {
    public ProductoIngrediente getIngredientesNecesarios (Long id) throws PersitenciaException;
    
    public Producto editarProducto (Producto p) throws PersitenciaException;
    
    public Producto registraProducto(String nombre, TipoProducto tipo, EstadoProducto estado, Double precio) throws PersitenciaException;
    
    public void quitarIngrediente ( Long id, String nombre) throws PersitenciaException;
    
    public void agregarIngrediente (Long idProducto, Ingrediente ingrediente, Double cantidad) throws PersitenciaException;
    
    public EstadoProducto ModificarEstado (Long idProducto, EstadoProducto estado) throws PersitenciaException;
    
    public List<Producto> buscaProducto(TipoProducto tipo, String nombre)throws PersitenciaException;
}
