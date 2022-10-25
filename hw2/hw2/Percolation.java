package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int sideLen;

    private boolean[][] Grid;
    private int openSiteNum;

    WeightedQuickUnionUF sites;
    public Percolation(int N)// create N-by-N grid, with all sites initially blocked
    {
        if(N<=0)
            throw new IllegalArgumentException("N must be bigger than 0!");
        sideLen=N;
        Grid=new boolean[sideLen][sideLen];
        openSiteNum=0;
        sites=new WeightedQuickUnionUF(sideLen*sideLen+2);
        //sidelen*sidelen for bottom,sidelen*sidelen+1 for top;
    }

    private int turnIntoOne_dem(int row,int col)
    {
        return row*sideLen+col;
    }

    /**
     *
     * @param row:水平列
     * @param col:垂直列
     */
    public void open(int row, int col)       // open the site (row, col) if it is not open already
    {
        if(row<0||row>=sideLen||col<0||col>=sideLen)
        {
            throw new IllegalArgumentException("Out Of Index");
        }
        if(isOpen(row,col))return;
        Grid[row][col]=true;
        openSiteNum++;
        int[][] next={
                {0,1},
                {1,0},
                {-1,0},
                {0,-1}
        };
        for(int i=0;i<4;i++)
        {
            int mx=row+next[i][0];
            int my=col+next[i][1];
            if(my<0||my>=sideLen)
                continue;
            if(mx==-1)
                sites.union(turnIntoOne_dem(row, col),sideLen*sideLen);
            else if(mx==sideLen)
                sites.union(turnIntoOne_dem(row, col),sideLen*sideLen+1);
            else if(isOpen(mx,my)&& !sites.connected(turnIntoOne_dem(mx, my), turnIntoOne_dem(row, col)))
                sites.union(turnIntoOne_dem(row, col),turnIntoOne_dem(mx,my));
        }
    }

    public boolean isOpen(int row, int col)  // is the site (row, col) open?
    {
        if(row<0||row>=sideLen||col<0||col>=sideLen)
        {
            throw new IllegalArgumentException("Out Of Index");
        }
        return Grid[row][col];
    }
    public boolean isFull(int row, int col)  // is the site (row, col) full?
    {
        if(row<0||row>=sideLen||col<0||col>=sideLen)
        {
            throw new IllegalArgumentException("Out Of Index");
        }
        return sites.connected(turnIntoOne_dem(row,col),sideLen*sideLen+1);
    }
    public int numberOfOpenSites()           // number of open sites
    {
        return openSiteNum;
    }
    public boolean percolates()              // does the system percolate?
    {
        return sites.connected(sideLen*sideLen,sideLen*sideLen+1);
    }
}
