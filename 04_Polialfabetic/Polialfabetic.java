import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

public class Polialfabetic {

    public static final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] alfabetPermuta;
    public static int clauSecreta = 1821; //independencia del peru
    public static Random random;

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre","Test 02 Taüll, DÍA, año","Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats [] = new String[msgs.length];
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats [i] = xifraPoliAlfa (msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats [i]);
        }
        System.out.println("Desxifratge: \n-----------" );
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa (msgsXifrats [i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats [i], msg);
        }
    }
    
    private static void initRandom(int clauSecreta) {
        random = new Random(clauSecreta);
    }
    
    public static void permutaAlfabet() {
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

    public static String xifraPoliAlfa (String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            permutaAlfabet();
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
    
    public static String desxifraPoliAlfa (String cadena) {
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

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }
}

