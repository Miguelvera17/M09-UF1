import java.util.Scanner;

public class Rot13 {

    public static final String abc = "abcdefghijklmnopqrstuvwxyz";

    public static void main (String[] args){
        Scanner scann = new Scanner(System.in);
        System.out.println("Introduzca la cadena");
        String cadena = scann.nextLine();

        System.out.println(xifratRot13(cadena));
        //System.out.println(desxifratRot13(cadena));
    }

    public static String xifratRot13 (String cadena) {
        String vacio = "";
        for (int i = 0; i < cadena.length(); i++) {
            for (int j = 0; j < abc.length()-1; j++) {
                char letra = cadena.charAt(i);
                char letra2 = abc.charAt(j);
                if (letra==letra2) {
                    if (j >= 13) {
                        vacio = vacio + abc.charAt(j+13-abc.length());
                    } else {
                        vacio = vacio + abc.charAt(j+13);
                    }
                }
            }
        }
        return vacio;
    }

    //public static String desxifratRot13(String cadena) {

    //}
}