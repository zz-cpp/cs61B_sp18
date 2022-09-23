package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF gridSets;

    private boolean[] gridState;

    private int openSites;

    private int fake1;

    private int fake2;

    private int RAndC;

    private class Position {

        private int row;

        private int col;

        private Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * create N-by-N grid, with all sites initially blocked.
     *
     * @param N is presenting N * N.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        gridSets = new WeightedQuickUnionUF(N * N + 2);
        fake1 = N * N;
        fake2 = N * N + 1;
        RAndC = N;
        openSites = 0;
        gridState = new boolean[N * N];
        for (int i = 0; i < N * N; i++) {
            gridState[i] = false;
        }
    }

    /*
     *  open the site (row, col) if it is not open already
     * */
    public void open(int row, int col) {
        if (isOutIndex(row, col)) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(row, col)) {
            return;
        }

        int position = computeOneDimenXY(row, col);

        // if position is top or down , connect the virtual grid.
        if (position >= 0 && position <= RAndC - 1) {
            gridSets.union(position, fake1);
        }

        if (position >= (RAndC - 1) * RAndC && position <= RAndC * RAndC - 1) {
            gridSets.union(position, fake2);
        }

        gridState[position] = true;
        dynamicConnect(row, col);
        openSites++;
    }

    public boolean isOpen(int row, int col) {
        if (isOutIndex(row, col)) {
            throw new IndexOutOfBoundsException("fail: isOpen");
        }
        return gridState[computeOneDimenXY(row, col)];
    }


    public boolean isFull(int row, int col) {
        if (isOutIndex(row, col)) {
            throw new IndexOutOfBoundsException(" fail: isFull");
        }

        int position = computeOneDimenXY(row, col);
        return gridSets.connected(position, fake1);
    }

    /**
     * check the system whether percolate
     */
    public boolean percolates() {
        return gridSets.connected(fake1, fake2);
    }

    /**
     * number of open sites
     */
    public int numberOfOpenSites() {
        return openSites;
    }

    private void dynamicConnect(int row, int col) {

        int position = computeOneDimenXY(row, col);

        Position top = null;
        Position down = null;
        Position left = null;
        Position right = null;

        if (!isOutIndex(row - 1, col)) {
            top = new Position(row - 1, col);
        }

        if (!isOutIndex(row + 1, col)) {
            down = new Position(row + 1, col);
        }

        if (!isOutIndex(row, col - 1)) {
            left = new Position(row, col - 1);
        }

        if (!isOutIndex(row, col + 1)) {
            right = new Position(row, col + 1);
        }

        if (top != null && isOpen(top.row, top.col)) {
            gridSets.union(position, computeOneDimenXY(top.row, top.col));
        }

        if (down != null && isOpen(down.row, down.col)) {
            gridSets.union(position, computeOneDimenXY(down.row, down.col));
        }

        if (left != null && isOpen(left.row, left.col)) {
            gridSets.union(position, computeOneDimenXY(left.row, left.col));
        }

        if (right != null && isOpen(right.row, right.col)) {
            gridSets.union(position, computeOneDimenXY(right.row, right.col));
        }


    }


    /*
     * transform the 2D array location into 1D location.
     * */
    private int computeOneDimenXY(int row, int col) {
        return row * RAndC + col;
    }


    private boolean isOutIndex(int row, int col) {

        if (row >= RAndC || row < 0 || col >= RAndC || col < 0) {
            return true;
        }

        return false;
    }


}
