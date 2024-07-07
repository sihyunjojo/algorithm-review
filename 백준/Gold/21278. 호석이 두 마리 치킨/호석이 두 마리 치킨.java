import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int N, M;
    public static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static boolean[] visited;
    public static boolean[] counted;
    public static int resultFirstIdx = 0;
    public static int resultSecondIdx = 0;
    public static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i=0;i<=N;i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[N+1];

        for(int j=0;j<M;j++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            graph.get(nodeA).add(new Node(nodeA, nodeB, 0));
            graph.get(nodeB).add(new Node(nodeB, nodeA, 0));
        }
        simulate(1, 0);

        System.out.println(resultFirstIdx+" "+resultSecondIdx+ " "+answer*2);
    }

    public static void simulate(int idx, int level) {
        if(level == 2) {
            counted = new boolean[N+1];
            Queue<Node> q = new LinkedList<>();
            int result = 0;
            for(int i=1;i<=N;i++) {
                if(visited[i] == true) {
                    q.offer(new Node(i, 0, 0));
                    counted[i] = true;
                }
            }


            while(!q.isEmpty()) {
                Node temp = q.poll();
                int nodeA = temp.nodeA;
                int cnt = temp.count;

                for(int i=0;i<graph.get(nodeA).size();i++) {
                    if(counted[graph.get(nodeA).get(i).nodeB] == false ) {
                        q.offer(new Node(graph.get(nodeA).get(i).nodeB, 0, cnt + 1));
                        counted[graph.get(nodeA).get(i).nodeB]= true;
                        result += cnt + 1;
                    }
                }
            }

            if(answer > result) {
                answer = result;
                resultFirstIdx = 0;
                resultSecondIdx = 0;
                for(int i=1;i<=N;i++) {
                    if(visited[i] == true) {
                        if( resultFirstIdx == 0 ) {
                            resultFirstIdx = i;
                        }else {
                            resultSecondIdx = i;
                        }

                    }
                }
            }
            return ;

        }
        for(int i=idx;i<=N;i++) {

            if(visited[i] == true) continue;
            visited[i] = true;
            simulate(i + 1, level + 1);
            visited[i] = false;
        }
    }


}

class Node{
    int nodeA;
    int nodeB;
    int count = 0;
    public Node(int nodeA, int nodeB, int cnt) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.count = cnt;
    }
}