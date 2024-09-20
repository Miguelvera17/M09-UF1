import java.util.Scanner;

public class Rot13 {

    public static final String abc = "abcdefghijklmnopqrstuvwxyz";
    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main (String[] args){
        Scanner scann = new Scanner(System.in);
        System.out.println("Introduzca la contraseña");
        String cadena = scann.nextLine();
        if (cadena.isEmpty() || cadena.isBlank()) {
            System.out.println("Error, debe introducir una contraseña");
        } else {
            System.out.println("Contraseña cifrada: " + xifratRot13(cadena));
            System.out.println("Contraseña descifrada: " + desxifratRot13(xifratRot13(cadena)));
        }
    }

    public static String xifratRot13 (String cadena) {
        String vacio = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (Character.isLetter(letra)) {
                for (int j = 0; j < abc.length(); j++) {
                    if (letra==abc.charAt(j)) {
                        if (j >= 13) {
                            vacio = vacio + abc.charAt(j+13-abc.length());
                        } else {
                            vacio = vacio + abc.charAt(j+13);
                        }
                    }
                    if (letra==ABC.charAt(j)) {
                        if (j >= 13) {
                            vacio = vacio + ABC.charAt(j+13-ABC.length());
                        } else {
                            vacio = vacio + ABC.charAt(j+13);
                        }
                    }
                }
            } else {
                vacio = vacio + cadena.charAt(i);
            }
        }
        return vacio;
    }

    public static String desxifratRot13(String cadena) {
        String vacio = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (Character.isLetter(letra)) {
                for (int j = 0; j < abc.length(); j++) {
                    if (letra==abc.charAt(j)) {
                        if (j < 13) {
                            vacio = vacio + abc.charAt(abc.length()-13+j);
                        } else {
                            vacio = vacio + abc.charAt(j-13);
                        }
                    }
                    if (letra==ABC.charAt(j)) {
                        if (j < 13) {
                            vacio = vacio + ABC.charAt(ABC.length()-13+j);
                        } else {
                            vacio = vacio + ABC.charAt(j-13);
                        }
                    }
                }
            } else {
                vacio = vacio + cadena.charAt(i);
            }
        }
        return vacio;
    }

    //
}