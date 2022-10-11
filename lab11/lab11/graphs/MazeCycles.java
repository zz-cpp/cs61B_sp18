package lab11.graphs;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;

    private Maze maze;

    // check if a marked neighbor is the current parent
    private int[] parentTo;

    private final  int NOCYCLE = 0;


    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        distTo[s] = 0;
        edgeTo[s] = s;
        parentTo = new int[maze.V()];
        for (int i = 0; i < parentTo.length; i++) {
            parentTo[i] = Integer.MAX_VALUE;
        }
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(s);
    }

    // Helper methods go here
    private int dfs(int s) {
        marked[s] = true;
        announce();
        for (Integer neighbor : maze.adj(s)) {

            if (marked[neighbor] && parentTo[s] != neighbor) {
                edgeTo[s] = neighbor;
                announce();
                return distTo[s] - distTo[neighbor];
            }

            if (!marked[neighbor]) {
                distTo[neighbor] = distTo[s] + 1;
                parentTo[neighbor] = s;
                announce();
                int returnValue = dfs(neighbor);
                if (returnValue > NOCYCLE) {
                    edgeTo[s] = neighbor;
                    announce();
                    return returnValue - 1;
                }
            }
        }
        return NOCYCLE;
    }
}

