/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Encriptador;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
/**
 * Clase encargada de realizar la encriptación y desencriptación de cadenas de texto.
 * Utiliza una clave fija.
 * Se utiliza para el teléfono.
 * @author katia
 */
public class Encriptador {
    
    private static final String ALGORITMO = "AES";
    private static final String CLAVE = "clave-fija-para-";

    /**
     * Encripta el texto de entrada utilizando el algoritmo AES
     * (Advanced Encryption Standard)
     * @param input Texto que se desea encriptar.
     * @return Texto encriptado, como una cadena en base 64.
     */
    public static String encrypt(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(CLAVE.getBytes(), ALGORITMO); // Usamos una clave fija
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // Usamos el modo ECB
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cifrado = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(cifrado); // Devolvemos el texto cifrado en Base64
        } catch (Exception e) {
            throw new RuntimeException("Error en la encriptación", e);
        }
    }

    /**
     * Desencripta el texto en Base64 utilizando el algoritmo AES con una clave fija.
     * @param input El texto en Base64 que se desea desencriptar.
     * @return Texto original después de la desencriptación.
     */
    public static String decrypt(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(CLAVE.getBytes(), ALGORITMO);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodificado = Base64.getDecoder().decode(input); // Decodificamos el Base64
            return new String(cipher.doFinal(decodificado)); // Desencriptamos el texto
        } catch (Exception e) {
            throw new RuntimeException("Error en la desencriptación", e);
        }
    }
    
}

