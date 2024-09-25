import java.util.Scanner;

public class Rot132 {

    public static final char[] ABC = "abcdefghijklmnñopqrstuvwxyz".toCharArray();
    public static final char[] ABCMAY = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();

    public static void main (String[] args){
        Scanner scann = new Scanner(System.in);
        Scanner scann2 = new Scanner(System.in);
        Scanner scann3 = new Scanner(System.in);
        System.out.println("\n Introduzca la contraseña");
        String cadena = scann.nextLine();
        if (cadena.isEmpty() || cadena.isBlank()) {
            System.out.println("Error, debe introducir una contraseña");
        } else {
            System.out.println("\n Indique si desea \"cifrar\" o \"descifrar\"");
            String accion = scann2.nextLine();
            System.out.println("Indique la cantidad a desplazar");
            int desplaza = Integer.parseInt(scann3.nextLine());
            if (accion.equals("cifrar")) {
                System.out.println("\n Contraseña cifrada: " + xifraRot13(cadena, desplaza));
            } if (accion.equals("descifrar")) {
                System.out.println("\n Contraseña descifrada: " + desxifraRot13(cadena, desplaza));
            }
        }
    }

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public static String xifraRot13 (String cadena, int desplazamiento) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length; j++) {
                    if (letra==ABC[j]) {
                            if (j >= desplazamiento) {s = s + ABC[j+desplazamiento-ABC.length];} 
                            else {s = s + ABC[j+desplazamiento];}
                    }
                    if (letra==ABCMAY[j]) {
                        if (j >= desplazamiento) {s = s + ABCMAY[j+desplazamiento-ABCMAY.length];} 
                        else {s = s + ABCMAY[j+desplazamiento];}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    } 
    
    public static String desxifraRot13 (String cadena, int desplazamiento) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length; j++) {
                    if (letra==ABC[j]) {
                            if (j >= desplazamiento) {s = s + ABC[j+desplazamiento-ABC.length];} 
                            else {s = s + ABC[j+desplazamiento];}
                    }
                    if (letra==ABCMAY[j]) {
                        if (j >= desplazamiento) {s = s + ABCMAY[j+desplazamiento-ABCMAY.length];} 
                        else {s = s + ABCMAY[j+desplazamiento];}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    }
}