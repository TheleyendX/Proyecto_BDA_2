///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package BO;
//
//import DTOs.IngredienteDTO;
//import ENUM.UnidadMedida;
//import static ENUM.UnidadMedida.gm;
//import static ENUM.UnidadMedida.ml;
//import Excepciones.NegocioException;
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
///**
// *
// * @author jorge
// */
//public class IngredienteBOTest {
// 
//
//    private IngredienteBO ingredienteBO;
//
//    @BeforeEach
//    public void setUp() {
//        ingredienteBO = new IngredienteBO();
//    }
//
//    @Test
//    public void testRegistrarIngredienteValido() {
//        IngredienteDTO dto = new IngredienteDTO();
//        dto.setNombre(" Harina ");
//        dto.setUndadMedida(UnidadMedida.gm);
//
//        try {
//            IngredienteDTO registrado = ingredienteBO.registrarIngrediente(dto);
//            assertEquals("Harina", registrado.getNombre());
//        } catch (NegocioException e) {
//            fail("No debería lanzar excepción: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testRegistrarIngredienteDuplicado() {
//        IngredienteDTO dto1 = new IngredienteDTO();
//        dto1.setNombre("Azúcar");
//        dto1.setUndadMedida(UnidadMedida.gm);
//
//        IngredienteDTO dto2 = new IngredienteDTO();
//        dto2.setNombre("Azúcar"); // Mismo nombre
//        dto2.setUndadMedida(ml); // Puede ser distinta unidad
//
//        try {
//            ingredienteBO.registrarIngrediente(dto1);
//        } catch (NegocioException e) {
//            fail("No debería fallar el primero: " + e.getMessage());
//        }
//
//        NegocioException ex = assertThrows(NegocioException.class, () -> {
//            ingredienteBO.registrarIngrediente(dto2);
//        });
//        assertTrue(ex.getMessage().contains("Ya existe"));
//    }
//
//    @Test
//    public void testBuscarPorNombreExistente() {
//        IngredienteDTO dto = new IngredienteDTO();
//        dto.setNombre("Sal");
//        dto.setUndadMedida(UnidadMedida.gm);
//
//        try {
//            ingredienteBO.registrarIngrediente(dto);
//            IngredienteDTO encontrado = ingredienteBO.buscarPorNombre("Sal");
//            assertNotNull(encontrado);
//            assertEquals("Sal", encontrado.getNombre());
//        } catch (NegocioException e) {
//            fail("Error al buscar ingrediente: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testObtenerIngredientes() {
//        try {
//            List<IngredienteDTO> ingredientes = ingredienteBO.obtenerIngredientes();
//            assertNotNull(ingredientes);
//        } catch (NegocioException e) {
//            fail("Error al obtener ingredientes: " + e.getMessage());
//        }
//    }
//
//    @Test
//    public void testEliminarIngredienteInexistente() {
//        NegocioException ex = assertThrows(NegocioException.class, () -> {
//            ingredienteBO.eliminarIngrediente(99999L);
//        });
//        assertTrue(ex.getMessage().contains("Ingrediente no encontrado"));
//    }
//}