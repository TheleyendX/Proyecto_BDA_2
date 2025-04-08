package Mapper;

import DTOs.DetallesComandaDTO;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Producto;

public class DetallesComandaMapper {

    private final ProductoMapper productoMapper = new ProductoMapper();
    private final ComandaMapper comandaMapper = new ComandaMapper();

    public DetallesComanda toEntity(DetallesComandaDTO dt) {
        if (dt == null) {
            return null;
        }

        // Corregir la sintaxis en el retorno
        return new DetallesComanda(
            dt.getPrecioPorPorducto(),
            dt.getComentarios(),
            dt.getCantidadRequerida(),
            comandaMapper.toEntity(dt.getComanda()),
            dt.getImporteTotal(),
            productoMapper.toEntity(dt.getProducto())
        );
    }

    public DetallesComandaDTO toDTO(DetallesComanda dt) {
        if (dt == null) {
            return null;
        }

        // Corregir la sintaxis en el retorno
        return new DetallesComandaDTO(
            dt.getPrecioPorProducto(),
            dt.getComentarios(),
            dt.getCantidadRequerida(),
            dt.getImporteTotal(),
            productoMapper.toDTO(dt.getProducto()),
            comandaMapper.toDTO(dt.getComanda())
        );
    }
}
