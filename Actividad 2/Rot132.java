import java.util.Scanner;

public class Rot132 {

    public static final char[] ABC = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    public static final char[] ABCMAY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("\n Introduzca la contraseña");
        String cadena = sc.nextLine();
        if (cadena.isEmpty() || cadena.isBlank()) {
            System.out.println("Error, debe introducir una contraseña");
        } else {
            System.out.println("\n Indique si desea \"cifrar\"(c) o \"descifrar\"(d) o \"fuerza bruta\"(f)");
            String accion = sc.nextLine();
            System.out.println("Indique la cantidad a desplazar (valido de 0 a 25)");
            int desplaza = Integer.parseInt(sc.nextLine());
            if (accion.equals("c")) {
                System.out.println("\n Contraseña cifrada: " + xifraRotX(cadena, desplaza));
            } 
            if (accion.equals("d")) {
                System.out.println("\n Contraseña descifrada: " + desxifraRotX(cadena, desplaza));
            }
            if (accion.equals("f")) {
                for (int i = 0; i < 26; i++) {
                    System.out.println("\n Contraseña desplazada " + i + " posición: " + forcaBrutaRotX(xifraRotX(cadena, desplaza)));
                }
            }
        }
    }

    public static boolean esLetra (char letra) {
        char[] especiales = "àáèéíòóúüñç".toCharArray();
        for (int i = 0; i < especiales.length; i++) {
            if (i == letra) {return true;}
        }
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public static String xifraRotX (String cadena, int desplazamiento) {
        char[] especiales = "àáèéíòóúüñç".toCharArray();
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length; j++) {
                    if (letra==ABC[j]) {
                            if (j + desplazamiento <= ABC.length-1) {s = s + ABC[j+desplazamiento];} 
                            if (j + desplazamiento > ABC.length-1) {s = s + ABC[j+desplazamiento-ABC.length];}
                    }
                    if (letra==ABCMAY[j]) {
                        if (j + desplazamiento <= ABCMAY.length-1) {s = s + ABCMAY[j+desplazamiento];} 
                            if (j + desplazamiento > ABCMAY.length-1) {s = s + ABCMAY[j+desplazamiento-ABCMAY.length];}
                    }
                    if (letra == 'ñ') {
                        j = 13;
                        s = s + ABC[j+desplazamiento];
                    }
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    } 
    
    public static String desxifraRotX (String cadena, int desplazamiento) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length; j++) {
                    if (letra==ABC[j]) {
                            if (j >= desplazamiento) {s = s + ABC[(j-desplazamiento)];} 
                            if (j < desplazamiento) {s = s + ABC[ABC.length-desplazamiento+j];}
                    }
                    if (letra==ABCMAY[j]) {
                        if (j >= desplazamiento) {s = s + ABCMAY[j-desplazamiento];} 
                        if (j < desplazamiento) {s = s + ABCMAY[ABCMAY.length-desplazamiento+j];}
                    } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    }

    public static String forcaBrutaRotX(String cadena) {
        String s = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (esLetra(letra)) {
                for (int j = 0; j < ABC.length; j++) {
                    if (letra==ABC[j]) {
                        if (j >= 1) {s = s + ABC[(j-1)];} 
                        if (j < 1) {s = s + ABC[ABC.length-1+j];}
                }
                if (letra==ABCMAY[j]) {
                    if (j >= 1) {s = s + ABCMAY[j-1];} 
                    if (j < 1) {s = s + ABCMAY[ABCMAY.length-1+j];}
                } 
                }
            } else {s = s + cadena.charAt(i);}
        } return s;
    }
}