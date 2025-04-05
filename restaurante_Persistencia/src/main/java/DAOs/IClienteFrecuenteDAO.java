/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOs;

import Entidades.ClienteFrecuente;
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
    
    public void puntosVisitasGasto(ClienteFrecuente cliente);
    
    public ClienteFrecuente buscarPorId(Long id) throws PersistenciaException;
    
}
