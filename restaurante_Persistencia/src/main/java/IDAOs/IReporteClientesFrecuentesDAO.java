/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import Entidades.ClienteFrecuente;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz que define las operaciones que se pueden realizar sobre la clase
 * ReporteClientesFrecuentesDAO.
 * @author katia
 */
public interface IReporteClientesFrecuentesDAO {

    /**
     * Obtiene una lista de clientes frecuentes filtrada por nombre y mínimo de visitas. 
     * (Los filtros son opcionales).
     * @param nombre Nombre completo o parcial del cliente que se desea obtener.
     * @param visitasMinimas Cantidad de visitas mínimas que deben tener los clientes
     * que se desea obtener.
     * @return
     * @throws PersistenciaException
     */
    public List<ClienteFrecuente> obtenerClientesFrecuentesPorFiltro(String nombre, Integer visitasMinimas) throws PersistenciaException;
    
    /**
     * Obtiene la última fecha de comanda registrada que tenga un cliente frecuente.
     * @param cliente Cliente al que se consulta su historial de comanda.s
     * @return Última fecha de comanda del cliente.
     */
    public LocalDate obtenerUltimaFechaComanda(ClienteFrecuente cliente);
}
