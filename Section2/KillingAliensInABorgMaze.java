import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        int WALL = 0;
        int ALIEN = 1;
        int START = 2;
        int UNVISITED = 3;
        int VISITED = 4;

        /*
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader("files/killingaliens.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        int testcases = Integer.parseInt(sc.nextLine());

        for(int i = 0; i < testcases; i++){
            String[] firstLine = sc.nextLine().split(" ");
            int cols = Integer.parseInt(firstLine[0]);
            int rows = Integer.parseInt(firstLine[1]);
            Node[][] graph = new Node[rows][cols];
            LinkedList<Node> nodes = new LinkedList<>();
            LinkedList<Edge> edges = new LinkedList<>();
            Node start = null;
            int counter = 0;
            HashMap<Integer, LinkedList<Edge>> neighboutMap = new HashMap<>();

            for(int j = 0; j < rows; j++){ //INITIALIZE
                char[] line = sc.nextLine().toCharArray();
                for(int k = 0; k < cols; k++) {
                    char c = line[k];
                    
                    if(c == '#'){
                        graph[j][k] = new Node(j, k, WALL, -1);
                    } else if(c == ' '){
                        graph[j][k] = new Node(j, k, UNVISITED , -1);
                    }else if(c == 'A'){
                        Node node = new Node(j, k, ALIEN, counter++);
                        graph[j][k] = node;
                        nodes.add(node);
                    }else if(c == 'S'){
                        Node node = new Node(j, k, START, counter++);
                        graph[j][k] = node;
                        nodes.addFirst(node);
                        start = node;
                    }
                }
            }
            
            for(int l = 0; l < nodes.size(); l++){
                LinkedList<Edge> newEdges = main.getEdgesFrom(graph, nodes.get(l), nodes.size());
                
                for(Edge e : newEdges) {
                    edges.add(e);
                }
                
                neighboutMap.put(nodes.get(l).getCount(), newEdges);
            }

            int N = nodes.size();
            boolean[] mst = new boolean[N];
            int[] key = new int[N];

            for(int r = 0; r < N; r++){
                mst[r] = false;
                key[r] = Integer.MAX_VALUE;
            }

            key[start.getCount()] = 0;

            for(int count = 0; count < N - 1; count++){
                int minIndex = main.minimunKey(key, mst);
                
                mst[minIndex] = true;
                LinkedList<Edge> edg = neighboutMap.get(minIndex);
                
                for(Edge e : edg){
                    if(mst[e.getTo()] == false && key[e.getTo()] > e.getWeight()){
                        key[e.getTo()] = e.getWeight();
                    }
                }
            }
            
            int sum = 0;
            for(int o = 0; o < key.length; o++){
                sum += key[o];
            }
            System.out.println(sum);

        }


    }
    
    int minimunKey(int[] key, boolean[] mst){
        int min = Integer.MAX_VALUE;
        int index = -1;
        
        for(int i = 0; i < key.length; i++){
            if(mst[i] == false && key[i] < min){
                min = key[i];
                index = i;
            }
        }
        
        return index;
    }
    
    LinkedList<Edge> getEdgesFrom(Node[][] graph, Node node, int total){
        int WALL = 0;
        int ALIEN = 1;
        int START = 2;
        int UNVISITED = 3;
        int VISITED = 4;
        LinkedList<Edge> edges = new LinkedList<>();
        LinkedList<Node> current = new LinkedList<>();
        current.add(node);
        Node[][] workingGraph = new Node[graph.length][graph[0].length];
        
        for(int q = 0; q < graph.length; q++){
            for(int w = 0; w < graph[q].length; w++){
                workingGraph[q][w] = new Node(graph[q][w].getX(), graph[q][w].getY(), graph[q][w].getType(), graph[q][w].getCount());
            }
        }
        
        workingGraph[node.getX()][node.getY()].setType(VISITED);
        workingGraph[node.getX()][node.getY()].setCost(0);
        
        while(edges.size() < total && !current.isEmpty()){
            Node from = current.removeFirst();
            Node[] around = new Node[4];
            around[0] = workingGraph[from.getX() - 1][from.getY()];
            around[1] = workingGraph[from.getX()][from.getY() + 1];
            around[2] = workingGraph[from.getX() + 1][from.getY()];
            around[3] = workingGraph[from.getX()][from.getY() - 1];
            
            for(int i = 0; i < 4; i++){
                Node n = around[i];
                
                if(n.getType() == UNVISITED){
                    n.setType(VISITED);
                    n.setCost(from.getCost() + 1);
                    current.addLast(n);
                } else if(n.getType() == ALIEN || n.getType() == START){
                    n.setType(VISITED);
                    n.setCost(from.getCost() + 1);
                    current.addLast(n);
                    edges.addLast(new Edge(node.getCount(), n.getCount(), from.getCost() + 1));
                }
            }
            
            
        }
        
        return edges;
        
    }
    
    static class Node implements Comparable{
        int x;
        int y;
        int type;
        int cost;
        int count;
        
        public Node(int x, int y, int type, int count){
            this.x = x;
            this.y = y;
            this.type = type;
            this.count = count;
        }
        
        public void setType(int type){
            this.type = type;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getType() {
            return type;
        }
        
        public void setCost(int cost){
            this.cost = cost;
        }
        
        public int getCost(){
            return cost;
        }
        
        public int getCount(){
            return count;
        }
        

        @Override
        public int compareTo(Object o) {
            return getCost() - ((Node)o).getCost();
        }
    }
    
    static class Edge{
        int from; 
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "From: [" + from + "] to ["  +  to + "] weight: " + weight;
        }
    }
}