package iticbcn.xifratge;
import java.util.Collections;
import java.util.ArrayList;

public class XifradorMonoalfabetic implements Xifrador{
    
    public final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public char[] alfabetPermuta = permutaAlfabet(alfabet);

    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> abclist = new ArrayList<>();
        for (char i : alfabet) {
            abclist.add(i);
        }
        Collections.shuffle(abclist);
        char [] abcPermuta = new char[abclist.size()];
        for (int i = 0; i < abclist.size(); i++) {
            abcPermuta[i] = abclist.get(i);
        }
        return abcPermuta;
    }

    public String xifraMonoAlfa (String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
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
        } return s;
    } 
    
    public String desxifraMonoAlfa (String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
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
}
