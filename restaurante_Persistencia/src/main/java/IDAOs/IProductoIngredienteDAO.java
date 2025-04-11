/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.ProductoIngrediente;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IProductoIngredienteDAO {

    ProductoIngrediente registrar(ProductoIngrediente pi) throws PersistenciaException;

    ProductoIngrediente editar(ProductoIngrediente pi) throws PersistenciaException;

    void eliminar(Long idProductoIngrediente) throws PersistenciaException;

    List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws PersistenciaException;

    ProductoIngrediente obtenerPorProductoEIngrediente(Long idProducto, Long idIngrediente) throws PersistenciaException;

    boolean existeRelacion(Long idProducto, Long idIngrediente) throws PersistenciaException;

    List<ProductoIngrediente> obtenerTodos() throws PersistenciaException;
}
