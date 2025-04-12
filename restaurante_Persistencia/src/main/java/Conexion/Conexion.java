/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase responsable de gestionar la conexión con la base de datos haciendo uso
 * de JPA (Java Persistence API).
 * Proporciona métodos estáticos para crear y cerrar conexiones con la bd.
 * @author katia
 */
public class Conexion {
    private static EntityManagerFactory emf;
    
    /**
     * Crea y devuelve una nueva instancia de EntityManager para interactuar
     * con la base de datos.
     * @return Un entitymanager para realizar las operaciones de persistencia.
     */
    public static EntityManager crearConexion(){
        if (emf == null || !emf.isOpen()){
                emf = Persistence.createEntityManagerFactory("ConexionPU");
            }
        EntityManager entityManager = emf.createEntityManager();
        return entityManager;
    }
    
    /**
     * Cierra la instancia de EntityManagerFactory si está abierta.
     */
    public static void cerrarEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
