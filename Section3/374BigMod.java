import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/374BigMod.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */
        while (sc.hasNextInt()) {
            int b = sc.nextInt();
            int p = sc.nextInt();
            int mod = sc.nextInt();

            main.calculate(b,Integer.toBinaryString(p),mod);

            if(sc.hasNextLine()){
                sc.nextLine();
            }
        }

    }

    public void calculate(int base, String power, int modulus){
        long sum = 1;
        long x = base;

        for(int i = (power.length()-1); i > -1 ; i--){
            if(power.charAt(i) == '1'){
                sum = (sum * x) % modulus;
            }
            x = (x * x) % modulus;
        }
        System.out.println(sum);
    }

    public long getSum(long sum, int base, int modulus){
        return (sum * base) % modulus;
    }

}