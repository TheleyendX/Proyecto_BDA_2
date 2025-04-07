/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interafaces;

import DTOs.ProductoDTO;
import ENUM.EstadoProducto;
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
    
    public Producto registrarProducto (Producto p) throws PersitenciaException;
    
    public void quitarIngrediente (Ingrediente ingrediente) throws PersitenciaException;
    
    public void agregarIngrediente (Ingrediente ingrediente, Double cantidad) throws PersitenciaException;
    
    public EstadoProducto ModificarEstado (EstadoProducto estado) throws PersitenciaException;
}
