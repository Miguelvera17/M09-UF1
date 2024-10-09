import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Monoalfabetic {
    public static final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] alfabetPermuta = permutaAlfabet(alfabet);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Introduzca una palabra");
        String cadena = sc.nextLine();
        System.out.println("\n Indique si desea \"cifrar\"[c] o \"descifrar\"[d]");
        System.out.print("---> ");
        String accion = sc.nextLine();
        System.out.print("\nAlfabeto original:  ");
        System.out.print(alfabet);
        System.out.println("\n                    ||||||||||||||||||||||||||||||||||||||");
        System.out.print("Alfabeto permutado: ");
        System.out.print(alfabetPermuta);
        System.out.println("\n");
        if (accion.equals("c")) {
            System.out.println("\nContraseña cifrada: " + xifraMonoAlfa(cadena));
        } 
        if (accion.equals("d")) {
            System.out.println("\nContraseña descifrada: " + desxifraMonoAlfa(cadena));
        }
    }

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

    public static String xifraMonoAlfa (String cadena) {
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
    
    public static String desxifraMonoAlfa (String cadena) {
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

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }
}
