package iticbcn.xifratge;
import java.security.*;
import java.util.Arrays;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador{

    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            // Aplicar la sustitución monoalfabética
            byte[] msgBytes = msg.getBytes("UTF-8");

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

            // return iv + msg cifrat
            return new TextXifrat(combinedIV);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error de cifrado: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            // Extreure l'IV.
            byte[] iv = Arrays.copyOfRange(xifrat.getBytes(), 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            // Extreure la part xifrada.
            byte[] encryptedMsg = Arrays.copyOfRange(xifrat.getBytes(), MIDA_IV, xifrat.getBytes().length);

            // Fer hash de la clau
            MessageDigest hash = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] keyHash = hash.digest(clau.getBytes("UTF-8"));
            SecretKeySpec secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);

            // Desxifrar.
            Cipher desencrypt = Cipher.getInstance(FORMAT_AES);
            desencrypt.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] decryptedMsg = desencrypt.doFinal(encryptedMsg);

            // Revertir la sustitución monoalfabética
            String msgDesencriptado = new String(decryptedMsg, "UTF-8");
            return msgDesencriptado;
        } catch (Exception e) {
            throw new ClauNoSuportada("Error de descifrado: " + e.getMessage());
        }
    }
}
