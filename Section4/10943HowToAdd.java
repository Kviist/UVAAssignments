import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/howdoyouadd10943.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        String line = sc.nextLine();
        String[] split = line.split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        while(N != 0 && K != 0){
            if(K == 1){
                System.out.println("1");
            } else if(K == 2){
                System.out.println((N + 1) % 1000000);
            } else {
                long[][] array = main.calculateNumbers(N, K);
                System.out.println(array[N][K]);
            }

            line = sc.nextLine();
            split = line.split(" ");
            N = Integer.parseInt(split[0]);
            K = Integer.parseInt(split[1]);
        }
    }

    private long[][] calculateNumbers(int N, int K) {
        long[][] array = new long[N + 1][K + 1];

        for(int i = 0; i <= N; i++){
            array[i][0] = 0;
            array[i][1] = 1;
             
            for(int j = 2; j < K + 1; j++ ){
                array[i][j] = getSum(array, i, j);
            }

        }

        return array;
    }

    private long getSum(long[][] array, int sum, int nbrs) {
        long total = 0;
        for(int i = 0; i <= sum; i++){
            total += array[i][nbrs -1];
        }
        return total % 1000000;
    }


}