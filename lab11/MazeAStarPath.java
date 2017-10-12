import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;
import java.util.Observable;
/**
 *  @author Josh Hug
 */

public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private int tX;
    private int tY;
    private boolean targetFound = false;
    private Maze maze;

    private class Node{
        int index;
        int priority;
        public Node(int i, int prio){
            index = i;
            priority = prio;
        }
    }

    private class Comp implements Comparator<Node> {
        @Override
        public int compare(Node n1, Node n2) {
            return n1.priority-n2.priority;
        }
    }

    MinPQ<Node> minPQ = new MinPQ(new Comp());

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        tX = targetX;
        tY = targetY;
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int disX = Math.abs(maze.toX(v)-tX);
        int disY = Math.abs(maze.toY(v)-tY);
        return disX+disY;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return minPQ.delMin().index;
        /* You do not have to use this method. */
    }

    /** Performs an astar search from vertex s. */
    private void astar(int n) {
        announce();
        if(n==t){
            targetFound = true;
        }
        if(targetFound == true){
            return;
        }
        for(int adj:maze.adj(n)){
            if(!marked[adj]){
                edgeTo[adj] = n;
                distTo[adj] =distTo[n]+1;
                Node curr = new Node(adj,h(adj));
                minPQ.insert(curr);
            }
        }
        int next = findMinimumUnmarked();
        marked[next] = true;
        astar(next);
    }

    @Override
    public void solve() {
        astar(s);
    }

}

