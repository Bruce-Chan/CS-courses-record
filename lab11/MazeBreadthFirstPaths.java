import java.util.Comparator;
import java.util.Observable;
import java.util.PriorityQueue;
import edu.princeton.cs.algs4.MinPQ;


/**
 *  @author Josh Hug
 */

public class MazeBreadthFirstPaths extends MazeExplorer {

    class Node{
        int index;
        int priority;
        public Node(int i, int prio){
            index = i;
            priority = prio;
        }
    }

    class Comp implements Comparator<Node>{
        @Override
        public int compare(Node n1, Node n2) {
            return n1.priority-n2.priority;
        }
    }

    MinPQ<Node> minPQ = new MinPQ(new Comp());
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    boolean targetFound = false;
    int source;
    int target;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {

        super(m);
        maze = m;
        source = maze.xyTo1D(sourceX,sourceY);
        target = maze.xyTo1D(targetX,targetY);

        marked[source] = true;
        distTo[source] = 0;
    }

    /** Conducts a breadth first search of the maze starting at vertex x. */
    private void bfs(int s) {

        announce();
        if(targetFound){
            return;
        }
        if(s == target){
            targetFound = true;
            return;
        }
        for(int adj : maze.adj(s)){
            if(!marked[adj]){
                distTo[adj] = distTo[s]+1;
                edgeTo[adj] = s;
                Node curr = new Node(adj,distTo[adj]);
                minPQ.insert(curr);
            }
        }
        int next = minPQ.delMin().index;
        marked[next] = true;
        bfs(next);
        announce();
    }


    @Override
    public void solve() {
       bfs(source);
    }
}

