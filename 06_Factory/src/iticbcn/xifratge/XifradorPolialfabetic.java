package iticbcn.xifratge;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador{

    public final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public char[] alfabetPermuta;
    public int clauSecreta = 1821; //independencia del peru
    public Random random;
    
    private void initRandom(long clauSecreta) {
        random = new Random(clauSecreta);
    }
    
    public void permutaAlfabet() {
        alfabetPermuta = new char[alfabet.length];
        ArrayList<Character> abclist = new ArrayList<>();

        for (char i : alfabet) {
            abclist.add(i);
        }
        Collections.shuffle(abclist, random);
        
        for (int i = 0; i < abclist.size(); i++) {
            alfabetPermuta[i] = abclist.get(i);
        }
    }
        
    public String desxifraPoliAlfa (String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet();
            char letra = cadena.charAt(i);
            if (esLetra((letra))) {
                if (Character.isUpperCase(letra)) {
                    for (int j = 0; j < alfabetPermuta.length; j++) {
                        if (Character.toLowerCase(letra) ==alfabetPermuta[j]) {
                            s = s + Character.toUpperCase(alfabet[j]);
                        }
                    }
                } else {
                    for (int j = 0; j < alfabetPermuta.length; j++) {
                        if (letra ==alfabetPermuta[j]) {
                            s = s + alfabet[j];
                        }
                    }
                }
            } else {s = s + letra;}
        } return s;
    }

    public boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            long claveLong = Long.parseLong(clau);
            initRandom(claveLong);
            String s = "";
            for (int i = 0; i < msg.length(); i++) {
                permutaAlfabet();
                char letra = msg.charAt(i);
                if (esLetra(letra)) {
                    if (Character.isUpperCase(letra)) {
                        letra = Character.toLowerCase(letra);
                        for (int j = 0; j < alfabet.length; j++) {
                            if (letra == alfabet[j]) {
                                s = s + Character.toUpperCase(alfabetPermuta[j]);
                            }
                        }
                    } else {
                        for (int j = 0; j < alfabet.length; j++) {
                            if (letra == alfabet[j]) {
                                s = s + alfabetPermuta[j];
                            }
                        }
                    } 
                } else {s = s + letra;}
            } return new TextXifrat(s.getBytes(java.nio.charset.StandardCharsets.UTF_8));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau no es suportada. Ha de ser un valor numèric."); // Mensaje de excepción
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            long claveLong = Long.parseLong(clau);
            initRandom(claveLong);
            byte[] bytesXifrats = xifrat.getBytes();
            String cadena = new String(bytesXifrats, java.nio.charset.StandardCharsets.UTF_8);
            String s = "";
            for (int i = 0; i < cadena.length(); i++) {
                permutaAlfabet();
                char letra = cadena.charAt(i);
                if (esLetra((letra))) {
                    if (Character.isUpperCase(letra)) {
                        for (int j = 0; j < alfabetPermuta.length; j++) {
                            if (Character.toLowerCase(letra) ==alfabetPermuta[j]) {
                                s = s + Character.toUpperCase(alfabet[j]);
                            }
                        }
                    } else {
                        for (int j = 0; j < alfabetPermuta.length; j++) {
                            if (letra ==alfabetPermuta[j]) {
                                s = s + alfabet[j];
                            }
                        }
                    }
                } else {s = s + letra;}
            } return s;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau no es suportada. Ha de ser un valor numèric."); // Mensaje de excepción
        }
    }
}

