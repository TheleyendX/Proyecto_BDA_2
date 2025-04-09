package Mapper;

import DTOs.ProductoDTO;
import DTOs.ProductoIngredienteDTO;
import Entidades.Producto;
import Entidades.ProductoIngrediente;
import java.util.ArrayList;
import java.util.List;

public class ProductoMapper {

    private final ProductoIngredienteMapper ingredienteMapper = new ProductoIngredienteMapper();

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;

        List<ProductoIngrediente> ingredientes = new ArrayList<>();
        if (dto.getProductoIngrediente() != null) {
            for (ProductoIngredienteDTO ingDTO : dto.getProductoIngrediente()) {
                ProductoIngrediente pi = new ProductoIngrediente();
                pi.setIngrediente(ingDTO.getIngrediente());
                pi.setCantidadRequerida(ingDTO.getCantidadRequerida());
                ingredientes.add(pi);
            }
        }

        Producto entity = new Producto(
            dto.getTipoProducto(),
            dto.getEstadoProducto(),
            dto.getNombre(),
            dto.getPrecio(),
            ingredientes
        );
        return entity;
    }

    public ProductoDTO toDTO(Producto entity) {
        if (entity == null) return null;

        List<ProductoIngredienteDTO> ingredientesDTO = new ArrayList<>();
        if (entity.getIngrediente() != null) {
            for (ProductoIngrediente pi : entity.getIngrediente()) {
                ProductoIngredienteDTO piDTO = new ProductoIngredienteDTO();
                piDTO.setIngrediente(pi.getIngrediente());
                piDTO.setCantidadRequerida(pi.getCantidadRequerida());
                ingredientesDTO.add(piDTO);
            }
        }

        ProductoDTO dto = new ProductoDTO(
            entity.getTipo(),
            entity.getEstado(),
            entity.getNombre(),
            entity.getPrecio(),
                ingredientesDTO
        );
        return dto;
    }
}
