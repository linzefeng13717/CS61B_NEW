package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /*public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;*/
    private int s;
    private int t;
    //private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze=m;
        s=m.xyTo1D(sourceX,sourceY);
        t=m.xyTo1D(targetX,targetY);
        distTo[s]=0;
        edgeTo[s]=s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> queue=new ArrayDeque<>();
        marked[s]=true;
        queue.add(s);
        while(!queue.isEmpty())
        {
            if(marked[t])return;
            int topOne=queue.remove();
            announce();
            for(int nei:maze.adj(topOne))
            {
                if(!marked[nei])
                {
                    edgeTo[nei] = topOne;
                    distTo[nei] = distTo[topOne] + 1;
                    marked[nei] = true;
                    queue.add(nei);
                    announce();
                }
                if(marked[t])return;
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

