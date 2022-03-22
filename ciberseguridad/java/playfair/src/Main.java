import java.util.Scanner;

public class Main{
    public static Scanner scan = new Scanner(System.in);
    public static String msg, key = null, encrypted = "", decrypted = "";
    public static String defKey = "abcdefghijklmnopqrstuvxyz";

    public static void makeKey(String val) {
        String temp = "";
        if(val != null){
            for (int i = 0; i < val.length(); i++) {
                if(temp.indexOf(val.charAt(i)) == -1){
                    temp += val.charAt(i);
                }
            }
            key = temp;
            for (int i = 0; i < 25; i++) {
                if (key.indexOf(defKey.charAt(i)) == -1) {
                    key += defKey.charAt(i);
                }
            }
        } else {
            key = defKey;
        }
    }

    public static void printKey() {
        for (int i = 0; i < 25; i++) {
            if (i % 5 == 0) {
                System.out.print("\n "+key.charAt(i)+" ");
            } else {
                System.out.print(key.charAt(i)+" ");
            }    
            if (i == 24) System.out.println("\n");
        }
    }

    public static void cipher() {
        System.out.print("\n**Deje campo en blanco para usar alfabeto americano sin w**\nIngrese llave: ");
        key = scan.nextLine();
        makeKey(key);
        printKey();
        System.out.println("Llave: "+key);
        System.out.print("Ingrese texto para encriptar: ");
        encrypted = "";
        msg = scan.nextLine();
        if (msg.length() % 2 != 0) msg += "x";


        for (int i = 0; i < msg.length(); i+=2) {
            char l = msg.charAt(i), r = msg.charAt(i+1), first = l; //represents the left and right letter from a straight array perspective
            if(key.indexOf(msg.charAt(i)) > key.indexOf(msg.charAt(i+1))){
                r = msg.charAt(i);
                l = msg.charAt(i+1);
            }

            int linediff = (key.indexOf(r) / 5 - 1) - (key.indexOf(l) / 5);
            if(linediff < 0) linediff = 0;

            //case checker
            if (key.indexOf(r) == key.indexOf(l) + 5 + (linediff * 5)) {

                if (first == l) {
                    encrypted += key.charAt(key.indexOf(l)+5);
                    encrypted += ((key.indexOf(r)+5 > key.length() - 1)) ? key.charAt(4 - ((key.length() - 1) - key.indexOf(r))) : key.charAt(key.indexOf(r)+5);
                } else {
                    encrypted += ((key.indexOf(r)+5 > key.length() - 1)) ? key.charAt(4 - ((key.length() - 1) - key.indexOf(r))) : key.charAt(key.indexOf(r)+5);
                    encrypted += key.charAt(key.indexOf(l)+5);
                }
                
            } else if (key.indexOf(l) / 5 == key.indexOf(r) / 5) {
                
                if (first == l) {
                    encrypted += (key.indexOf(l)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(l)+1);
                    encrypted += (key.indexOf(r)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(r)+1);
                } else {
                    encrypted += (key.indexOf(r)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(r)+1);
                    encrypted += (key.indexOf(l)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(l)+1);
                }

            } else {

                if (first == l) {
                    encrypted += key.charAt(key.indexOf(r)-5-(linediff * 5));
                    encrypted += key.charAt(key.indexOf(l)+5+(linediff * 5));
                } else {
                    encrypted += key.charAt(key.indexOf(l)+5+(linediff * 5));
                    encrypted += key.charAt(key.indexOf(r)-5-(linediff * 5));
                } 

            }
        }
        System.out.println("\nMensaje encriptado: "+encrypted+"\n");
    }

    public static void decipher() {
        System.out.print("\nIngrese llave para desencriptar: ");
        key = scan.nextLine();
        System.out.print("Ingrese texto para desencriptar: ");
        decrypted = "";
        msg = scan.nextLine();

        for (int i = 0; i < msg.length(); i+=2) {
            char l = msg.charAt(i), r = msg.charAt(i+1), first = l; //represents the left and right letter from a straight array perspective
            if(key.indexOf(msg.charAt(i)) > key.indexOf(msg.charAt(i+1))){
                r = msg.charAt(i);
                l = msg.charAt(i+1);
            }

            int linediff = (key.indexOf(r) / 5 - 1) - (key.indexOf(l) / 5);
            if(linediff < 0) linediff = 0;

            //case checker
            if (key.indexOf(r) == key.indexOf(l) + 5 + (linediff * 5)) {
                //Caso vertical
                if (first == l) {
                    decrypted += ((key.indexOf(l)-5 < key.length() - 1)) ? key.charAt(((key.length() - 1) - (4 - key.indexOf(l)))) : key.charAt(key.indexOf(l)+5);
                    decrypted += key.charAt(key.indexOf(r)-5);
                } else {
                    decrypted += key.charAt(key.indexOf(r)+5);
                    decrypted += ((key.indexOf(l)-5 < key.length() - 1)) ? key.charAt(((key.length() - 1) - (4 - key.indexOf(l)))) : key.charAt(key.indexOf(l)+5);
                }
                
            } else if (key.indexOf(l) / 5 == key.indexOf(r) / 5) {
                //Caso horizontal
                if (first == l) {
                    decrypted += (key.indexOf(l)-1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(l)-1);
                    decrypted += (key.indexOf(r)-1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(r)-1);
                } else {
                    decrypted += (key.indexOf(r)-1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(r)-1);
                    decrypted += (key.indexOf(l)-1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(l)-1);
                }

            } else {
                //Caso cuadrado
                if (first == l) {
                    decrypted += key.charAt(key.indexOf(r)-5-(linediff * 5));
                    decrypted += key.charAt(key.indexOf(l)+5+(linediff * 5));
                } else {
                    decrypted += key.charAt(key.indexOf(l)+5+(linediff * 5));
                    decrypted += key.charAt(key.indexOf(r)-5-(linediff * 5));
                } 
            }
        }
        System.out.println("\nMensaje encriptado: "+decrypted+"\n");
    }
    
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#########################");
        System.out.println("#    Playfair cipher    #");
        System.out.println("#########################\n");

        while(true) {

            System.out.println("Seleccione un opción: ");
            System.out.println("[1] Cipher");
            System.out.println("[2] Decipher");
            System.out.println("[3] Exit\n");
            String input = scan.nextLine();

            if (input.equals("1")) {
                cipher();
            } else if (input.equals("2")) {
                decipher();
            } else {
                break;
            }
        }
    }
}