/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IClienteFrecuenteDAO {
    
    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente cliente) throws PersistenciaException;
    
    public List<ClienteFrecuente> obtenerTodos() throws PersistenciaException;
    
    public List<ClienteFrecuente> filtrarClientesFrecuentes(String nombre, String telefono, String correo) throws PersistenciaException;
    
    public Double obtenerGastoTotalAcumulado(ClienteFrecuente cliente);
    
    public Integer obtenerConteoVisitas(ClienteFrecuente cliente);
    
    public Integer obtenerPuntos(ClienteFrecuente cliente);
    
    //public void puntosVisitasGasto(ClienteFrecuente cliente);
    
    public ClienteFrecuente buscarPorId(Long id) throws PersistenciaException;
    public void persistirComanda(Comanda comanda) throws PersistenciaException;
    
}
