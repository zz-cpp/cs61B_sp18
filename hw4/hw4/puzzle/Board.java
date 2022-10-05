package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int[][] state;

    private final int BLANK = 0;

    private int N;

    private int hamming;

    private int manhattan;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j.
     */
    public Board(int[][] tiles) {
        state = new int[tiles.length][tiles.length];
        N = this.state.length;
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, state[i], 0, N);
        }


        manhattan = manhattan();
        hamming = hamming();
    }


    /**
     * Returns value of tile at row i, column j (or 0 if blank)
     */
    public int tileAt(int i, int j) {
        int index = i * this.N + j;
        if (index >= N * N || index < 0) {
            throw new IndexOutOfBoundsException("tileAt failed");
        }
        return state[i][j];
    }

    /**
     * Returns the board size N
     */

    public int size() {
        return N;
    }

    /**
     * Returns the neighbors of the current board
     *
     * @author http://joshh.ug/neighbors.html
     */

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

    /*
     * Compute array index
     * */
    private int computeIndex(int i, int j) {
        return i * N + j;
    }


    /**
     * Hamming estimate
     * The number of tiles in the wrong position
     */

    public int hamming() {
        int estimate = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                int index = computeIndex(i, j);
                if (state[i][j] != index + 1) {
                    estimate++;
                }
            }
        }

        // we don't care state[N*N -1] ==  blank
        estimate--;
        return estimate;
    }


    /**
     * Manhattan estimate described below
     * The sum of the Manhattan distances (sum of the vertical and horizontal distance)
     * from the tiles to their goal positions.
     */
    public int manhattan() {
        int estimate = 0;
        int tary;
        int tarx;
        int xoff;
        int yoff;
        int v;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int index = computeIndex(i, j);
                if (state[i][j] != index + 1) {
                    if (state[i][j] == 0) {
                        continue;
                    }
                    tarx = (state[i][j] - 1) / N;
                    tary = (state[i][j] - 1) % N;
                    xoff = (tarx - i) >= 0 ? (tarx - i) : ((~(tarx - i)) + 1);
                    yoff = (tary - j) >= 0 ? (tary - j) : ((~(tary - j)) + 1);
                    v = xoff + yoff;
                    estimate += v;
                }
            }

        }
        return estimate;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to Gradescope.
     */
    public int estimatedDistanceToGoal() {

        return this.manhattan;
    }

    /**
     * Returns true if this board's tile values are the same position as y's.
     */
    @Override
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }

        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board board = (Board) y;
        if (board.N != this.N || board.manhattan() != this.manhattan || board.hamming != this.hamming) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board.tileAt(i, j) != this.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Returns the string representation of the board.
     * Uncomment this method.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
