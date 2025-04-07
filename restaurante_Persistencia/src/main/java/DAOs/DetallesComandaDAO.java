/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import ENUM.EstadoProducto;
import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import java.util.ArrayList;
import java.util.List;
import IDAOs.IDetallesComandaDAO;

/**
 *
 * @author mmax2
 */
public class DetallesComandaDAO implements IDetallesComandaDAO {

    @Override
    public List<DetallesComanda> registraDetalles(Comanda comanda, List<Producto> productos, List<Integer> cantidades, List<Double> precios, List<String> comentarios) throws PersistenciaException {
        List<DetallesComanda> Ldetalles = new ArrayList<>();

        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);

            if (producto.getEstado() != EstadoProducto.Disponible) {
                throw new PersistenciaException("El producto" + producto.getNombre()
                        + " No se encuentra activo actualmente");
            }
            Integer cantidad = cantidades.get(i);
            Double precio = precios.get(i);
            String comentario = comentarios.get(i);

            Double total = cantidad * precio;

            DetallesComanda detalle = new DetallesComanda();
            detalle.setProducto(producto);
            detalle.setCantidadRequerida(cantidad);
            detalle.setPrecioPorProducto(precio);
            detalle.setComentarios(comentario);
            detalle.setImporteTotal(total);
            detalle.setComanda(comanda);

            Ldetalles.add(detalle);
        }
        return Ldetalles;
    }

}
