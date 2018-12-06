import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int towers = sc.nextInt();
        int setCounter = 1;
        LinkedList<Integer> firstBuiltTowers;
        
        while(towers != 0) {
            int totalBricks = 0;
            firstBuiltTowers = new LinkedList<Integer>();
            
            for(int i = 0; i < towers; i++) {
                int towerHeight = sc.nextInt();
                totalBricks += towerHeight;
                firstBuiltTowers.add(towerHeight);
            }
            
            int shouldHaveHeight = totalBricks / towers;
            int setOutput = 0;
            for(int tower: firstBuiltTowers) {
                if(tower < shouldHaveHeight) {
                    setOutput += shouldHaveHeight - tower;
                } else if(tower > shouldHaveHeight) {
                    setOutput += tower - shouldHaveHeight;
                }
            }
            
            System.out.println("Set #" + setCounter++);
            System.out.println("The minimum number of moves is " + (setOutput / 2) + ".");
            System.out.println();
            towers = sc.nextInt();
        }
    }
}