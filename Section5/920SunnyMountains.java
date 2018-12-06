import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        /*
        Main main = new Main();
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/closestPair10245.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        int N = Integer.parseInt(sc.nextLine());

        while(N > 0){
            Point[] point = new Point[N];

            for(int i = 0; i < N; i++){
                String[] splittedIn = sc.nextLine().split(" ");
                point[i] = new Point(Float.parseFloat(splittedIn[0]), Float.parseFloat(splittedIn[1]));
            }

            Arrays.sort(point);
            float res = closestPoint(point, point.length);

            if(res >= 10000){
                System.out.println("INFINITY");
            } else {
                System.out.printf("%.4f", res);
                System.out.println();
            }

            N = Integer.parseInt(sc.nextLine());
        }
    }

    static float closestPoint(Point[] point, int n) {
        if(n < 4){
            return solve(point, n);
        }
        float midX = point[n/2].getX();
        float leftMin = closestPoint(point, n/2);

        Point[] rightHaftPoints = Arrays.copyOfRange(point, (point.length / 2) +1, point.length);
        float rightMin = closestPoint(rightHaftPoints, rightHaftPoints.length);
        float min = Math.min(leftMin, rightMin);


        LinkedList<Point> possibleCloser = new LinkedList<>();
        for(int i = 0; i < n; i++){
            float dis = Math.abs(point[i].getX() - midX);
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

    static float closerExists(Point[] possibleCloser, float min) {
        float closest = min;

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
                float dis = distanceBetween(current, possibleCloser[j]);
                if(dis < closest){
                    closest = dis;
                }
            }
        }

        return closest;
    }

    static float solve(Point[] point, int n) {
        float minDis = Float.MAX_VALUE;

        for(int i = 0; i < n; i++){
            for(int k = i + 1; k < n; k++){
                float dis = distanceBetween(point[i], point[k]);
                if( dis < minDis){
                    minDis = dis;
                }
            }
        }

        return minDis;
    }

    static float distanceBetween(Point p1, Point p2){
        return (float) Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

    static class Point implements Comparable{
        float x;
        float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }


        @Override
        public int compareTo(Object o) {
            float x2 = ((Point)o).getX();
            if(getX() > x2){
                return 1;
            } else if(getX() < x2){
                return -1;
            }
            return 0;
        }
    }
}