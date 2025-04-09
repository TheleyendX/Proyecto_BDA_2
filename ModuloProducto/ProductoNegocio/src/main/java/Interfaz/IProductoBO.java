/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaz;

import DTOs.ProductoDTO;
import DTOs.ProductoIngredienteDTO;
import ENUM.EstadoProducto;
import ENUM.TipoProducto;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoBO {
    public ProductoDTO registraProducto ();
    
    public List<ProductoDTO> buscaProducto (String nombre, TipoProducto tipo);
    
    public ProductoDTO eliminaIngrediente(String nombreIngrediente);
    
    public ProductoDTO actualizaEstado (String nombre, EstadoProducto estado);
    
    
}
