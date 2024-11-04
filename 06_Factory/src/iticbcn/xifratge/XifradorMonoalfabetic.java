package iticbcn.xifratge;
import java.util.Collections;
import java.util.ArrayList;



public class XifradorMonoalfabetic implements Xifrador{
    
    public final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public char[] alfabetPermuta;

    public XifradorMonoalfabetic() {
        alfabetPermuta = permutaAlfabet(alfabet);
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> abclist = new ArrayList<>();
        for (char i : alfabet) {
            abclist.add(i);
        }
        Collections.shuffle(abclist);
        char [] abcPermuta = new char[abclist.size()];
        for (int i = 0; i < abclist.size(); i++) {
            abcPermuta[i] = abclist.get(i);
        }
        return abcPermuta;
    }

    
    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau == null) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < msg.length(); i++) {
                char letra = msg.charAt(i);
                if (esLetra(letra)) {
                    if (Character.isUpperCase(letra)) {
                        letra = Character.toLowerCase(letra);
                        for (int j = 0; j < alfabet.length; j++) {
                            if (letra == alfabet[j]) {
                                s.append(Character.toUpperCase(alfabetPermuta[j]));
                                break;  // Salir del bucle una vez que se encuentra la letra
                            }
                        }
                    } else {
                        for (int j = 0; j < alfabet.length; j++) {
                            if (letra == alfabet[j]) {
                                s.append(alfabetPermuta[j]);
                                break;  // Salir del bucle una vez que se encuentra la letra
                            }
                        }
                    }
                } else {
                    s.append(letra);
                }
            }
            // Convertir el String a bytes y crear un TextXifrat
            return new TextXifrat(s.toString().getBytes(java.nio.charset.StandardCharsets.UTF_8));  
        } else {
            System.out.println("Xifratxe monoalfabètic no suporta clau != null");
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            System.out.println("Xifratxe monoalfabètic no suporta clau != null");
            return null;
        } else {
            byte[] bytesXifrats = xifrat.getBytes();
            // Decodificar el mensaje utilizando el proceso inverso
            StringBuilder s = new StringBuilder();
            // Convertir los bytes a un String (asumiendo que están en UTF-8)
            String cadena = new String(bytesXifrats, java.nio.charset.StandardCharsets.UTF_8);
            for (int i = 0; i < cadena.length(); i++) {
                char letra = cadena.charAt(i);
                if (esLetra(letra)) {
                    if (Character.isUpperCase(letra)) {
                        for (int j = 0; j < alfabetPermuta.length; j++) {
                            if (Character.toLowerCase(letra) == alfabetPermuta[j]) {
                                s.append(Character.toUpperCase(alfabet[j]));
                                break; // Salir del bucle una vez que se encuentra la letra
                            }
                        }
                    } else {
                        for (int j = 0; j < alfabetPermuta.length; j++) {
                            if (letra == alfabetPermuta[j]) {
                                s.append(alfabet[j]);
                                break; // Salir del bucle una vez que se encuentra la letra
                            }
                        }
                    }
                } else {
                    s.append(letra);
                }
            }
            return s.toString();
        }
    }

    public static boolean esLetra (char letra) {
        if (Character.isLetter(letra)) {return true;}
        else {return false;}
    }
}
