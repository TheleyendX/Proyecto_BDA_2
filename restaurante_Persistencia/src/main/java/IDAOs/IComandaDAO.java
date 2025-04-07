/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IDAOs;

import ENUM.EstadoComanda;
import Entidades.Cliente;
import Entidades.Comanda;
import Entidades.Mesa;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author mmax2
 */
public interface IComandaDAO {
    public Comanda registraComanda (Cliente cliente, Mesa mesa, List<Producto> productos,
            List<Integer> cantidades, List<Double> precios, List<String> Comentarios) throws PersistenciaException;
    
    public Comanda actualizaEstadoComanda (Long id, EstadoComanda estado) throws PersistenciaException;
    
    public String generaFolio () throws PersistenciaException;
}
