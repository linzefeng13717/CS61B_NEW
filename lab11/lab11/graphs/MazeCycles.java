package lab11.graphs;

import java.util.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    private int s;
    private int pre;

    public MazeCycles(Maze m)
    {
        super(m);
        maze=m;
        s=0;
        distTo[s]=0;
        edgeTo[s]=0;
    }

    @Override
    public void solve() {
        Stack<Integer> stack=new Stack<>();
        stack.push(s);
        marked[s]=true;
        announce();
        while(!stack.empty()){
            int top=stack.pop();
            for(int i:maze.adj(top)){
                if(marked[i]&&i!=edgeTo[top])
                {
                    announce();
                    return;
                }
                marked[i]=true;
                distTo[i]=distTo[top]+1;
                edgeTo[i]=top;
                stack.push(i);
                announce();
            }
        }
    }

    // Helper methods go here
}

