import java.util.Scanner;

public class Rot13 {

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
                System.out.println("\n Contraseña cifrada: " + xifraRot13(cadena, ABC, ABCMAY));
            } if (accion.equals("descifrar")) {
                System.out.println("\n Contraseña descifrada: " + desxifraRot13(cadena, ABC, ABCMAY));
            } //if(!accion.equals("cifrar") || !accion.equals("descifrar")) {
                //System.out.println("No es una opción correcta");
            //}
        }
    }

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public static String xifraRot13 (String cadena, String abc, String ABCm) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < abc.length(); j++) {
                    if (letra==abc.charAt(j)) {
                            if (j >= 13) {s = s + abc.charAt(j+13-abc.length());} 
                            else {s = s + abc.charAt(j+13);}
                    }
                    if (letra==ABCm.charAt(j)) {
                        if (j >= 13) {s = s + ABCm.charAt(j+13-ABCm.length());} 
                        else {s = s + ABCm.charAt(j+13);}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    } 
    
    public static String desxifraRot13 (String cadena, String abc, String ABCm) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < abc.length(); j++) {
                    if (letra==abc.charAt(j)) {
                            if (j >= 13) {s = s + abc.charAt(j+13-abc.length());} 
                            else {s = s + abc.charAt(j+13);}
                    }
                    if (letra==ABCm.charAt(j)) {
                        if (j >= 13) {s = s + ABCm.charAt(j+13-ABCm.length());} 
                        else {s = s + ABCm.charAt(j+13);}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    }
}