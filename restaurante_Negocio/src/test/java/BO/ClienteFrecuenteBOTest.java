/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package BO;

import DTOs.ClienteFrecuenteDTO;
import Encriptador.Encriptador;
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
    public void testFiltrarPorTelefono() {
        ClienteFrecuenteDTO cliente = new ClienteFrecuenteDTO();
        cliente.setNombre("Pepe");
        cliente.setApellidoP("Pica");
        cliente.setTelefono("8998677678");
        cliente.setCorreo("pepe@pica.com");
        try {
            List<ClienteFrecuenteDTO> resultado = clienteBO.filtrarClientesFrecuentes(null, "8998677678", null);
            assertTrue(!resultado.isEmpty());
        } catch (NegocioException e) {
            fail("Error al filtrar por nombre: " + e.getMessage());
        }
    }
    
//    @Test
//    public void testGuardarYBuscarPorTelefono() {
//        // Paso 1: Crear un DTO para un cliente frecuente
//        ClienteFrecuenteDTO cliente = new ClienteFrecuenteDTO();
//        cliente.setNombre("Pepe");
//        cliente.setApellidoP("Pica");
//        cliente.setTelefono("8998677678"); // El teléfono a buscar
//        cliente.setCorreo("pepe@pica.com");
//
//        // Paso 2: Guardar el cliente (esto usa el BO y DAO, el teléfono será encriptado)
//        try {
//            clienteBO.registrarClienteFrecuente(cliente);
//        } catch (NegocioException e) {
//            fail("Error al registrar cliente: " + e.getMessage());
//        }
//
//        // Paso 3: Buscar el cliente por teléfono encriptado
//        try {
//            List<ClienteFrecuenteDTO> resultado = clienteBO.filtrarClientesFrecuentes(null, "8998677678", null);
//            System.out.println("Resultado busqueda: "+ resultado);
//            assertFalse(resultado.isEmpty());
//        } catch (NegocioException e) {
//            fail("Error al filtrar clientes por teléfono: " + e.getMessage());
//        }
//
//        }
    
    @Test
    public void testEncriptacionTelefono() {
        String telefonoOriginal = "8998677678";
        String telefonoEncriptado = Encriptador.encrypt(telefonoOriginal);
        assertNotEquals(telefonoOriginal, telefonoEncriptado);
        System.out.println("Telefono encriptado: " + telefonoEncriptado);
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
