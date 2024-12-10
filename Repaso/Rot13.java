package Repaso;

public class Rot13 {
    
    public static final char[] mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
    public static final char[] minusculas = "abcdefghijklmnñopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdon, per tu que es?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrats\n-------");

        for(int i = 0; i< msgsXifrats.length; i++) {
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("\nDesxifrat\n---------");

        for(String msg : msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desXifraRot13(msg));
        }
    }

    public static int posicioEnArray(char[] llista, char c) {
        for (int i = 0; i < llista.length; i++) {
            if (llista[i] == c) return i;
        }
        return -1;
    }

    public static char xifraCaracterRot13(char[] llista, int pos) {
        int posChar = (pos + 13)%llista.length;
        return llista[posChar];
    }

    public static char desXifraCaracterRot13(char[] llista, int pos) {
        int posChar= ((pos-13) < 0) ? (pos-13) + llista.length : (pos-13);
        return llista[posChar];
    }

    public static String rota13(String msg, boolean dreta) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < msg.length(); i++) {
            char cOrig = msg.charAt(i);
            int pos = posicioEnArray(mayusculas, cOrig);
            char cRotat;
            if (pos != -1) {

                cRotat = dreta ? xifraCaracterRot13(mayusculas, pos) : desXifraCaracterRot13(mayusculas, pos);
            } else {
                pos = posicioEnArray(minusculas, cOrig);
                if( pos != -1 ) {
                    cRotat = dreta ? xifraCaracterRot13(minusculas, pos) : desXifraCaracterRot13(minusculas, pos);
                } else {
                    cRotat = cOrig;
                }
            }
            sb.append(cRotat);
        }
        return sb.toString();
    }

    public static String xifraRot13( String msg) {
        return rota13(msg, true);
    }

    public static String desXifraRot13(String msgXifrat) {
        return rota13(msgXifrat,false);
    }
}
