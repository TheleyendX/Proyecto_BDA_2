/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package BO;

import Conexion.Conexion;
import DAOs.ReporteClientesFrecuentesDAO;
import DTOs.ClienteFrecuenteDTO;
import ENUM.EstadoComanda;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.ClienteFrecuenteMapper;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katia
 */
public class ReporteClientesFrecuentesBOTest {
    
    private ReporteClientesFrecuentesBO reporteClientesBO;
    private ReporteClientesFrecuentesDAO reporteClientesDAO;
    private ClienteFrecuenteMapper clienteFMapper;

    @BeforeEach
    public void setUp() {
        reporteClientesDAO = new ReporteClientesFrecuentesDAO();  
        clienteFMapper = new ClienteFrecuenteMapper(); 
        reporteClientesBO = new ReporteClientesFrecuentesBO();
    }

    @Test
    public void testObtenerClientesFrecuentesPorFiltroNombre() throws PersistenciaException, NegocioException {
        // Datos de prueba
        ClienteFrecuente cliente1 = new ClienteFrecuente();
        cliente1.setNombre("Carlos");
        cliente1.setApellidoP("Ramírez");
        cliente1.setCorreo("carlos.ramirez@example.com");
        cliente1.setTelefono("1231231230");
        reporteClientesDAO.registrarClienteFrecuente(cliente1);

        ClienteFrecuente cliente2 = new ClienteFrecuente();
        cliente2.setNombre("Marco");
        cliente2.setApellidoP("Polo");
        cliente2.setCorreo("marco.polo@example.com");
        cliente2.setTelefono("3213213210");
        reporteClientesDAO.registrarClienteFrecuente(cliente2);

        // Llamamos al método de BO con filtro por nombre
        List<ClienteFrecuenteDTO> clientesFiltrados = reporteClientesBO.obtenerClientesFrecuentesPorFiltro("Carlos", null);

        // Verificamos que solo aparezca el cliente con nombre "Carlos"
        assertNotNull(clientesFiltrados, "La lista de clientes no debe ser nula.");
        assertEquals(1, clientesFiltrados.size(), "Debe haber un cliente llamado 'Carlos'.");
        assertEquals("Carlos", clientesFiltrados.get(0).getNombre(), "El nombre debe ser 'Carlos'.");
    }

    @Test
    public void testObtenerClientesFrecuentesPorFiltroVisitasMinimas() throws PersistenciaException, NegocioException {
        // Datos de prueba
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Laura");
        cliente.setApellidoP("Martínez");
        cliente.setCorreo("laura.martinez@example.com");
        cliente.setTelefono("4564564560");
        reporteClientesDAO.registrarClienteFrecuente(cliente);

        // Registramos varias comandas para el cliente
        for (int i = 0; i < 10; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setTotalVenta(800.00);
            
            String folio = "CM" + (i + 1);
            comanda.setFolio(folio);
            reporteClientesDAO.persistirComanda(comanda);
        }

        // Llamamos al método de BO con filtro por visitas mínimas
        List<ClienteFrecuenteDTO> clientesFiltrados = reporteClientesBO.obtenerClientesFrecuentesPorFiltro(null, 5);

        // Verificamos que el cliente con más de 5 visitas sea retornado
        assertNotNull(clientesFiltrados, "La lista de clientes no debe ser nula.");
        assertTrue(clientesFiltrados.size() >= 1, "Debe haber al menos un cliente con más de 5 visitas.");
    }

    @Test
    public void testObtenerClientesFrecuentesPorFiltroConVisitasYNombre() throws PersistenciaException, NegocioException {
        // Datos de prueba
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Marta");
        cliente.setApellidoP("González");
        cliente.setCorreo("marta.gonzalez@example.com");
        cliente.setTelefono("6546546540");
        reporteClientesDAO.registrarClienteFrecuente(cliente);

        // Registramos varias comandas para el cliente
        for (int i = 0; i < 15; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setTotalVenta(800.00);
            
            String folio = "CMND" + (i + 1);
            comanda.setFolio(folio);
            reporteClientesDAO.persistirComanda(comanda);
        }

        // Llamamos al método de BO con filtro por nombre y visitas mínimas
        List<ClienteFrecuenteDTO> clientesFiltrados = reporteClientesBO.obtenerClientesFrecuentesPorFiltro("Marta González", 10);

        // Verificamos que el cliente "Marta González" con más de 10 visitas sea retornado
        assertNotNull(clientesFiltrados, "La lista de clientes no debe ser nula.");
        assertEquals(1, clientesFiltrados.size(), "Debe haber un cliente llamado 'Marta González' con más de 10 visitas.");
        assertEquals("Marta González", clientesFiltrados.get(0).getNombreCompleto(), "El nombre debe ser 'Marta González'.");
    }

//    @Test
//    public void testObtenerTodosLosClientesFrecuentes() throws PersistenciaException, NegocioException {
//        // Datos de prueba
//        ClienteFrecuente cliente1 = new ClienteFrecuente();
//        cliente1.setNombre("Juan");
//        cliente1.setApellidoP("Pérez");
//        cliente1.setCorreo("juan.perez@example.com");
//        cliente1.setTelefono("1234567890");
//        reporteClientesDAO.registrarClienteFrecuente(cliente1);
//
//        ClienteFrecuente cliente2 = new ClienteFrecuente();
//        cliente2.setNombre("Ana");
//        cliente2.setApellidoP("Gómez");
//        cliente2.setCorreo("ana.gomez@example.com");
//        cliente2.setTelefono("9876543210");
//        reporteClientesDAO.registrarClienteFrecuente(cliente2);
//
//        // Llamamos al método de BO sin filtros
//        List<ClienteFrecuenteDTO> clientes = reporteClientesBO.obtenerTodosLosClientesFrecuentes();
//
//        // Verificamos que ambos clientes sean retornados
//        assertNotNull(clientes, "La lista de clientes no debe ser nula.");
//        assertEquals(2, clientes.size(), "Debe haber al menos dos clientes.");
//        assertTrue(clientes.stream().anyMatch(c -> c.getNombre().equals("Juan")), "Debe haber un cliente llamado 'Juan'.");
//        assertTrue(clientes.stream().anyMatch(c -> c.getNombre().equals("Ana")), "Debe haber un cliente llamado 'Ana'.");
//    }
}
