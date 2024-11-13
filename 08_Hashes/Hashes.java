
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    String hash;
    byte[] bytes;
    int npass=0;

    public String getSHA512AmbSalt(String pw, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        hash = hex.formatHex(bytes);
        return hash;
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 10000;
        int keyLength = 512;
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, keyLength);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashBytes = skf.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        hash = hex.formatHex(hashBytes);
        return hash;
    }

    public String forcaBruta(String alg, String hash, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
                String charset = "abcdefABCDEF1234567890!";
                npass = 0; // Reiniciar el contador de passwords probados
                char[] attempt = new char[6];

        // Intentar todas las combinaciones de longitud de 1 a 6
        for (int len = 1; len <= 6; len++) {
            // Array para almacenar la contraseña en prueba

            // Bucle para longitud 1
            for (int i = 0; i < charset.length(); i++) {
                attempt[0] = charset.charAt(i);
                if (len == 1 && checkPassword(attempt, len, alg, hash, salt))
                    return new String(attempt, 0, len);

                // Bucle para longitud 2
                for (int j = 0; j < charset.length(); j++) {
                    attempt[1] = charset.charAt(j);
                    if (len == 2 && checkPassword(attempt, len, alg, hash, salt))
                        return new String(attempt, 0, len);

                    // Bucle para longitud 3
                    for (int k = 0; k < charset.length(); k++) {
                        attempt[2] = charset.charAt(k);
                        if (len == 3 && checkPassword(attempt, len, alg, hash, salt))
                            return new String(attempt, 0, len);

                        // Bucle para longitud 4
                        for (int l = 0; l < charset.length(); l++) {
                            attempt[3] = charset.charAt(l);
                            if (len == 4 && checkPassword(attempt, len, alg, hash, salt))
                                return new String(attempt, 0, len);

                            // Bucle para longitud 5
                            for (int m = 0; m < charset.length(); m++) {
                                attempt[4] = charset.charAt(m);
                                if (len == 5 && checkPassword(attempt, len, alg, hash, salt))
                                    return new String(attempt, 0, len);

                                // Bucle para longitud 6
                                for (int n = 0; n < charset.length(); n++) {
                                    attempt[5] = charset.charAt(n);
                                    if (len == 6 && checkPassword(attempt, len, alg, hash, salt))
                                        return new String(attempt, 0, len);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null; // Si no se encuentra la contraseña
    }

    // Método auxiliar para verificar la contraseña
    private boolean checkPassword(char[] attempt, int len, String alg, String hash, String salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        npass++; // Incrementar el contador de passwords probados
        String generatedHash;

        // Calcular el hash según el algoritmo especificado
        if (alg.equals("SHA-512")) {
            generatedHash = getSHA512AmbSalt(new String(attempt, 0, len), salt);
        } else {
            generatedHash = getPBKDF2AmbSalt(new String(attempt, 0, len), salt);
        }

        // Verificar si el hash generado coincide con el hash objetivo
        return generatedHash.equals(hash);
    }

    public String getInterval(long t1, long t2) {
        long interval = t2 - t1;
        long millis = interval % 1000;
        interval /= 1000;
        long seconds = interval % 60;
        interval /= 60;
        long minutes = interval % 60;
        interval /= 60;
        long hours = interval % 24;
        long days = interval / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds,
                millis);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException{
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = { "SHA-512", "PBKDF2" };
        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }

}
