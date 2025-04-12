/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con la l+ogica
 * de negocio.
 * @author katia
 */
public class NegocioException extends Exception{

    /**
     * Constructor que crea una nueva excepción con el mensaje especificado.
     * @param message mensaje que describe el problema.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Constructor que crea una nueva excepción con el mensaje y la causa especificados.
     * @param message El mensaje de error que describe el problema.
     * @param cause La causa que provocó el error).
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
