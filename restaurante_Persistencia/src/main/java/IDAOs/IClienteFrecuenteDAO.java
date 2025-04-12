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
 * Interfaz que se utilizará para la clase ClienteFrecuenteDAO.
 * Contiene métodos utilizados para persistir en la base de datos.
 * @author katia
 */
public interface IClienteFrecuenteDAO {
    
    /**
     * Método para registrar un cliente nuevo al sistema.
     * @param cliente El cliente que se desea registrar.
     * @return El cliente registrado.
     * @throws PersistenciaException por cualquier error que pueda ocurrir al
     * registrar el cliente.
     */
    public ClienteFrecuente registrarClienteFrecuente(ClienteFrecuente cliente) throws PersistenciaException;
    
    /**
     * Se encarga de obtener todos los clientes frecuentes que se encuentren
     * registrados en la base de datos.
     * @return Lista con todos los clientes.
     * @throws PersistenciaException En caso de error al obtener clientes frecuentes.
     */
    public List<ClienteFrecuente> obtenerTodos() throws PersistenciaException;
    
    /**
     * Método encargado de filtrar los clientes frecuentes según los criterios proporcionados.
     * @param nombre Nombre completo o parcial del cliente.
     * @param telefono Teléfono completo del cliente.
     * @param correo Correo completo o parcial del cliente.
     * @return Lista de clientes que coincidan con los criterios de búsqueda.
     * @throws PersistenciaException En caso de error al realizar la búsqueda.
     */
    public List<ClienteFrecuente> filtrarClientesFrecuentes(String nombre, String telefono, String correo) throws PersistenciaException;
    
    /**
     * Método que calcula el gasto total acumulado de un cliente, basado en las
     * comandas cuyo estado sea entregado.
     * @param cliente Cliente al que se le calcula su gasto.
     * @return Total de lo que ha gastado un cliente.
     */
    public Double obtenerGastoTotalAcumulado(ClienteFrecuente cliente);
    
    /**
     * Método que calcula el número de visitas que ha realizado un cliente al
     * restaurante.
     * @param cliente Cliente al que se desea calcular.
     * @return Número total de visitas.
     */
    public Integer obtenerConteoVisitas(ClienteFrecuente cliente);
    
    /**
     * Calcula los puntos acumulados por un cliente basado en su gasto total 
     * acumulado.
     * @param cliente Cliente al que se le calcularán sus puntos.
     * @return La cantidad de puntos que tenga.
     */
    public Integer obtenerPuntos(ClienteFrecuente cliente);
    
    /**
     * Busca un cliente frecuente proporcionando su id.
     * @param id ID del cliente que se desea encontrar.
     * @return Cliente encontrado.
     * @throws PersistenciaException En caso de error.
     */
    public ClienteFrecuente buscarPorId(Long id) throws PersistenciaException;

    /**
     * MÉTODO REALIZADO PARA PRUEBAS.
     * Guarda una nueva comanda en la base de datos.
     * @param comanda La comanda con toda la información que se
     * persistirá.
     * @throws PersistenciaException En caso de que ocurra un error.
     */
    public void persistirComanda(Comanda comanda) throws PersistenciaException;
    
}
