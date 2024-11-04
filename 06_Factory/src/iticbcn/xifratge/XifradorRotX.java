package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{

    public final String ABC = "abcdefghijklmnopqrstuvwxyz";
    public final String ABCMAY = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public String xifraRotX (String cadena) {
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
    
    public String desxifraRotX (String cadena) {
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