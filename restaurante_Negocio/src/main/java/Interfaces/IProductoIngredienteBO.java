/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaces;

import Entidades.ProductoIngrediente;
import Excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoIngredienteBO {

    public ProductoIngrediente registrar(ProductoIngrediente pi) throws NegocioException;

    public ProductoIngrediente editar(ProductoIngrediente pi) throws NegocioException;

    public void eliminar(Long idProductoIngrediente) throws NegocioException;

    public List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws NegocioException;

    public ProductoIngrediente obtenerPorProductoEIngrediente(Long idProducto, Long idIngrediente) throws NegocioException;

    boolean existeRelacion(Long idProducto, Long idIngrediente) throws NegocioException;

    public List<ProductoIngrediente> obtenerTodos() throws NegocioException;

}
