package closestPair10245;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //Main main = new Main();

        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/closestPair10245.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */



        if(sc.hasNextInt()) {
            int N = sc.nextInt();

            while (N > 0) {

                double[] xArray = new double[N];
                double[] yArray = new double[N];

                for (int i = 0; i < N; i++) {
                    xArray[i] = sc.nextDouble();
                    yArray[i] = sc.nextDouble();
                }

                if (N != 1) {
                    double minDist = Double.MAX_VALUE;
                    for(int i = 0; i < xArray.length - 1; i++){
                        for (int j = i + 1; j < xArray.length; j++){
                            double distance = distanceBetween(xArray[i], yArray[i], xArray[j], yArray[j]);

                            if(distance < minDist){
                                minDist = distance;
                            }
                        }
                    }


                    if (minDist >= 10000) {
                        System.out.println("INFINITY");
                    } else {
                        System.out.printf("%.4f", minDist);
                        System.out.println();
                    }
                } else {
                    System.out.println("INFINITY");
                }

                if(sc.hasNextInt()) {
                    N = sc.nextInt();
                }
            }
        }
    }

    static double distanceBetween(double p1X, double p1Y, double p2X, double p2Y){
        return (double) Math.sqrt(Math.pow(p2X - p1X, 2) + Math.pow(p2Y - p1Y, 2));
    }

}

/*

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/closestPair10245.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int N = Integer.parseInt(sc.nextLine());

        while(N > 0){
            Point[] point = new Point[N];

            for(int i = 0; i < N; i++){
                String[] splittedIn = sc.nextLine().split(" ");
                point[i] = new Point(Double.parseDouble(splittedIn[0]), Double.parseDouble(splittedIn[1]));
            }

            Arrays.sort(point);
            Double res = closestPoint(point, point.length);

            if(res >= 10000){
                System.out.println("INFINITY");
            } else {
                System.out.printf("%.4f", res);
                System.out.println();
            }

            N = Integer.parseInt(sc.nextLine());
        }
    }

    static Double closestPoint(Point[] point, int n) {
        if(n < 4){
            return solve(point, n);
        }
        double midX = point[n/2].getX();
        double leftMin = closestPoint(point, n/2);

        Point[] rightHaftPoints = Arrays.copyOfRange(point, (point.length / 2) +1, point.length);
        double rightMin = closestPoint(rightHaftPoints, rightHaftPoints.length);
        double min = Math.min(leftMin, rightMin);


        LinkedList<Point> possibleCloser = new LinkedList<>();
        for(int i = 0; i < n; i++){
            double dis = Math.abs(point[i].getX() - midX);
            if(dis < min){
                possibleCloser.add(point[i]);
            }
        }

        Point[] possibleArray = new Point[possibleCloser.size()];
        for(int b = 0; b < possibleArray.length; b++){
            possibleArray[b] = possibleCloser.get(b);
        }

        return closerExists(possibleArray, min);
    }

    static double closerExists(Point[] possibleCloser, double min) {
        double closest = min;

        Arrays.sort(possibleCloser, (new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if(o1.getX() > o2.getX()){
                    return 1;
                } else if(o1.getX() < o2.getX()){
                    return -1;
                }
                return 0;
            }
        }));


        for(int i = 0; i < possibleCloser.length - 1; i++){
            Point current = possibleCloser[i];
            for(int j = i + 1; j < possibleCloser.length; j++){
                double dis = distanceBetween(current, possibleCloser[j]);
                if(dis < closest){
                    closest = dis;
                }
            }
        }

        return closest;
    }

    static double solve(Point[] point, int n) {
        double minDis = Double.MAX_VALUE;

        for(int i = 0; i < n; i++){
            for(int k = i + 1; k < n; k++){
                double dis = distanceBetween(point[i], point[k]);
                if( dis < minDis){
                    minDis = dis;
                }
            }
        }

        return minDis;
    }

    static double distanceBetween(Point p1, Point p2){
        return (double) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

    static class Point implements Comparable{
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }


        @Override
        public int compareTo(Object o) {
            double x2 = ((Point)o).getX();
            if(getX() > x2){
                return 1;
            } else if(getX() < x2){
                return -1;
            }
            return 0;
        }
    }
}
*/
