/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOs;

import ENUM.EstadoComanda;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katia
 */
public class ReporteClientesFrecuentesDAOTest {
    
    private ReporteClientesFrecuentesDAO reporteClientesFrecuentesDAO;

    @BeforeEach
    public void setUp() {
        reporteClientesFrecuentesDAO = new ReporteClientesFrecuentesDAO();
    }
    
    @Test
    public void testObtenerClientesSinFiltros() throws PersistenciaException {
        ClienteFrecuente cliente1 = new ClienteFrecuente();
        cliente1.setNombre("Juan");
        cliente1.setApellidoP("Pérez");
        cliente1.setCorreo("juan.perez@example.com");
        cliente1.setTelefono("1234567890");
        reporteClientesFrecuentesDAO.registrarClienteFrecuente(cliente1);

        ClienteFrecuente cliente2 = new ClienteFrecuente();
        cliente2.setNombre("Ana");
        cliente2.setApellidoP("Gómez");
        cliente2.setCorreo("ana.gomez@example.com");
        cliente2.setTelefono("9876543210");
        reporteClientesFrecuentesDAO.registrarClienteFrecuente(cliente2);

        List<ClienteFrecuente> clientes = reporteClientesFrecuentesDAO.obtenerClientesFrecuentesPorFiltro(null, null);

        assertNotNull(clientes, "La lista no debe ser nula.");
        assertTrue(clientes.size() >= 2, "Debe haber al menos dos clientes.");
    }

    @Test
    public void testObtenerClientesPorNombre() throws PersistenciaException {
        ClienteFrecuente cliente1 = new ClienteFrecuente();
        cliente1.setNombre("Carlos");
        cliente1.setApellidoP("Ramírez");
        cliente1.setApellidoM("Sánchez");
        cliente1.setCorreo("carlos.ramirez@example.com");
        cliente1.setTelefono("1231231230");
        reporteClientesFrecuentesDAO.registrarClienteFrecuente(cliente1);

        ClienteFrecuente cliente2 = new ClienteFrecuente();
        cliente2.setNombre("Marco");
        cliente2.setApellidoP("Polo");
        cliente2.setCorreo("marco.polo@example.com");
        cliente2.setTelefono("3213213210");
        reporteClientesFrecuentesDAO.registrarClienteFrecuente(cliente2);

        List<ClienteFrecuente> clientesFiltrados = reporteClientesFrecuentesDAO.obtenerClientesFrecuentesPorFiltro("Carlos", null);

        assertNotNull(clientesFiltrados, "La lista de clientes filtrados no debe ser nula.");
        assertEquals(1, clientesFiltrados.size(), "Debe haber un cliente llamado 'Carlos'.");

        clientesFiltrados = reporteClientesFrecuentesDAO.obtenerClientesFrecuentesPorFiltro("Carlos Ramírez", null);

        assertNotNull(clientesFiltrados, "La lista de clientes filtrados no debe ser nula.");
        assertEquals(1, clientesFiltrados.size(), "Debe haber un cliente llamado 'Carlos Ramírez'.");
    }

    @Test
    public void testObtenerClientesPorVisitasMinimas() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Laura");
        cliente.setApellidoP("Martínez");
        cliente.setCorreo("laura.martinez@example.com");
        cliente.setTelefono("4564564560");

        reporteClientesFrecuentesDAO.registrarClienteFrecuente(cliente);
        for (int i = 0; i < 10; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setTotalVenta(800.00);
            
            String folio = "CM" + (i+1);
            comanda.setFolio(folio);
            
            reporteClientesFrecuentesDAO.persistirComanda(comanda);
        }
        Integer conteoVisitas = reporteClientesFrecuentesDAO.obtenerConteoVisitas(cliente);
        cliente.setConteoVisitas(conteoVisitas);
        
        List<ClienteFrecuente> clientesFiltrados = reporteClientesFrecuentesDAO.obtenerClientesFrecuentesPorFiltro(null, 5);

        assertNotNull(clientesFiltrados, "La lista de clientes filtrados no debe ser nula.");
        assertTrue(clientesFiltrados.size() >= 1, "Debe haber al menos un cliente con más de 5 visitas.");
    }

    @Test
    public void testObtenerClientesPorNombreYVisitas() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Marta");
        cliente.setApellidoP("González");
        cliente.setCorreo("marta.gonzalez@example.com");
        cliente.setTelefono("6546546540");
        //cliente.setConteoVisitas(15); 
        reporteClientesFrecuentesDAO.registrarClienteFrecuente(cliente);
        for (int i = 0; i < 15; i++) {
            Comanda comanda = new Comanda();
            comanda.setCliente(cliente);
            comanda.setEstado(EstadoComanda.Entregado);
            comanda.setFechaHora(LocalDateTime.now());
            comanda.setTotalVenta(800.00);
            
            String folio = "CMND" + (i+1);
            comanda.setFolio(folio);
            
            reporteClientesFrecuentesDAO.persistirComanda(comanda);
        }
        Integer conteoVisitas = reporteClientesFrecuentesDAO.obtenerConteoVisitas(cliente);
        cliente.setConteoVisitas(conteoVisitas);
        
        List<ClienteFrecuente> clientesFiltrados = reporteClientesFrecuentesDAO.obtenerClientesFrecuentesPorFiltro("Marta González", 10);

        assertNotNull(clientesFiltrados, "La lista de clientes filtrados no debe ser nula.");
        assertEquals(1, clientesFiltrados.size(), "Debe haber un cliente llamado 'Marta González' con más de 10 visitas.");
    }

    @Test
    public void testManejoDeExcepcion() throws PersistenciaException {
        ReporteClientesFrecuentesDAO reporteDAOConError = new ReporteClientesFrecuentesDAO() {
            @Override
            public List<ClienteFrecuente> obtenerClientesFrecuentesPorFiltro(String nombre, Integer visitasMinimas) throws PersistenciaException {
                throw new PersistenciaException("Error en la base de datos");
            }
        };
        assertThrows(PersistenciaException.class, () -> {
            reporteDAOConError.obtenerClientesFrecuentesPorFiltro(null, null);
        });
    }
}
