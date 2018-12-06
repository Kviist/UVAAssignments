import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/839Input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */


        int WL = -1;
        int WR = -1;
        int DL = -1;
        int DR = -1;
        int N = sc.nextInt();
        sc.nextLine();

        for(int i = 0; i < N; i++){
            WL = sc.nextInt();
            DL = sc.nextInt();
            WR = sc.nextInt();
            DR = sc.nextInt();

            if(WL == 0){
                WL = resolve(sc);
            }
            if(WR == 0){
                WR = resolve(sc);
            }

            boolean even = (WL * DL) == (WR * DR);

            if(WL == -1 || WR == -1){
                System.out.println("NO");
            }else if(even){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

            if(i < N-1){
                System.out.println();
                sc.nextLine();
            }

        }

    }

    static int resolve(Scanner sc){
        int WL = sc.nextInt();
        int DL = sc.nextInt();
        int WR = sc.nextInt();
        int DR = sc.nextInt();

        if(WL == 0){
            WL = resolve(sc);
        }

        if(WR == 0){
            WR = resolve(sc);
        }

        if((WL * DL) != (WR * DR)){
            return -1;
        }

        return WL + WR;

    }
}