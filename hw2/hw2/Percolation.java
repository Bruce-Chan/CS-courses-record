package hw2;                       

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.util.ArrayList;
import java.util.List;

public class Percolation {
    Boolean[] sitesOpenStatusArr;
    WeightedQuickUnionUF sites;
    WeightedQuickUnionUF percoSites; // use to check does it percolate
    int openSitesSize;
    int length;
    private final int beginPoint;
    private final int endPoint;
    public Percolation(int N){
        length = N;
        openSitesSize = 0;
        sitesOpenStatusArr = new Boolean[N*N];
        // initialize array
        for(int i = 0; i<N*N; i++){
            sitesOpenStatusArr[i] = false;
        }
        sites = new WeightedQuickUnionUF(N*N+1);
        percoSites = new WeightedQuickUnionUF(N*N+2);;

        beginPoint = N*N;

        endPoint = N*N+1;// only used in peroSotes to check the status of perocolation
    }

    /*
     * convert the number of row and column to a single number index
     */
    private int XYtoNum(int X, int Y){
        return X*length+Y;
    }

    private void checkAndConnect(int row, int col){
        int curr = XYtoNum(row,col);
        List<Integer> checkedLst = new ArrayList<>();
        if(row>0){
            int up = XYtoNum(row-1,col); //up site
            checkedLst.add(up);
        }
        if(row<length-1){
            int down = XYtoNum(row+1,col); //down site
            checkedLst.add(down);
        }
        if(col>0){
            int left = XYtoNum(row,col-1); // left site
            checkedLst.add(left);
        }
        if(col<length-1){
            int right = XYtoNum(row,col+1); //right site
            checkedLst.add(right);
        }

        for (Integer i :checkedLst){
            if(sitesOpenStatusArr[i]){
                sites.union(curr,i);
                percoSites.union(curr,i);
            }
        }
    }

    public void open(int row, int col){
        if (row<0||row>=length||col<0||col>=length){
            throw new IllegalArgumentException();
        }
        int index = XYtoNum(row, col);

        checkAndConnect(row,col);
        if(sitesOpenStatusArr[index] != true){
            openSitesSize++;
            sitesOpenStatusArr[index]=true;
        }

        /*
         *  if this site is one of the lowest site and full
         *  connect it with the virtual end point
         */
        if(row==0){
            percoSites.union(index,beginPoint);
            sites.union(index,beginPoint);
        }

        /*
         *  if this site is one of the lowest site
         *  connect it with the virtual end point in the percoSites
         */
        if(row==length-1){
            percoSites.union(index,endPoint);
        }
    }

    public boolean isOpen(int row, int col){
        if (row<0||row>=length||col<0||col>=length){
            throw new IllegalArgumentException();
        }
        int index = XYtoNum(row, col);
        return sitesOpenStatusArr[index];
    }

    public boolean isFull(int row, int col){
        if (row<0||row>=length||col<0||col>=length){
            throw new IllegalArgumentException();
        }
        int index = XYtoNum(row, col);
        return sites.connected(index,beginPoint);
    }

    public int numberOfOpenSites(){
        return openSitesSize;
    }

    public boolean percolates(){
        // check if the virtual begin point is connecting the
        // virtual end point
        return percoSites.connected(beginPoint,endPoint);
    }
}                       
