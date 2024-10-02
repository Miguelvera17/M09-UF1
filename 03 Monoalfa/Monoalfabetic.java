import java.util.Collections;
import java.util.ArrayList;

public class Monoalfabetic {
    public static final char[] alfabet = "aàábcçdeèéfghiíïjklmnñoóòpqrstuúüvwxyz".toCharArray();
    public static char[] abcPermuta;

    public static void main(String[] args) {
        char[] a = permutaAlfabet(alfabet);
        for (int i = 0 ; i < alfabet.length; i++) {
            System.out.println(a[i]);
        }
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> abclist = new ArrayList<>();
        abcPermuta = new char[abclist.size()];
        for (char i : alfabet) {
            abclist.add(i);
        }
        Collections.shuffle(abclist);
        for (int i = 0; i < abclist.size(); i++) {
            abcPermuta[i] = abclist.get(i);
        }
        return abcPermuta;
    }
}
