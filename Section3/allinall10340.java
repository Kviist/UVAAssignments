import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/allinall10340.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        String nextline;
        String[] split;
        StringBuilder s;
        StringBuilder t;
        while(sc.hasNextLine()){
            nextline = sc.nextLine();
            split = nextline.split(" ");
            s = new StringBuilder(split[0]);
            t = new StringBuilder(split[1]);

            int sCounter = 0;
            for(int i = 0; i < t.length(); i++){
                if(s.charAt(sCounter) == t.charAt(i)){
                    sCounter++;
                } else {
                    t.deleteCharAt(i);
                    i--;
                }

                if(sCounter == (s.length())){
                    break;
                }
            }

            if(sCounter == (s.length())){
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}