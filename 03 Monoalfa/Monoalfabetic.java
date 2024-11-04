import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Monoalfabetic {
    public static final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] a = permutaAlfabet(alfabet);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Introduzca una palabra");
        String cadena = sc.nextLine();
        System.out.println("\n Indique si desea \"cifrar\"[c] o \"descifrar\"[d]");
        String accion = sc.nextLine();
        System.out.println();
        System.out.print("abecedario original -->  ");
        for (int i = 0; i < alfabet.length; i++) {
            if (i == 0) {
                System.out.print(alfabet[i]);
            }
            if (i != 0) {
                System.out.print(", " + alfabet[i]);
            }
        }
        System.out.println();
        System.out.print("abecedario permutado --> ");
        for (int i = 0; i < a.length; i++) {
            if (i == 0) {
                System.out.print(a[i]);
            }
            if (i != 0) {
                System.out.print(", " + a[i]);
            }
        }
        System.out.println();
        System.out.println();
        if (accion.equals("c")) {
            System.out.println("Contraseña cifrada: " + xifraMonoAlfa(cadena));
        } 
        if (accion.equals("d")) {
            System.out.println("Contraseña descifrada: " + desxifraMonoAlfa(cadena));
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
                if (Character.isLowerCase(letra)) {
                    for (int j = 0; j < alfabet.length; j++) {
                        if (letra == alfabet[j]) {
                            s = s + a[j];
                        }
                    }
                }
                if (Character.isUpperCase(letra)) {
                    for (int j = 0; j < alfabet.length; j++) {
                        if (Character.toLowerCase(letra) == alfabet[j]) {
                            s = s + Character.toUpperCase(a[j]);
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
            if (esLetra(letra)) {
                if (Character.isLowerCase(letra)) {
                    for (int j = 0; j < a.length; j++) {
                        if (letra==a[j]) {
                            s = s + alfabet[j];
                        }
                    }
                }
                if (Character.isUpperCase(letra)) {
                    for (int j = 0; j < a.length; j++) {
                        if (Character.toLowerCase(letra) ==a[j]) {
                            s = s + Character.toUpperCase(alfabet[j]);
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
