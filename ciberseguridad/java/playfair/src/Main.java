import java.util.Scanner;

public class Main{
    public static Scanner scan = new Scanner(System.in);
    public static String msg, key = null, encripted = "";
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
            for (int i = 0; i < (25 - temp.length()); i++) {
                if (key.indexOf(defKey.charAt(i)) == -1) {
                    key += defKey.charAt(i);
                }
            }
        } else {
            key = defKey;
        }
    }

    public static void cipher() {
        System.out.print("**Deje campo en blanco para usar alfabeto americano sin w**\nIngrese llave: ");
        key = scan.nextLine();
        makeKey(key);
        System.out.println("Llave: "+key);
        System.out.print("Ingrese texto para encriptar: ");
        msg = scan.nextLine();
        if (msg.length() % 2 != 0) msg += "x";

        System.out.println("\n"+key.charAt(0)+" "+key.charAt(1)+" "+key.charAt(2)+" "+key.charAt(3)+" "+key.charAt(4));
        System.out.println(key.charAt(5)+" "+key.charAt(6)+" "+key.charAt(7)+" "+key.charAt(8)+" "+key.charAt(9));
        System.out.println(key.charAt(10)+" "+key.charAt(11)+" "+key.charAt(12)+" "+key.charAt(13)+" "+key.charAt(14));
        System.out.println(key.charAt(15)+" "+key.charAt(16)+" "+key.charAt(17)+" "+key.charAt(18)+" "+key.charAt(19));
        System.out.println(key.charAt(20)+" "+key.charAt(21)+" "+key.charAt(22)+" "+key.charAt(23)+" "+key.charAt(24)+"\n");

        for (int i = 0; i < msg.length(); i+=2) {
            char l = msg.charAt(i), r = msg.charAt(i+1), first = l; //represents the left and right letter from a straight array perspective
            if(key.indexOf(msg.charAt(i)) > key.indexOf(msg.charAt(i+1))){
                r = msg.charAt(i);
                l = msg.charAt(i+1);
            }

            int linediff = (key.indexOf(r) / 5 - 1) - (key.indexOf(l) / 5);
            if(linediff < 0) linediff = 0;

            //case checker
            String caso = "";
            if (key.indexOf(r) == key.indexOf(l) + 5 + (linediff * 5)) {

                caso = "vertical";
                if (first == l) {
                    encripted += key.charAt(key.indexOf(l)+5);
                    encripted += ((key.indexOf(r)+5 > key.length() - 1)) ? key.charAt(4 - ((key.length() - 1) - key.indexOf(r))) : key.charAt(key.indexOf(r)+5);
                } else {
                    encripted += ((key.indexOf(r)+5 > key.length() - 1)) ? key.charAt(4 - ((key.length() - 1) - key.indexOf(r))) : key.charAt(key.indexOf(r)+5);
                    encripted += key.charAt(key.indexOf(l)+5);
                }
                
            } else if (key.indexOf(l) / 5 == key.indexOf(r) / 5) {
                
                caso = "horizontal";
                if (first == l) {
                    encripted += (key.indexOf(l)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(l)+1);
                    encripted += (key.indexOf(r)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(r)+1);
                } else {
                    encripted += (key.indexOf(r)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(r)+1);
                    encripted += (key.indexOf(l)+1 > key.length() - 1) ? "a" : key.charAt(key.indexOf(l)+1);
                }

            } else {
                caso = "cuadrado";
                if (first == l) {
                    encripted += key.charAt(key.indexOf(r)-5-(linediff * 5));
                    encripted += key.charAt(key.indexOf(l)+5+(linediff * 5));
                } else {
                    encripted += key.charAt(key.indexOf(l)+5+(linediff * 5));
                    encripted += key.charAt(key.indexOf(r)-5-(linediff * 5));
                } 
            }
            //System.out.println("Left: "+l+" Right: "+r+" Separación: "+linediff+" Caso: "+caso);
        }
        System.out.println("Mensaje encriptado: "+encripted);

    }

    public static void decipher() {

    }
    public static void main(String[] args) {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#########################");
        System.out.println("#    Playfair cipher    #");
        System.out.println("#########################\n");
        cipher();
    }
}