import java.security.*;
import java.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "salchipapa";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        //Obtenir els bytes de l’String
        byte[] msgBytes = msg.getBytes();
        // Genera IvParameterSpec
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        // Genera hash
        MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyHash = hash.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);
        // Encrypt.
        Cipher encrypt = Cipher.getInstance(FORMAT_AES);
        encrypt.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedMsg = encrypt.doFinal(msgBytes);
        // Combinar IV i part xifrada.
        byte[] combinedIV = new byte[iv.length + encryptedMsg.length];
        System.arraycopy(iv, 0, combinedIV, 0, iv.length);
        System.arraycopy(encryptedMsg, 0, combinedIV, iv.length, encryptedMsg.length);
        // return iv+msgxifrat
        return combinedIV;
    }

    public static String desxifraAES (byte[] bIvIMsgXifrat , String clau) throws Exception {
        // Extreure l'IV.
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        // Extreure la part xifrada.
        byte[] encryptedMsg = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);
        // Fer hash de la clau
        MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] keyHash = hash.digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);
        // Desxifrar.
        Cipher desencrypt = Cipher.getInstance(FORMAT_AES);
        desencrypt.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedMsg = desencrypt.doFinal(encryptedMsg);
        // return String desxifrat
        return new String(decryptedMsg, "UTF-8");   
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado","Àgora ïlla Ôtto"};
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES (bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage ());
            }
            System.out.println("--------------------" );
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
