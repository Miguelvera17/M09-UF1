package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{

    public static final char[] ABC = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static final char[] ABCMAY = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    public boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            String s = "";
            int desplazamiento = Integer.parseInt(clau);
            for (int i = 0; i < msg.length(); i++) {
                char letra = msg.charAt(i);
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
                } else {s = s + msg.charAt(i);}
            return new TextXifrat(s);
            }        
        } else {
            System.out.println("Xifratxe monoalfabètic no suporta clau != null");
            return null;
        }
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
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
}