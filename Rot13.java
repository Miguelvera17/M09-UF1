import java.util.Scanner;

public class Rot13 {

    public static final String abc = "abcdefghijklmnopqrstuvwxyz";
    public static final String ABCm = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

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
                System.out.println("\n Contraseña cifrada: " + construye(cadena, abc, ABCm, accion));
            } if (accion.equals("descifrar")) {
                System.out.println("\n Contraseña descifrada: " + construye(cadena, abc, ABCm, accion));
            } //if(!accion.equals("cifrar") || !accion.equals("descifrar")) {
                //System.out.println("No es una opción correcta");
            //}
        }
    }

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public static String construye (String cadena, String abc, String ABCm, String accion) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < abc.length(); j++) {
                    if (letra==abc.charAt(j)) {
                        if (accion.equals("cifrar")) {
                            if (letra==abc.charAt(j)) {
                                if (j >= 13) {s = s + abc.charAt(j+13-abc.length());} 
                                else {s = s + abc.charAt(j+13);}
                            }
                        } 
                        if (letra==ABCm.charAt(j)) {
                            if (j >= 13) {s = s + ABCm.charAt(j+13-ABCm.length());} 
                            else {s = s + ABCm.charAt(j+13);}
                        }     
                    }
                    if (accion.equals("descifrar")) {
                        if (letra==abc.charAt(j)) {
                            if (j < 13) {s = s + abc.charAt(abc.length()-13+j);} 
                            else {s = s + abc.charAt(j-13);}
                        }
                        if (letra==ABCm.charAt(j)) {
                            if (j < 13) {s = s + ABCm.charAt(ABCm.length()-13+j);} 
                            else {s = s + ABCm.charAt(j-13);}
                        }
                    }
                }
            } 
            else {s = s + cadena.charAt(i);}
        }
        return s;
    }
}