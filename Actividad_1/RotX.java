import java.util.Scanner;

public class RotX {

    public static final String ABC = "abcdefghijklmnopqrstuvwxyz";
    public static final String ABCMAY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main (String[] args){
        Scanner scann = new Scanner(System.in);
        Scanner scann2 = new Scanner(System.in);
        System.out.println("\n Introduzca la contraseña");
        String cadena = scann.nextLine();
        if (cadena.isEmpty() || cadena.isBlank()) {
            System.out.println("Error, debe introducir una contraseña");
        } else {
            System.out.println("\n Indique si desea \"cifrar\" o \"descifrar\"");
            String accion = scann2.nextLine();
            if (accion.equals("cifrar")) {
                System.out.println("\n Contraseña cifrada: " + xifraRotX(cadena));
            } if (accion.equals("descifrar")) {
                System.out.println("\n Contraseña descifrada: " + desxifraRotX(cadena));
            } //if(!accion.equals("cifrar") || !accion.equals("descifrar")) {
                //System.out.println("No es una opción correcta");
            //}
        }
        scann.close();
        scann2.close();
    }

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public static String xifraRotX (String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length(); j++) {
                    if (letra==ABC.charAt(j)) {
                            if (j >= 13) {s = s + ABC.charAt(j+13-ABC.length());} 
                            else {s = s + ABC.charAt(j+13);}
                    }
                    if (letra==ABCMAY.charAt(j)) {
                        if (j >= 13) {s = s + ABCMAY.charAt(j+13-ABCMAY.length());} 
                        else {s = s + ABCMAY.charAt(j+13);}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    } 
    
    public static String desxifraRotX (String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length(); j++) {
                    if (letra==ABC.charAt(j)) {
                            if (j >= 13) {s = s + ABC.charAt(j+13-ABC.length());} 
                            else {s = s + ABC.charAt(j+13);}
                    }
                    if (letra==ABCMAY.charAt(j)) {
                        if (j >= 13) {s = s + ABCMAY.charAt(j+13-ABCMAY.length());} 
                        else {s = s + ABCMAY.charAt(j+13);}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    }
}