package BO;

import DTOs.ProductoDTO;

import ENUM.EstadoProducto;
import ENUM.TipoProducto;
import Entidades.Ingrediente;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import IDAOs.IProductoDAO;
import Interfaces.IProductoBO;
import Mapper.ProductoMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductoBO implements IProductoBO {

    private final IProductoDAO productoDAO;
    private final ProductoMapper mapper;

    public ProductoBO(IProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
        this.mapper = new ProductoMapper();
    }

    @Override
    public ProductoIngrediente getIngredientesNecesarios(Long id) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("ID de producto inválido.");
        }
        try {
            return productoDAO.getIngredientesNecesarios(id);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ProductoDTO editarProducto(ProductoDTO p) throws NegocioException {
        if (p == null) {
            throw new NegocioException("El producto no puede ser nulo.");
        }
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new NegocioException("El nombre del producto es obligatorio.");
        }
        if (p.getPrecio() == null || p.getPrecio() <= 0) {
            throw new NegocioException("El precio debe ser mayor a cero.");
        }
        if (p.getTipoProducto() == null) {
            throw new NegocioException("El tipo de producto es obligatorio.");
        }
        if (p.getEstadoProducto() == null) {
            throw new NegocioException("El estado del producto es obligatorio.");
        }

        Producto producto = mapper.toEntity(p);
        Producto actualizado = null;
        try {
            actualizado = productoDAO.editarProducto(producto);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapper.toDTO(actualizado);
    }

    @Override
    public ProductoDTO registraProducto(String nombre, TipoProducto tipo, EstadoProducto estado, Double precio) throws NegocioException {
        if (nombre == null || nombre.isBlank()) {
            throw new NegocioException("El nombre del producto es obligatorio.");
        }
        if (precio == null || precio <= 0) {
            throw new NegocioException("El precio debe ser mayor a cero.");
        }
        if (tipo == null) {
            throw new NegocioException("El tipo de producto es obligatorio.");
        }
        if (estado == null) {
            throw new NegocioException("El estado del producto es obligatorio.");
        }

        Producto nuevo = null;
        try {
            nuevo = productoDAO.registraProducto(nombre, tipo, estado, precio);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mapper.toDTO(nuevo);
    }

    @Override
    public void quitarIngrediente(Long id, String nombre) throws NegocioException {
        if (id == null || id <= 0) {
            throw new NegocioException("ID de producto inválido.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new NegocioException("El nombre del ingrediente es obligatorio.");
        }

        try {
            productoDAO.quitarIngrediente(id, nombre);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void agregarIngrediente(Long idProducto, Ingrediente ingrediente, Double cantidad) throws NegocioException {
        if (idProducto == null || idProducto <= 0) {
            throw new NegocioException("ID de producto inválido.");
        }
        if (ingrediente == null) {
            throw new NegocioException("Ingrediente inválido.");
        }
        if (cantidad == null || cantidad <= 0) {
            throw new NegocioException("La cantidad debe ser mayor a cero.");
        }

        try {
            productoDAO.agregarIngrediente(idProducto, ingrediente, cantidad);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public EstadoProducto ModificarEstado(Long idProducto, EstadoProducto estado) throws NegocioException {
        if (idProducto == null || idProducto <= 0) {
            throw new NegocioException("ID de producto inválido.");
        }
        if (estado == null) {
            throw new NegocioException("El nuevo estado es obligatorio.");
        }

        try {
            return productoDAO.ModificarEstado(idProducto, estado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<ProductoDTO> buscaProducto(TipoProducto tipo, String nombre) throws NegocioException {
        if ((tipo == null) && (nombre == null || nombre.isBlank())) {
            throw new NegocioException("Debe proporcionar al menos un criterio de búsqueda.");
        }

        List<Producto> encontrados = null;
        try {
            encontrados = productoDAO.buscaProducto(tipo, nombre);
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return encontrados.stream().map(mapper::toDTO).toList();
    }

   
}
