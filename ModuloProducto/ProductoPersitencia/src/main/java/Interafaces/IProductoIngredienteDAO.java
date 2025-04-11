/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interafaces;

import Entidades.ProductoIngrediente;
import Exception.PersitenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoIngredienteDAO {

    ProductoIngrediente registrar(ProductoIngrediente pi) throws PersitenciaException;

    ProductoIngrediente editar(ProductoIngrediente pi) throws PersitenciaException;

    void eliminar(Long idProductoIngrediente) throws PersitenciaException;

    List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws PersitenciaException;

    ProductoIngrediente obtenerPorProductoEIngrediente(Long idProducto, Long idIngrediente) throws PersitenciaException;

    boolean existeRelacion(Long idProducto, Long idIngrediente) throws PersitenciaException;

    List<ProductoIngrediente> obtenerTodos() throws PersitenciaException;
}
