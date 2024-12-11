package Repaso;

import java.io.*;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecreta01";

    public static IvParameterSpec generaIv() {
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private static IvParameterSpec extreuIv(byte[] bIvIMsgXifrat) {
        System.arraycopy(bIvIMsgXifrat, 0, iv, 0, iv.length);
        return new IvParameterSpec(iv);
    }

        public static byte[] getBytesXifrats(byte[] bIvIMsgXifrat) {
            int midaXifrat = bIvIMsgXifrat.length - MIDA_IV;
            byte[] bXifrat = new byte[midaXifrat];
            System.arraycopy(bIvIMsgXifrat, MIDA_IV,bXifrat,0,midaXifrat);
            return bXifrat;
    }

    private static SecretKeySpec generaHash(String clau) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md =MessageDigest.getInstance(ALGORISME_HASH);

        md.update(clau.getBytes("UTF-8"));
        byte[] bClau = new byte[16];
        System.arraycopy(md.digest(), 0, bClau, 0, bClau.length);

        return new SecretKeySpec(bClau, ALGORISME_XIFRAT);
    }

    private static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bNets = msg.getBytes();

        IvParameterSpec ivPS = generaIv();

        SecretKeySpec secretKeySpec = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivPS);
        byte[] bXifrat = cipher.doFinal(bNets);

        byte[] bIvIMsgXifrat = new byte[MIDA_IV + bXifrat.length];
        System.arraycopy(iv, 0, bIvIMsgXifrat, 0, MIDA_IV);
        System.arraycopy(bXifrat, 0, bIvIMsgXifrat, MIDA_IV, bXifrat.length);

        return bIvIMsgXifrat;
    }

    public static String desxifraAES (byte[] bIvIMsgXifrat , String clau) throws Exception {
        // Extreure l'IV.
        IvParameterSpec ivParameterSpec = extreuIv(bIvIMsgXifrat);
        // Extreure la part xifrada.
        byte[] bXifrat = getBytesXifrats(bIvIMsgXifrat);
        // Fer hash de la clau
        SecretKeySpec secretKeySpec = generaHash(clau);
        // Desxifrar.
        Cipher cipherDecrypt = Cipher.getInstance(FORMAT_AES);
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] decrypted = cipherDecrypt.doFinal(bXifrat);
        // return String desxifrat
        return new String(decrypted);   
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", "Hola Andrés cómo está tu cuñado","Àgora ïlla Ôtto", ")(/&%$)"};
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
