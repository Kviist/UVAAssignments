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
            sc = new Scanner(new FileReader("files/winetrading11054.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        int nbrOfCases = sc.nextInt();

        while(nbrOfCases != 0){
            long[] array = new long[nbrOfCases];

            for(int i = 0; i < nbrOfCases; i++ ){
                array[i] = sc.nextLong();
            }

            main.calculateCost(array);
            nbrOfCases = sc.nextInt();
        }
    }

    public void calculateCost(long[] array) {
        long sum = 0;
        long currentCarry = 0;
        long currentValue;
        LinkedList<String> posInts = new LinkedList<>();
        LinkedList<String> negInts = new LinkedList<>();
        boolean run;

        for(int i = 0; i < array.length; i++){
            currentValue = array[i];
            run = true;

            if(currentValue > 0){
                if(!negInts.isEmpty()) {
                    while (negInts.size() > 0 && run == true) {
                        String negValue = negInts.pop();
                        String[] values = negValue.split(",");
                        long value = Long.parseLong(values[0]);
                        long index = Long.parseLong(values[1]);

                        if (value > currentValue) {
                            sum += currentValue * (i - index);
                            value -= currentValue;
                            negInts.push(value + "," + index);
                            run = false;
                        } else if (value < currentValue) {
                            sum += value * (i - index);
                            currentValue -= value;
                        } else {
                            sum += value * (i - index);
                            run = false;
                        }
                    }

                    if(currentValue != 0 && run){
                        posInts.push(currentValue +","+ i);
                    }

                } else {
                    posInts.push(currentValue +","+ i);
                }

            } else if(currentValue < 0){
                currentValue = Math.abs(currentValue);
                if(!posInts.isEmpty()) {
                    while (posInts.size() > 0 && run == true) {
                        String posValue = posInts.pop();
                        String[] values = posValue.split(",");
                        long value = Long.parseLong(values[0]);
                        long index = Long.parseLong(values[1]);

                        if (value > currentValue) {
                            sum += currentValue * (i - index);
                            value -= currentValue;
                            posInts.push(value + "," + index);
                            run = false;
                        } else if (value < currentValue) {
                            sum += value * (i - index);
                            currentValue -= value;
                        } else {
                            sum += value * (i - index);
                            run = false;
                        }

                    }

                    if(currentValue != 0 && run){
                        negInts.push(currentValue +","+ i);
                    }
                } else {
                    negInts.push(currentValue +","+ i);
                }

            }

        }

        System.out.println(sum);
    }
}