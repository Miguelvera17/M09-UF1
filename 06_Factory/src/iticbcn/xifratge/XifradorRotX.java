package iticbcn.xifratge;

public class XifradorRotX implements Xifrador{

    public static final char[] ABC = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static final char[] ABCMAY = "AÀÁBCÇDEÈÉFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    public boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        StringBuilder s = new StringBuilder();
        if (Character.isLetter(clau.charAt(0))) {
            System.out.println("Clau de RotX ha de ser un sencer de 0 a 40");
            return null;
        }
        if (Integer.parseInt(clau) < 0 || Integer.parseInt(clau) > 40) {
            System.out.println("Clau de RotX ha de ser un sencer de 0 a 40");
            return null;
        }
        else {
            int desplazamiento = Integer.parseInt(clau);      
            for (int i = 0; i < msg.length(); i++) {
                char letra = msg.charAt(i);
                if (esLetra(letra)) {
                    for (int j = 0; j < ABC.length; j++) {
                        if (letra==ABC[j]) {
                            if (j + desplazamiento <= ABC.length-1) {
                                s.append(ABC[j+desplazamiento]);
                            } 
                            if (j + desplazamiento > ABC.length-1) {
                                s.append(ABC[j+desplazamiento-ABC.length]);
                            }
                        }
                        if (letra==ABCMAY[j]) {
                            if (j + desplazamiento <= ABCMAY.length-1) {
                                s.append(ABCMAY[j+desplazamiento]);
                            } 
                                if (j + desplazamiento > ABCMAY.length-1) {
                                    s.append(ABCMAY[j+desplazamiento-ABCMAY.length]);
                                }
                        }
                        if (letra == 'ñ') {
                            j = 13;
                            s.append(ABC[j+desplazamiento]);
                        }
                    }
                } else {
                    s.append(msg.charAt(i));
                }
            }        
        } return new TextXifrat(s.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        StringBuilder s = new StringBuilder();
        if (Character.isLetter(clau.charAt(0))) {
            System.out.println("Clau de RotX ha de ser un sencer de 0 a 40");
            return null;
        }
        if (Integer.parseInt(clau) < 0 || Integer.parseInt(clau) > 40) {
            System.out.println("Clau de RotX ha de ser un sencer de 0 a 40");
            return null;
        }
        else {    
            byte[] bytesXifrats = xifrat.getBytes();
            // Decodificar el mensaje utilizando el proceso inverso
            int desplazamiento = Integer.parseInt(clau);
            // Convertir los bytes a un String (asumiendo que están en UTF-8)
            String cadena = new String(bytesXifrats, java.nio.charset.StandardCharsets.UTF_8);
            for (int i = 0; i < cadena.length(); i++) {
                char letra = cadena.charAt(i);
                if (esLetra(letra)) {
                    for (int j = 0; j < ABC.length; j++) {
                        if (letra==ABC[j]) {
                            if (j >= desplazamiento) {
                                s.append(ABC[(j-desplazamiento)]);
                            } 
                            if (j < desplazamiento) {
                                s.append(ABC[ABC.length-desplazamiento+j]);
                            }
                        }
                        if (letra==ABCMAY[j]) {
                            if (j >= desplazamiento) {
                                s.append(ABCMAY[j-desplazamiento]);
                            } 
                            if (j < desplazamiento) {
                                s.append(ABCMAY[ABCMAY.length-desplazamiento+j]);
                            }
                        } 
                    }
                } else {
                    s.append(cadena.charAt(i));
                }
            } return s.toString();
        }
    }
}