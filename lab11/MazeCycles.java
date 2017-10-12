import java.util.Observable;
/** 
 *  @author Josh Hug
 */

public class MazeCycles extends MazeExplorer {
    /* Inherits public fields: 
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    boolean getCircle = false;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        edgeTo[1] = 0;
        marked[1] =true;
    }

    public void findCircle(int i){
        announce();
        for(int adj : maze.adj(i)){
            if(adj!=edgeTo[i]){
                if(marked[adj]){
                    edgeTo[adj] = i;
                    announce();
                    getCircle = true;
                    return;
                } else{
                    marked[adj] = true;
                    edgeTo[adj] = i;
                    findCircle(adj);
                }
            }
            if(getCircle){
                return;
            }
        }
    }

    @Override
    public void solve() {
        findCircle(1);
    }
} 

