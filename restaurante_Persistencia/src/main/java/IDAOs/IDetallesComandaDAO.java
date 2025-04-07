/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.Comanda;
import Entidades.DetallesComanda;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IDetallesComandaDAO {
    public List<DetallesComanda> registraDetalles (Comanda comanda, List<Producto> productos,
            List<Integer> cantidades, List<Double> precios, List<String> comentarios) throws PersistenciaException;
    
    
}
