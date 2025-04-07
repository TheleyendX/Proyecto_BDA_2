/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package BO;

import DTOs.ClienteFrecuenteDTO;
import Excepciones.NegocioException;
import java.util.List;
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
public class ClienteFrecuenteBOTest {
    private ClienteFrecuenteBO clienteBO;
    
    @BeforeEach
    public void setUp(){
        clienteBO = new ClienteFrecuenteBO();
    }
    
    @Test
    public void testRegistrarClienteValido() {
        ClienteFrecuenteDTO cliente = new ClienteFrecuenteDTO();
        cliente.setNombre(" Ana ");
        cliente.setApellidoP(" López ");
        cliente.setApellidoM(" Torres ");
        cliente.setTelefono("1234567890");
        cliente.setCorreo("ana.lopez@gmail.com");

        try {
            ClienteFrecuenteDTO registrado = clienteBO.registrarClienteFrecuente(cliente);
            assertNotNull(registrado.getId());
            assertEquals("Ana", registrado.getNombre());  // Trim aplicado
        } catch (NegocioException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }

    @Test
    public void testRegistrarClienteDuplicadoTelefono() {
        ClienteFrecuenteDTO cliente1 = new ClienteFrecuenteDTO();
        cliente1.setNombre("Luis");
        cliente1.setApellidoP("Ramírez");
        cliente1.setTelefono("1112223333");
        cliente1.setCorreo("luis.ramirez@gmail.com");

        ClienteFrecuenteDTO cliente2 = new ClienteFrecuenteDTO();
        cliente2.setNombre("Carlos");
        cliente2.setApellidoP("Hernández");
        cliente2.setTelefono("1112223333"); // Mismo teléfono
        cliente2.setCorreo("carlos.hernandez@gmail.com");

        try {
            clienteBO.registrarClienteFrecuente(cliente1);
        } catch (NegocioException e) {
            fail("No debería fallar el primero: " + e.getMessage());
        }

        NegocioException ex = assertThrows(NegocioException.class, () -> {
            clienteBO.registrarClienteFrecuente(cliente2);
        });
        assertTrue(ex.getMessage().contains("teléfono"));
    }

    @Test
    public void testFiltrarPorNombre() {
        try {
            List<ClienteFrecuenteDTO> resultado = clienteBO.filtrarClientesFrecuentes("Ana", null, null);
            assertFalse(resultado.isEmpty());
        } catch (NegocioException e) {
            fail("Error al filtrar por nombre: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarPorIdExistente() {
        ClienteFrecuenteDTO cliente = new ClienteFrecuenteDTO();
        cliente.setNombre("Mario");
        cliente.setApellidoP("Bros");
        cliente.setTelefono("2223334444");
        cliente.setCorreo("mario@plomeros.com");

        try {
            ClienteFrecuenteDTO registrado = clienteBO.registrarClienteFrecuente(cliente);
            ClienteFrecuenteDTO encontrado = clienteBO.buscarPorId(registrado.getId());

            assertNotNull(encontrado);
            assertEquals("Mario", encontrado.getNombre());
        } catch (NegocioException e) {
            fail("Error en testBuscarPorIdExistente: " + e.getMessage());
        }
    }

    @Test
    public void testBuscarPorIdNoExistente() {
        NegocioException ex = assertThrows(NegocioException.class, () -> {
            clienteBO.buscarPorId(99999L); // ID que no existe
        });
        assertTrue(ex.getMessage().contains("No se encontró"));
    }
    
}
