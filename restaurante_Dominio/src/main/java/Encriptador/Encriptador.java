package Encriptador;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Proporciona metodos para encriptar y desencriptar textos utilizando
 * el algoritmo AES.
 * @author katia
 */
public class Encriptador {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456";

    /**
     * Encripta el texto proporcionado utilizando el algoritmo AES.
     * @param text El texto a encriptar.
     * @return El texto encriptado en formato Base64.
     * @throws Exception Si ocurre un error durante el proceso de encriptación.
     */
    public static String encrypt(String text) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Desencripta el texto previamente encriptado utilizando el algoritmo AES.
     * @param encryptedText El texto encriptado en formato Base64.
     * @return El texto desencriptado.
     * @throws Exception Si ocurre un error durante el proceso de desencriptación.
     */
    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}

