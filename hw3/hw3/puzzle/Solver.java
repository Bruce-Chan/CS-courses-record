package puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solver {
    private class SearchNode{
        private WorldState worldState;
        private int history;
        private SearchNode prevSearchNode;
        private int priority;
        public SearchNode(WorldState ws, int historyLen, SearchNode prevNode){
            worldState = ws;
            history = historyLen;
            prevSearchNode = prevNode;
            priority = history + worldState.estimatedDistanceToGoal();
        }

        public int priority(){
            return priority;
        }
    }

    class Comp implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode s1, SearchNode s2) {
            return s1.priority()-s2.priority();
        }
    }

    private SearchNode finalSearchNode;

    public Solver(WorldState initial){
        SearchNode curr = new SearchNode(initial,0,null);
        MinPQ<SearchNode> pq = new MinPQ(new Comp());
        while(curr.worldState.isGoal()!=true){
            for(WorldState neighb : curr.worldState.neighbors()){
                SearchNode prevNode = curr.prevSearchNode;
                if(prevNode==null||
                        neighb.equals(prevNode.worldState)==false) {
                    SearchNode newSNode = new SearchNode(neighb,
                            curr.history + 1, curr);
                    pq.insert(newSNode);
                }
            }
            curr = pq.delMin();
        }
        finalSearchNode = curr;
    }

    public int moves(){
        return finalSearchNode.history;
    }

    public Iterable<WorldState> solution(){
        List<WorldState> solutionLst = new ArrayList<>();
        SearchNode curr = finalSearchNode;
        getSolu(solutionLst,finalSearchNode);
        return solutionLst;
    }

    /*
     * A recursive helper function to get solution.
     */
    private void getSolu(List<WorldState> lst, SearchNode n){
        if(n.prevSearchNode!=null){
            getSolu(lst,n.prevSearchNode);
        }
        lst.add(n.worldState);
    }

}

