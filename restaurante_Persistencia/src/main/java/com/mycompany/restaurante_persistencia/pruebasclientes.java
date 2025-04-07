/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.restaurante_persistencia;

import DAOs.ClienteFrecuenteDAO;
import ENUM.EstadoComanda;
import Entidades.ClienteFrecuente;
import Entidades.Comanda;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katia
 */
public class pruebasclientes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClienteFrecuenteDAO clienteFrecuenteDAO = new ClienteFrecuenteDAO();

//        // Paso 1: Registrar varios clientes frecuentes
//        System.out.println("Registrando varios clientes...");
//        
//        // Cliente 1
//        ClienteFrecuente cliente1 = new ClienteFrecuente();
//        cliente1.setNombre("Juan");
//        cliente1.setApellidoP("Pérez");
//        cliente1.setApellidoM("González");
//        cliente1.setCorreo("juanperez@gmail.com");
//        cliente1.setTelefono("123456789");
//        cliente1.setFechaRegistro(LocalDate.now());
//        
//        // Cliente 2
//        ClienteFrecuente cliente2 = new ClienteFrecuente();
//        cliente2.setNombre("María");
//        cliente2.setApellidoP("López");
//        cliente2.setApellidoM("Martínez");
//        cliente2.setCorreo("marialopez@gmail.com");
//        cliente2.setTelefono("987654321");
//        cliente2.setFechaRegistro(LocalDate.now());
//
//        // Cliente 3
//        ClienteFrecuente cliente3 = new ClienteFrecuente();
//        cliente3.setNombre("Carlos");
//        cliente3.setApellidoP("Ramírez");
//        cliente3.setApellidoM("Sánchez");
//        cliente3.setCorreo("carlosramirez@gmail.com");
//        cliente3.setTelefono("555123456");
//        cliente3.setFechaRegistro(LocalDate.now());
//
//        // Registrar los clientes
//        try {
//            cliente1 = clienteFrecuenteDAO.registrarClienteFrecuente(cliente1);
//            cliente2 = clienteFrecuenteDAO.registrarClienteFrecuente(cliente2);
//            cliente3 = clienteFrecuenteDAO.registrarClienteFrecuente(cliente3);
//            
//        } catch (PersistenciaException e) {
//            System.out.println("Error al registrar los clientes: " + e.getMessage());
//            return;
//        }
//
////        // Paso 2: Crear y asociar comandas a los clientes
//        System.out.println("Asociando comandas a los clientes...");
//
//        // Comandas para Cliente 1
//        Comanda comanda1Cliente1 = new Comanda();
//        comanda1Cliente1.setEstado(EstadoComanda.Entregado);
//        comanda1Cliente1.setFechaHora(LocalDateTime.now());
//        comanda1Cliente1.setTotalVenta(100.0);
//        comanda1Cliente1.setFolio("CM1");
//        comanda1Cliente1.setCliente(cliente1);
//
//        Comanda comanda2Cliente1 = new Comanda();
//        comanda2Cliente1.setEstado(EstadoComanda.Entregado);
//        comanda2Cliente1.setFechaHora(LocalDateTime.now());
//        comanda2Cliente1.setTotalVenta(150.0);
//        comanda2Cliente1.setFolio("CM2");
//        comanda2Cliente1.setCliente(cliente1);
//
//        // Comandas para Cliente 2
//        Comanda comanda1Cliente2 = new Comanda();
//        comanda1Cliente2.setEstado(EstadoComanda.Entregado);
//        comanda1Cliente2.setFechaHora(LocalDateTime.now());
//        comanda1Cliente2.setTotalVenta(200.0);
//        comanda1Cliente2.setFolio("CM3");
//        comanda1Cliente2.setCliente(cliente2);
//
//        // Comandas para Cliente 3
//        Comanda comanda1Cliente3 = new Comanda();
//        comanda1Cliente3.setEstado(EstadoComanda.Entregado);
//        comanda1Cliente3.setFechaHora(LocalDateTime.now());
//        comanda1Cliente3.setTotalVenta(50.0);
//        comanda1Cliente3.setFolio("CM4");
//        comanda1Cliente3.setCliente(cliente3);
//        
//        try {
//            clienteFrecuenteDAO.persistirComanda(comanda1Cliente1);
//            clienteFrecuenteDAO.persistirComanda(comanda2Cliente1);
//            clienteFrecuenteDAO.persistirComanda(comanda1Cliente2);
//            clienteFrecuenteDAO.persistirComanda(comanda1Cliente3);
//        } catch (PersistenciaException e){
//            System.out.println("Error al guardar las comandas: " + e.getMessage());
//            return;
//        }
//
//        // Paso 3: Obtener y mostrar el gasto total acumulado, visitas y puntos para cada cliente
//        System.out.println("Obteniendo información sobre los clientes frecuentes...");
//
//        // Obtener y mostrar información del cliente 1
//        Double gastoTotal1 = clienteFrecuenteDAO.obtenerGastoTotalAcumulado(cliente1);
//        Integer conteoVisitas1 = clienteFrecuenteDAO.obtenerConteoVisitas(cliente1);
//        Integer puntos1 = clienteFrecuenteDAO.obtenerPuntos(cliente1);
//        System.out.println("Cliente 1 - " + cliente1.getNombre());
//        System.out.println("Gasto Total: " + gastoTotal1);  // Debería ser 250 (100 + 150)
//        System.out.println("Visitas: " + conteoVisitas1);    // Debería ser 2
//        System.out.println("Puntos: " + puntos1);           // Debería ser 12 (250 / 20)
//        
//        // Obtener y mostrar información del cliente 2
//        Double gastoTotal2 = clienteFrecuenteDAO.obtenerGastoTotalAcumulado(cliente2);
//        Integer conteoVisitas2 = clienteFrecuenteDAO.obtenerConteoVisitas(cliente2);
//        Integer puntos2 = clienteFrecuenteDAO.obtenerPuntos(cliente2);
//        System.out.println("Cliente 2 - " + cliente2.getNombre());
//        System.out.println("Gasto Total: " + gastoTotal2);  // Debería ser 200
//        System.out.println("Visitas: " + conteoVisitas2);    // Debería ser 1
//        System.out.println("Puntos: " + puntos2);           // Debería ser 10 (200 / 20)
//        
//        // Obtener y mostrar información del cliente 3
//        Double gastoTotal3 = clienteFrecuenteDAO.obtenerGastoTotalAcumulado(cliente3);
//        Integer conteoVisitas3 = clienteFrecuenteDAO.obtenerConteoVisitas(cliente3);
//        Integer puntos3 = clienteFrecuenteDAO.obtenerPuntos(cliente3);
//        System.out.println("Cliente 3 - " + cliente3.getNombre());
//        System.out.println("Gasto Total: " + gastoTotal3);  // Debería ser 50
//        System.out.println("Visitas: " + conteoVisitas3);    // Debería ser 1
//        System.out.println("Puntos: " + puntos3);           // Debería ser 2 (50 / 20)
//
//        // Paso 4: Obtener todos los clientes frecuentes
//        try {
//            List<ClienteFrecuente> clientes = clienteFrecuenteDAO.obtenerTodos();
//            System.out.println("Clientes frecuentes registrados:");
//            for (ClienteFrecuente c : clientes) {
//                System.out.println(c.getNombre() + " - " + c.getCorreo());
//            }
//        } catch (PersistenciaException e) {
//            System.out.println("Error al obtener los clientes frecuentes: " + e.getMessage());
//        }

        // Paso 5: Filtrar clientes frecuentes por nombre
        System.out.println("Filtrando clientes por nombre...");
        try {
            List<ClienteFrecuente> clientesFiltrados = clienteFrecuenteDAO.filtrarClientesFrecuentes("Juan Pérez", null, null);
            System.out.println("Clientes filtrados por nombre:");
            for (ClienteFrecuente c : clientesFiltrados) {
                System.out.println(c.getNombreCompleto() + " - " + c.getCorreo());
            }
        } catch (PersistenciaException e) {
            System.out.println("Error al filtrar clientes frecuentes: " + e.getMessage());
        }
        
        System.out.println("Filtrando clientes por telefono...");
        try {
            List<ClienteFrecuente> clientesFiltrados = clienteFrecuenteDAO.filtrarClientesFrecuentes(null, "123456789", null);
            System.out.println("Clientes filtrados por telefono:");
            for (ClienteFrecuente c : clientesFiltrados) {
                System.out.println(c.getNombreCompleto()+ " - " + c.getCorreo());
            }
        } catch (PersistenciaException e) {
            System.out.println("Error al filtrar clientes frecuentes: " + e.getMessage());
        }
        
        System.out.println("Filtrando clientes por nombre y telefono...");
        try {
            List<ClienteFrecuente> clientesFiltrados = clienteFrecuenteDAO.filtrarClientesFrecuentes("Juan", "123456789", null);
            System.out.println("Clientes filtrados por nombre y telefono:");
            for (ClienteFrecuente c : clientesFiltrados) {
                System.out.println(c.getNombreCompleto()+ " - " + c.getCorreo());
            }
        } catch (PersistenciaException e) {
            System.out.println("Error al filtrar clientes frecuentes: " + e.getMessage());
        }
        

//        // Paso 6: Buscar un cliente por ID
//        try {
//            ClienteFrecuente clientePorId = clienteFrecuenteDAO.buscarPorId(cliente1.getId());
//            System.out.println("Cliente encontrado por ID: " + clientePorId.getNombreCompleto());
//        } catch (PersistenciaException e) {
//            System.out.println("Error al buscar cliente por ID: " + e.getMessage());
//        }
    }
    
}
