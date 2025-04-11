/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAOs.ProductoIngredienteDAO;
import Entidades.ProductoIngrediente;
import Excepciones.NegocioException;
import Interfaces.IProductoIngredienteBO;
import java.util.List;

/**
 *
 * @author mmax2
 */
public class ProductoIngredienteBO implements IProductoIngredienteBO{
    private final ProductoIngredienteDAO productoIngredienteDAO;

    public ProductoIngredienteBO(ProductoIngredienteDAO productoIngredienteDAO) {
        this.productoIngredienteDAO = productoIngredienteDAO;
    }

    @Override
    public ProductoIngrediente registrar(ProductoIngrediente pi) throws NegocioException {
        if (pi == null) {
            throw new NegocioException("El ProductoIngrediente no puede ser nulo.");
        }
        if (pi.getProducto() == null) {
            throw new NegocioException("El Producto asociado no puede ser nulo.");
        }
        if (pi.getIngrediente() == null) {
            throw new NegocioException("El Ingrediente asociado no puede ser nulo.");
        }
        if (pi.getCantidadRequerida() == null || pi.getCantidadRequerida() <= 0) {
            throw new NegocioException("La cantidad requerida debe ser un valor positivo.");
        }

        try {
            return productoIngredienteDAO.registrar(pi);
        } catch (Exception e) {
            throw new NegocioException("Error al registrar la relación Producto-Ingrediente: " + e.getMessage());
        }
    }

    @Override
    public ProductoIngrediente editar(ProductoIngrediente pi) throws NegocioException {
        if (pi == null) {
            throw new NegocioException("El ProductoIngrediente no puede ser nulo.");
        }
        if (pi.getProducto() == null) {
            throw new NegocioException("El Producto asociado no puede ser nulo.");
        }
        if (pi.getIngrediente() == null) {
            throw new NegocioException("El Ingrediente asociado no puede ser nulo.");
        }
        if (pi.getCantidadRequerida() == null || pi.getCantidadRequerida() <= 0) {
            throw new NegocioException("La cantidad requerida debe ser un valor positivo.");
        }

        try {
            return productoIngredienteDAO.editar(pi);
        } catch (Exception e) {
            throw new NegocioException("Error al editar la relación Producto-Ingrediente: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(Long idProductoIngrediente) throws NegocioException {
        if (idProductoIngrediente == null || idProductoIngrediente <= 0) {
            throw new NegocioException("El ID del ProductoIngrediente es inválido.");
        }

        try {
            productoIngredienteDAO.eliminar(idProductoIngrediente);
        } catch (Exception e) {
            throw new NegocioException("Error al eliminar la relación Producto-Ingrediente: " + e.getMessage());
        }
    }

    @Override
    public List<ProductoIngrediente> obtenerPorProducto(Long idProducto) throws NegocioException {
        if (idProducto == null || idProducto <= 0) {
            throw new NegocioException("El ID del Producto es inválido.");
        }

        try {
            return productoIngredienteDAO.obtenerPorProducto(idProducto);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener las relaciones Producto-Ingrediente: " + e.getMessage());
        }
    }

    @Override
    public ProductoIngrediente obtenerPorProductoEIngrediente(Long idProducto, Long idIngrediente) throws NegocioException {
        if (idProducto == null || idProducto <= 0) {
            throw new NegocioException("El ID del Producto es inválido.");
        }
        if (idIngrediente == null || idIngrediente <= 0) {
            throw new NegocioException("El ID del Ingrediente es inválido.");
        }

        try {
            return productoIngredienteDAO.obtenerPorProductoEIngrediente(idProducto, idIngrediente);
        } catch (Exception e) {
            throw new NegocioException("Error al obtener la relación Producto-Ingrediente: " + e.getMessage());
        }
    }

    @Override
    public boolean existeRelacion(Long idProducto, Long idIngrediente) throws NegocioException {
        if (idProducto == null || idProducto <= 0) {
            throw new NegocioException("El ID del Producto es inválido.");
        }
        if (idIngrediente == null || idIngrediente <= 0) {
            throw new NegocioException("El ID del Ingrediente es inválido.");
        }

        try {
            return productoIngredienteDAO.existeRelacion(idProducto, idIngrediente);
        } catch (Exception e) {
            throw new NegocioException("Error al verificar la existencia de la relación Producto-Ingrediente: " + e.getMessage());
        }
    }

    @Override
    public List<ProductoIngrediente> obtenerTodos() throws NegocioException {
        try {
            return productoIngredienteDAO.obtenerTodos();
        } catch (Exception e) {
            throw new NegocioException("Error al obtener todas las relaciones Producto-Ingrediente: " + e.getMessage());
        }
    }
}
