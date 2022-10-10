package lab11.graphs;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;

    private int t;

    private Maze maze;

    private boolean targetFound = false;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        PriorityQueue<Integer> fringe = new PriorityQueue();
        fringe.add(s);
        marked[s] = true;
        while (!fringe.isEmpty()) {
            Integer curr = fringe.remove();
            announce();
            if (targetFound) {
                break;
            }
            for (Integer neighbor : maze.adj(curr)) {
                if (!marked[neighbor]) {
                    distTo[neighbor] = distTo[curr] + 1;
                    marked[neighbor] = true;
                    edgeTo[neighbor] = curr;
                    fringe.add(neighbor);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

