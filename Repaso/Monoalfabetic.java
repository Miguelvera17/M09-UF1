package Repaso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Monoalfabetic {

        
        public static final char[] alfabet = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ".toCharArray();
        public static char permutacio[];
        public static Random rand;
        public static long clauSecreta = 123456;
    
        public static void initRandom(long l){
            rand = new Random(l);
        }
    
        public static void permutaAlfabet() {
            List<Character> lChars = new ArrayList<>();
            for(char c : alfabet) lChars.add(c);
    
            Collections.shuffle(lChars, rand);
    
            permutacio = new char[alfabet.length];
    
            for (int i = 0; i < lChars.size(); i++) {
                permutacio[i] = lChars.get(i);
            }
        }
    
        public static int posicio(char[] alfa, char c) {
            for (int i = 0; i < alfa.length; i++) {
                if (alfa[i] == c) return i;
            }
            return -1;
        }
    
        public static String substitueix(String msg, boolean isXifrat) {
            StringBuilder sb = new StringBuilder();
            char desti[];
            permutaAlfabet();
            for(int i = 0; i < msg.length(); i++) {
                char c = msg.charAt(i);
                boolean isMin = Character.isLowerCase(c);
                int pos = isXifrat ? posicio(alfabet, Character.toUpperCase(c)) : posicio(permutacio,Character.toUpperCase(c));
                
                if (pos != -1) {
                    desti = isXifrat ? permutacio : alfabet;
    
                    char cCodif = isMin ? Character.toLowerCase(desti[pos]) : desti[pos];
                    sb.append(cCodif);
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    
        public static String xifraPoliAlfa(String msg) {
            return substitueix(msg, true);
        }
    
        public static String desXifraPoliAlfa(String msgXifrat) {
            return substitueix(msgXifrat, false);
        }
    
        public static void main(String[] args) {
            String msg[] = {"Test 01 arbitre, coixi, Perimetre", "Test 02 Taull, DIA, año", "Test 03 Peca, Orrius, Bovila"};
            String msgXifrat[] = new String[msg.length];
    
            System.out.println("\nXifratge\n--------");
    
            for(int i = 0; i <msg.length; i++) {
                initRandom(clauSecreta);
                msgXifrat[i] = xifraPoliAlfa(msg[i]);
                System.out.printf("%-34s -> %s%n", msg[i], msgXifrat[i]);
            }
    
            System.out.println("\nDesxifratge\n----------");
    
            for(int i = 0; i <msg.length; i++) {
                initRandom(clauSecreta);
                msgXifrat[i] = desXifraPoliAlfa(msgXifrat[i]);
                System.out.printf("%-34s -> %s%n", msgXifrat[i], msg[i]);
            }
        }
    }
    
