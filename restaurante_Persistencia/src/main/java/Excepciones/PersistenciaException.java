/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 * Excepción personalizada que se utiliza para manejar errores específicos relacionados
 * con la persistencia de los datos.
 * @author katia
 */
public class PersistenciaException extends Exception{
    /**
     *  Constructor que crea una nueva instancia de PersistenciaException.
     * @param message Mensaje de error.
     */
    public PersistenciaException(String message) {
        super(message);
    }
    
    /**
     * Constructor que crea una nueva instancia con un mensaje de error y 
     * una causa.
     * @param message Mensaje que se usa para describir el problema.
     * @param cause La causa del error.
     */
    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
