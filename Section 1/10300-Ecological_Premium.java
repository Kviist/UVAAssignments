import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double testCases = sc.nextInt();
        double farmers;
        double farmYardSize;
        double nbrOfAnimals;
        double envFriend;

        for(int j = 0; j < testCases; j++){
            double totalCost = 0;
            farmers = sc.nextInt();
            for(int i = 0; i < farmers; i++) {
                farmYardSize = sc.nextInt();
                nbrOfAnimals = sc.nextInt();
                envFriend = sc.nextInt();

                double avgSpace = farmYardSize / nbrOfAnimals;
                totalCost +=  (avgSpace * envFriend) * nbrOfAnimals;
            }
            System.out.println((int)totalCost);
        }

    }
}