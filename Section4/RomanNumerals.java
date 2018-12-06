import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/romannumerals.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        String rn1 = "";
        String rn2 = "";
        String rnRes = "";
        String line = sc.nextLine();
        HashMap<Character, Integer> values = new HashMap<>();
        values.put('I', 1);
        values.put('V', 5);
        values.put('X', 10);
        values.put('L', 50);
        values.put('C', 100);
        values.put('D', 500);
        values.put('M', 1000);


        while(!line.equals("#")){
            String[] splitted = line.split("\\+");
            rn1 = splitted[0];
            splitted = splitted[1].split("=");
            rn2 = splitted[0];
            rnRes = splitted[1];

            System.out.print(correct(rn1, rn2, rnRes, values) + " ");
            System.out.println(solve(rn1, rn2, rnRes));
            line = sc.nextLine();
        }

    }

    private static String correct(String rn1, String rn2, String rnRes, HashMap<Character, Integer> values) {
        int nbr1 = getRoman(rn1, values);
        int nbr2 = getRoman(rn2, values);
        int res = getRoman(rnRes, values);

        if(nbr1 + nbr2 == res){
            return "Correct";
        } else {
            return "Incorrect";
        }
    }

    private static int getRoman(String string, HashMap<Character, Integer> values) {
        int res = 0;
        for(int i = 0; i < string.length() - 1; i++){
            if(values.get(string.charAt(i)) < values.get(string.charAt(i+1)) ){
                res -= values.get(string.charAt(i));
            } else {
                res += values.get(string.charAt(i));
            }
        }

        res += values.get(string.charAt(string.length() - 1));
        return res;
    }

    static String solve(String rn1, String rn2, String rnRes) {
        HashMap<Character, Integer> values = new HashMap<>();
        HashMap<Character, Integer> arrayPos = new HashMap<>();
        int[] array;
        char c;
        int counter = 0;

        for (int i = 0; i < rn1.length(); i++) {
            c = rn1.charAt(i);
            if (!values.containsKey(c)) {
                values.put(c, counter++);
            }
        }

        for (int j = 0; j < rn2.length(); j++) {
            c = rn2.charAt(j);
            if (!values.containsKey(c)) {
                values.put(c, counter++);
            }
        }

        for (int k = 0; k < rnRes.length(); k++) {
            c = rnRes.charAt(k);
            if (!values.containsKey(c)) {
                values.put(c, counter++);
            }
        }

        array = new int[values.size()];

        Iterator it = values.entrySet().iterator();
        int count = 0;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            array[count] = 1;
            arrayPos.put(((Character) pair.getKey()), count++);
        }

        int resCount = 0;
        int arrayLength = array.length;


        outerloop:
        for (int I = 0; I < 10 &&  arrayLength > 0; I++) {
            array[0] = I;
            for (int V = 0; V < 10 && arrayLength > 1; V++) {
                if(V == I){
                    continue;
                }
                array[1] = V;
                for (int X = 0; X < 10 && arrayLength > 2; X++) {
                    if(X == V || X == I){
                        continue;
                    }
                    array[2] = X;
                    for (int L = 0; L < 10 && arrayLength > 3; L++) {
                        if(L == I || L == V || L == X){
                            continue;
                        }
                        array[3] = L;
                        for (int C = 0; C < 10 && arrayLength > 4; C++) {
                            if(C == I || C == V || C == X || C == L){
                                continue;
                            }
                            array[4] = C;
                            for (int D = 0; D < 10 && arrayLength > 5; D++) {
                                if(D == I || D == V || D == X || D == L || D == C){
                                    continue;
                                }
                                array[5] = D;
                                ounterM:
                                for (int M = 0; M < 10 && arrayLength > 6; M++) {
                                    if(M == I || M == V || M == X || M == L || M == C || M == D){
                                        continue;
                                    }
                                    array[6] = M;

                                    if(array.length == 7){
                                       boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                                       if(res){
                                           resCount++;
                                           if(resCount > 1){
                                               break outerloop;
                                           }
                                       }
                                    }
                                }
                                if(array.length == 6){
                                    boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                                    if(res){
                                        resCount++;
                                        if(resCount > 1){
                                            break outerloop;
                                        }
                                    }
                                }
                            }
                            if(array.length == 5){
                                boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                                if(res){
                                    resCount++;
                                    if(resCount > 1){
                                        break outerloop;
                                    }
                                }
                            }
                        }
                        if(array.length == 4){
                            boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                            if(res){
                                resCount++;
                                if(resCount > 1){
                                    break outerloop;
                                }
                            }
                        }
                    }
                    if(array.length == 3){
                        boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                        if(res){
                            resCount++;
                            if(resCount > 1){
                                break outerloop;
                            }
                        }
                    }
                }
                if(array.length == 2){
                    boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                    if(res){
                        resCount++;
                        if(resCount > 1){
                            break outerloop;
                        }
                    }
                }
            }
            if(array.length == 1){
                boolean res =  calculate(array, arrayPos,  rn1, rn2, rnRes);

                if(res){
                    resCount++;
                    if(resCount > 1){
                        break outerloop;
                    }
                }
            }
        }
        if(resCount == 0){
            return "impossible";
        } else if(resCount == 1){
            return "valid";
        } else {
            return "ambiguous";
        }
    }

    private static boolean calculate(int[] array, HashMap<Character, Integer> arrayPos,  String rn1, String rn2, String rnRes) {
        String n1 = "";
        for(int q = 0; q < rn1.length(); q++){
            n1 += array[arrayPos.get(rn1.charAt(q))];
        }

        String n2 = "";
        for(int w = 0; w < rn2.length(); w++){
            n2 += array[arrayPos.get(rn2.charAt(w))];
        }

        String r = "";
        for(int e = 0; e < rnRes.length(); e++){
            r += array[arrayPos.get(rnRes.charAt(e))];
        }

        if(n1.charAt(0) != '0' && n2.charAt(0) != '0' && r.charAt(0) != '0') {
            long nbr1 = Long.parseLong(n1);
            long nbr2 = Long.parseLong(n2);
            long res = Long.parseLong(r);

            if ((nbr1 + nbr2) == res) {
                return true;
            }

            return false;
        } else {
            return false;
        }
    }


}