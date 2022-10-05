package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class Solver {

    private List<WorldState> solution;

    private int minmove;

    private class SearchNode {

        private WorldState state;

        private int NumberTo;

        private SearchNode previous;

        private int edgCache;

        private SearchNode(WorldState state, int numberTo, SearchNode previous) {
            this.state = state;
            NumberTo = numberTo;
            this.previous = previous;
            edgCache = state.estimatedDistanceToGoal();
        }
    }


    private class WordComparator implements Comparator<SearchNode> {

        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            return (o1.NumberTo + o1.edgCache - (o2.NumberTo + o2.edgCache));
        }
    }


    /*
     * implement A* algorithm for shortest sequence from initial to goal
     *
     * */

    private void AStar(WorldState initial) {

        // guarantee no duplicate state enqueue
        HashSet<WorldState> marked = new HashSet<>();
        // fringe
        MinPQ<SearchNode> mq = new MinPQ<>(new WordComparator());
        SearchNode first = new SearchNode(initial, 0, null);
        mq.insert(first);

        while (!mq.isEmpty()) {

            SearchNode popNode = mq.delMin();

            if (popNode.state.isGoal()) {
                SearchNode ref = popNode;
                // for inversion
                Stack<WorldState> stack = new Stack<>();
                minmove = popNode.NumberTo;

                while (ref != null) {
                    stack.push(ref.state);
                    ref = ref.previous;
                }

                while (!stack.isEmpty()) {
                    solution.add(stack.pop());
                }
                break;
            }

            // first we try to use queue for critical optimal, but can't.
            for (WorldState neighbor : popNode.state.neighbors()) {

                if (popNode.previous != null && neighbor.equals(popNode.previous.state)) {
                    continue;
                }
                mq.insert(new SearchNode(neighbor, popNode.NumberTo + 1, popNode));

            }

        }


    }


    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        this.solution = new LinkedList<>();
        minmove = 0;
        AStar(initial);

    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return minmove;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     */
    public Iterable<WorldState> solution() {
        return solution;
    }
}
