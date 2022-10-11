package lab11.graphs;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    private IndexMinPQ<Integer> pq;    // priority queue of vertices


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new IndexMinPQ<>(maze.V());
        for (int i = 0; i < maze.V(); i++) {
            pq.insert(i, Integer.MAX_VALUE);
        }
        pq.changeKey(s, h(s));
    }

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        int vx = maze.toX(v);
        int vy = maze.toX(v);
        int tx = maze.toX(t);
        int ty = maze.toY(t);
        return Math.abs(vx - tx) + Math.abs(vy - ty);
    }

    /**
     * Finds vertex estimated to be closest to target.
     */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int s) {
        while (!pq.isEmpty()) {
            int min = pq.delMin();
            marked[min] = true;
            announce();
            if (min == t) {
                targetFound = true;
                return;
            }
            for (Integer neighbor : maze.adj(min)) {
                if (!marked[neighbor]) {
                    relax(neighbor, min);
                }
            }
        }
    }

    /**
     * update key in pq according to the estimate to target from source.
     *
     * @param v is vertex that need to update.
     * @param p is V's parent.
     */
    private void relax(int v, int p) {
        if (pq.keyOf(v) > (distTo[p] + 1 + h(v))) {
            pq.changeKey(v, distTo[p] + 1 + h(v));
            distTo[v] = distTo[p] + 1;
            edgeTo[v] = p;
        }
    }


    @Override
    public void solve() {
        astar(s);
    }

}

