import java.io.*;
import java.util.LinkedList;

class Main {
    public static void main(String[] args) {
        Main main = new Main();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        int n = 0;
        int m = 0;
        String[] splitted = null;
        int testCases = 0;
        LinkedList<Integer>[] adjLists = null;

        try {
            testCases = Integer.parseInt(bf.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < testCases; i++) {
            String firstLine = null;
            try {
                firstLine = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(firstLine != null) {
                splitted = firstLine.split("\\s+");
            }

            if(splitted != null) {
                n = Integer.parseInt(splitted[0]);
                m = Integer.parseInt(splitted[1]);
            }
            adjLists = new LinkedList[n];

            for(int k = 0; k < adjLists.length; k++) {
                adjLists[k] = new LinkedList<>();
            }

            main.setUpEdges(bf, adjLists, m, main);

        }

    }

    void setUpEdges(BufferedReader bf, LinkedList<Integer>[] adjLists, int m, Main main) {
        String readLine = null;
        String[] splitted = null;
        int node1 = -1;
        int node2 = -1;

        for(int j = 0; j < m; j++ ){
            try {
                readLine = bf.readLine();
                splitted = readLine.split("\\s+");
                node1 = Integer.parseInt(splitted[0]);
                node2 = Integer.parseInt(splitted[1]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(node1 != -1 && node2 != -1) {
                adjLists[node1 - 1].add(node2 - 1);
                adjLists[node2 - 1].add(node1 - 1);
            }

            node1 = -1;
            node2 = -1;
        }

        main.findLongestPath(adjLists, main);
    }

    void findLongestPath(LinkedList<Integer>[] adjLists, Main main) {
        int currentLongest = -1;

        for(int i = 0; i < adjLists.length; i++){
            int[] result = main.explore(adjLists, i, new int[adjLists.length], main);

            int sum = 0;
            for(Integer resInt : result) {
                sum += resInt;
            }

            if(sum > currentLongest) {
                currentLongest = sum;
            }
        }

        System.out.println(currentLongest);
    }

    int[] explore(LinkedList<Integer>[] adjLists, int i, int[] visited, Main main) {
        visited[i] = 1;
        LinkedList<Integer> currentList = adjLists[i];

        for(Integer cInt : currentList){
            if(visited[cInt] == 0) {
                visited = main.explore(adjLists, cInt, visited, main);
            }
        }
        return visited;
    }
}