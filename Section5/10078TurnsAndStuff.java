import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

class Main {
    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);

        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/theArtGallery10078.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        int N = sc.nextInt();

        while(N != 0){
            Point[] points = new Point[N + 2];

            for(int i = 0; i < N; i++){
                points[i] = new Point(sc.nextInt(), sc.nextInt());
            }

            points[N] = points[0];
            points[N+1] = points[1];

            String res = "No";
            double last = 0;

            for (int j = 0; j < points.length - 2; j++) {
                double area = area(points[j], points[j + 1], points[j + 2]);

                if (!(area >= 0 && last >= 0) && !(area <= 0 && last <= 0)) {
                    res = "Yes";
                }

                last = area;
            }

            System.out.println(res);

            N = sc.nextInt();

        }
    }

    static double area(Point p1, Point p2, Point p3){
        return ((p3.x - p2.x) * (p1.y - p2.y) - (p3.y - p2.y) * (p1.x - p2.x));
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}