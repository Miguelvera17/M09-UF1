package Repaso;

public class RotX {

    public static final char[] mayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
    public static final char[] minusculas = "abcdefghijklmnñopqrstuvwxyz".toCharArray();

    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdon, per tu que es?"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("\nXifrats\n-------");

        for(int i = 0; i< msgsXifrats.length; i++) {
            msgsXifrats[i] = xifraRotX(msgs[i], i*2);
            System.out.printf("(%d)-%-21s => %s%n",i*2, msgs[i], msgsXifrats[i]);
        }
        System.out.println("\nDesxifrat\n---------");

        for(int i = 0; i< msgs.length; i++) {
            System.out.printf("(%d)-%-21s => %s%n", i*2, msgsXifrats[i], desXifraRotX(msgsXifrats[i], i*2));
        }

        forcaBrutaRotX(msgsXifrats[3])  ;
    }

    public static int posicioEnArray(char[] llista, char c) {
        for (int i = 0; i < llista.length; i++) {
            if (llista[i] == c) return i;
        }
        return -1;
    }

    public static char xifraCaracterRotX(char[] llista, int pos, int rot) {
        int posChar = (pos + rot)%llista.length;
        return llista[posChar];
    }

    public static char desXifraCaracterRotX(char[] llista, int pos, int rot) {
        int posChar= ((pos-rot) < 0) ? (pos-rot) + llista.length : (pos-rot);
        return llista[posChar];
    }

    public static String rotaX(String msg, int rot, boolean dreta) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < msg.length(); i++) {
            char cOrig = msg.charAt(i);
            int pos = posicioEnArray(mayusculas, cOrig);
            char cRotat;
            if (pos != -1) {

                cRotat = dreta ? xifraCaracterRotX(mayusculas, pos, rot) : desXifraCaracterRotX(mayusculas, pos, rot);
            } else {
                pos = posicioEnArray(minusculas, cOrig);
                if( pos != -1 ) {
                    cRotat = dreta ? xifraCaracterRotX(minusculas, pos, rot) : desXifraCaracterRotX(minusculas, pos, rot);
                } else {
                    cRotat = cOrig;
                }
            }
            sb.append(cRotat);
        }
        return sb.toString();
    }

    public static String xifraRotX( String msg, int rot) {
        return rotaX(msg, rot, true);
    }

    public static String desXifraRotX(String msgXifrat, int rot) {
        return rotaX(msgXifrat,rot, false);
    }

    public static void forcaBrutaRotX(String msgX) {
        System.out.println("Missatge xifrat: " + msgX);
        System.out.println("-----------------");
        
        for(int i = 0; i < mayusculas.length; i++) {
            System.out.printf("(%d)-->%s\n", i,desXifraRotX(msgX, i));
        }
    }
}
