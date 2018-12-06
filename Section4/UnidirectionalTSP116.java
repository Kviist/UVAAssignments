import java.util.*;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/unidirTSP.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */


        while (sc.hasNext()) {
            int rows = sc.nextInt();
            int cols = sc.nextInt();

            if (rows == 0 || cols == 0) {

            } else if (rows == 1 || cols == 1) {
                long[][] array = main.construct(rows, cols, sc);
                main.handleSpecial(array);
            } else {
                long[][] array = main.construct(rows, cols, sc);
                main.findPaths(array);
            }


        }
    }

    private void handleSpecial(long[][] array) {
        if (array.length > 1) {
            int pos = -1;
            long value = Long.MAX_VALUE;

            for (int i = 0; i < array.length; i++) {
                if (array[i][0] < value) {
                    value = array[i][0];
                    pos = i;
                }
            }
            System.out.println(pos + 1);
            System.out.println(value);
        } else {
            long sum = 0;

            System.out.print("1");
            sum += array[0][0];

            for (int i = 1; i < array[0].length; i++) {
                System.out.print(" 1");
                sum += array[0][i];
            }
            System.out.println();
            System.out.println(sum);

        }
    }

    long[][] construct(int rows, int cols, Scanner sc) {
        long[][] array = new long[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = sc.nextLong();
            }
        }

        return array;
    }

    void findPaths(long[][] array){
        int rows = array.length;
        int cols = array[0].length;
        long[][] sum = new long[rows][cols];

        for(int w = 0; w < rows; w++) {
            int c = cols - 1;
            for (int q = 0; q < cols; q++) {
                sum[w][c--] = array[w][q];
            }
        }

        for(int i = 1; i < cols; i++){
            for(int j = 0; j < rows; j++ ){
                int over = (j == 0) ? rows - 1 : j - 1;
                int on = j;
                int under = (j == rows - 1) ? 0 : j + 1;

                long nbr1 = sum[over][i-1];
                long nbr2 = sum[on][i-1];
                long nbr3 = sum[under][i-1];

                sum[j][i] = Math.min(Math.min(nbr1, nbr2), nbr3) + sum[j][i];
            }
        }
        long sumTop = Long.MAX_VALUE;
        String path = "";
        int row = -1;
        for(int k = 0; k < rows; k++){
            if(sum[k][cols-1] < sumTop){
                sumTop = sum[k][cols-1];
                row = k;
            }
        }

        path += row + 1 + " ";
        for(int e = cols - 1; e > 0; e--){
            int over = (row == 0) ? rows - 1 : row - 1;
            int on = row;
            int under = (row == rows - 1) ? 0 : row + 1;

            long nbr1 = sum[over][e-1];
            long nbr2 = sum[on][e-1];
            long nbr3 = sum[under][e-1];

            LinkedList<Node> nodes = new LinkedList<>();
            nodes.add(new Node(nbr1, over));
            nodes.add(new Node(nbr2, on));
            nodes.add(new Node(nbr3, under));

            nodes.sort(new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if(o1.getValue() == o2.getValue()){
                        return o1.getFrom() - o2.getFrom();
                    }

                    return (int) (o1.getValue() - o2.getValue());
                }
            });

            long value = Long.MAX_VALUE;
            int from = -1;
            for(int i = 0; i < nodes.size(); i++){
                Node node = nodes.get(i);

                if(node.getValue() < value){
                    value = node.getValue();
                    row = node.getFrom();
                }
            }
            path += (row + 1 + " ");
        }

        path = path.substring(0, path.length() - 1);
        System.out.println(path);
        System.out.println(sumTop);

    }

    class Node {
        long value;
        int from;

        public Node(long value, int from) {
            this.value = value;
            this.from = from;
        }

        public long getValue() {
            return value;
        }

        public int getFrom() {
            return from;
        }
    }
}
