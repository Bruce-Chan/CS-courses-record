package puzzle;

import java.util.ArrayDeque;
import edu.princeton.cs.algs4.Queue;


public class Board implements WorldState{
    private int[][] tiles;
    private int size;
    private static final int BLANK = 0;
    public Board(int[][] tilesInput){

        size=tilesInput.length;
        tiles = new int[size][size];
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++) {
                tiles[i][j] = tilesInput[i][j];
            }
        }
    }

    public int tileAt(int i,int j){
        if(i>=size||i<0||j>=size||j<0){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size(){
        return size;
    }

    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     * Cited from http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /**
     * return hamming estimate for this board
     */
    private int hamming(){
        int expect = 1;
        int estimateDis = 0;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(tiles[i][j]!=expect){
                    estimateDis++;
                }
                expect++;
            }
        }
        return estimateDis;
    }

    /**
     *
     * @return the expect row of a value
     */
    private int row(int val){
        return (val-1)/size;
    }

    /**
     *
     * @return the expect column of a value
     */
    private int col(int val){
        return (val-1)%size;
    }

    /**
     * return manhattan estimate for this board
     */
    private int manhattan(){
        int estimateDis = 0;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int currVal = tiles[i][j];
                int rowDiff = Math.abs(i - row(currVal));
                int colDiff = Math.abs(j - col(currVal));
                estimateDis += (rowDiff+colDiff);
            }
        }
        return estimateDis;
    }

    /**
     *
     * Estimated distance to goal.
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     *
     * Returns true if this board is the goal board
     */
    @Override
    public boolean isGoal() {
        int expect = 1;
        int estimateDis = 0;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if(i==size-1&&j==size-1){ //final tile
                    expect = BLANK;
                }
                if(tiles[i][j]!=expect){
                    return false;
                }
                expect++;
            }
        }
        return true;
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     */
    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null || o.getClass()!=getClass()){
            return false;
        }
        Board other = (Board) o;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                int x = tiles[i][j];
                int y = other.tiles[i][j];
                if(x!=y){
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
