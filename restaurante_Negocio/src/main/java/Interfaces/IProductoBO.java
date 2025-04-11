/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTOs.ProductoDTO;
import DTOs.ProductoIngredienteDTO;
import ENUM.EstadoProducto;
import ENUM.TipoProducto;
import Entidades.Ingrediente;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import Excepciones.NegocioException;
import Exception.PersitenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoBO {
      public ProductoIngrediente getIngredientesNecesarios (Long id) throws NegocioException;
    
    public ProductoDTO editarProducto (ProductoDTO p) throws NegocioException;
    
    public ProductoDTO registraProducto(String nombre, TipoProducto tipo, EstadoProducto estado, Double precio) throws NegocioException;
    
    public void quitarIngrediente ( Long id, String nombre) throws NegocioException;
    
    public void agregarIngrediente (Long idProducto, Ingrediente ingrediente, Double cantidad) throws NegocioException;
    
    public EstadoProducto ModificarEstado (Long idProducto, EstadoProducto estado) throws NegocioException;
    
    public List<ProductoDTO> buscaProducto(TipoProducto tipo, String nombre)throws NegocioException;
    
    
}
