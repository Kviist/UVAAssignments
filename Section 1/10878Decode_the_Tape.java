import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        while(sc.hasNextLine()){
            String sequence = sc.nextLine();
            if(sequence.charAt(0) == '|'){
                int bitNumber = 0;
                int index = 1;
                for(int i = 4; i > -1; i--){
                    char isThere = sequence.charAt(index++);
                    if(isThere == 'o') {
                        bitNumber += Math.pow(2, i);

                    }
                }
                bitNumber *= 8;
                index = 7;
                for(int j = 2; j > -1; j--) {
                    char isThere = sequence.charAt(index++);

                    if(isThere == 'o') {
                        bitNumber += Math.pow(2, j);
                    }
                }
                System.out.print((char)bitNumber);
            }
        }
    }
}