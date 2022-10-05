package hw4.puzzle;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestBoard {
    @Test
    public void verifyImmutability() {
        int r = 2;
        int c = 2;
        int[][] x = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i += 1) {
            for (int j = 0; j < c; j += 1) {
                x[i][j] = cnt;
                cnt += 1;
            }
        }
        Board b = new Board(x);
        assertEquals("Your Board class is not being initialized with the right values.", 0, b.tileAt(0, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 1, b.tileAt(0, 1));
        assertEquals("Your Board class is not being initialized with the right values.", 2, b.tileAt(1, 0));
        assertEquals("Your Board class is not being initialized with the right values.", 3, b.tileAt(1, 1));

        x[1][1] = 1000;
        assertEquals("Your Board class is mutable and you should be making a copy of the values in the passed tiles array. Please see the FAQ!", 3, b.tileAt(1, 1));
    }

    @Test
    public void testHamming() {
        int[][] titles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(titles);
        int hamming = board.hamming();
        assertEquals(5, hamming);
    }

    @Test
    public void testManhattan() {
        int[][] titles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board board = new Board(titles);
        int manhattan = board.manhattan();
        assertEquals(10, manhattan);

        int[][] titles2 = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        board = new Board(titles2);
        assertEquals(4, board.manhattan());

        int[][] titles3 = {{1, 0}, {3, 2}};
        board = new Board(titles3);
        assertEquals(1, board.manhattan());

    }
} 
