/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAOs;

import ENUM.EstadoComanda;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class ClienteFrecuenteDAOTest {
    
    private ClienteFrecuenteDAO clienteFrecuenteDAO;

    @BeforeEach
    public void setUp() {
        clienteFrecuenteDAO = new ClienteFrecuenteDAO();
    }

    @Test
    public void testRegistrarCliente1() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Juan");
        cliente.setApellidoP("Pérez");
        cliente.setApellidoM("González");
        cliente.setCorreo("juanperez@gmail.com");
        cliente.setTelefono("123456789");
        cliente.setFechaRegistro(LocalDate.now());

        cliente = clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        assertNotNull(cliente.getId(), "El cliente 1 no fue registrado correctamente.");
    }

    @Test
    public void testAsociarComandas() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Ana");
        cliente.setApellidoP("Soto");
        cliente.setApellidoM("Lopez");
        cliente.setCorreo("ana@gmail.com");
        cliente.setTelefono("111222333");
        cliente = clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        Comanda c1 = new Comanda();
        c1.setCliente(cliente);
        c1.setEstado(EstadoComanda.Entregado);
        c1.setFechaHora(LocalDateTime.now());
        c1.setTotalVenta(80.0);
        c1.setFolio("CMD-A1");

        Comanda c2 = new Comanda();
        c2.setCliente(cliente);
        c2.setEstado(EstadoComanda.Entregado);
        c2.setFechaHora(LocalDateTime.now());
        c2.setTotalVenta(120.0);
        c2.setFolio("CMD-A2");

        clienteFrecuenteDAO.persistirComanda(c1);
        clienteFrecuenteDAO.persistirComanda(c2);

        assertNotNull(c1.getFolio());
        assertNotNull(c2.getFolio());
    }

    @Test
    public void testObtenerInformacionCliente() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Juanita");
        cliente.setApellidoP("Espinoza");
        cliente.setApellidoM("Vega");
        cliente.setCorreo("juanita@gmail.com");
        cliente.setTelefono("444555666");
        cliente = clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        Comanda c1 = new Comanda();
        c1.setCliente(cliente);
        c1.setEstado(EstadoComanda.Entregado);
        c1.setFechaHora(LocalDateTime.now());
        c1.setTotalVenta(200.0);
        c1.setFolio("CMD-L1");

        clienteFrecuenteDAO.persistirComanda(c1);

        Double gasto = clienteFrecuenteDAO.obtenerGastoTotalAcumulado(cliente);
        Integer visitas = clienteFrecuenteDAO.obtenerConteoVisitas(cliente);
        Integer puntos = clienteFrecuenteDAO.obtenerPuntos(cliente);

        assertEquals(200.0, gasto);
        assertEquals(1, visitas);
        assertEquals(10, puntos); // 200 / 20
    }

    @Test
    public void testObtenerTodosLosClientes() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Pedro");
        cliente.setApellidoP("Hernández");
        cliente.setApellidoM("Ruiz");
        cliente.setCorreo("pedro@gmail.com");
        cliente.setTelefono("999888777");
        cliente = clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        List<ClienteFrecuente> lista = clienteFrecuenteDAO.obtenerTodos();
        assertNotNull(lista);
        assertTrue(lista.size() > 0, "Debe haber al menos un cliente.");
    }

    @Test
    public void testFiltrarClientesPorNombre() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Marco");
        cliente.setApellidoP("Polo");
        cliente.setApellidoM("Sánchez");
        cliente.setCorreo("marco@gmail.com");
        cliente.setTelefono("1010101010");
        cliente = clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        List<ClienteFrecuente> filtrados = clienteFrecuenteDAO.filtrarClientesFrecuentes("Marco Polo", null, null);
        assertNotNull(filtrados);
        assertTrue(filtrados.size() >= 1, "Debe encontrar al menos un cliente con ese nombre.");
    }
    
    @Test
    public void testFiltrarClientesPorTelefono() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Ana");
        cliente.setApellidoP("Torres");
        cliente.setApellidoM("Mendoza");
        cliente.setTelefono("1010292938");
        cliente.setCorreo("ana@example.com");
        cliente.setFechaRegistro(LocalDate.now());

        clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        List<ClienteFrecuente> filtrados = clienteFrecuenteDAO.filtrarClientesFrecuentes(null, "1010292938", null);

        assertNotNull(filtrados, "La lista no debe ser nula.");
        assertEquals(1, filtrados.size(), "Debe haber un cliente con el teléfono '1010292938'.");
    }
    
    
    @Test
    public void testFiltrarClientesPorNombreYCorreo() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Laura");
        cliente.setApellidoP("García");
        cliente.setApellidoM("Mendoza");
        cliente.setTelefono("3476121212");
        cliente.setCorreo("laura@example.com");
        cliente.setFechaRegistro(LocalDate.now());

        clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        List<ClienteFrecuente> filtrados = clienteFrecuenteDAO.filtrarClientesFrecuentes("Laura García", null, "laura");

        assertNotNull(filtrados, "La lista no debe ser nula.");
        assertEquals(1, filtrados.size(), "Debe haber un cliente con nombre 'Laura' y apellido 'García'.");
    }
    
    
    @Test
    public void testFiltrarClientesConTresFiltros() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Luis");
        cliente.setApellidoP("Hernández");
        cliente.setApellidoM("Mendoza");
        cliente.setTelefono("8987898776");
        cliente.setCorreo("luis@example.com");
        cliente.setFechaRegistro(LocalDate.now());

        clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        List<ClienteFrecuente> filtrados = clienteFrecuenteDAO.filtrarClientesFrecuentes("Luis", "8987898776", "luis");

        assertNotNull(filtrados, "La lista no debe ser nula.");
        assertEquals(1, filtrados.size(), "Debe haber un cliente con los tres datos coincidentes.");
    }


    @Test
    public void testBuscarClientePorId() throws PersistenciaException {
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre("Lucía");
        cliente.setApellidoP("Mendoza");
        cliente.setApellidoM("Torres");
        cliente.setCorreo("lucia@gmail.com");
        cliente.setTelefono("1000012340");
        cliente = clienteFrecuenteDAO.registrarClienteFrecuente(cliente);

        ClienteFrecuente resultado = clienteFrecuenteDAO.buscarPorId(cliente.getId());

        assertNotNull(resultado);
        assertEquals(cliente.getNombre(), resultado.getNombre());
    }
}