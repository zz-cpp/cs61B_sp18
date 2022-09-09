import static org.junit.Assert.*;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.LinkedList;

public class TestArrayDequeGold {


    @Test
    public void testFirst() {
        Integer act = 1;
        Integer exp = 1;
        StudentArrayDeque<Integer> sd = new StudentArrayDeque();
        LinkedList<Integer> solve = new ArrayDequeSolution();
        String message = "";
        int operation1;
        int operation2;
        int randomNum;

        for (int i = 0; i < 1000; i++) {
            operation1 = StdRandom.uniform(0, 2);
            randomNum = StdRandom.uniform(0, 1000);

            if (sd.isEmpty() && solve.isEmpty()) {
                if (operation1 == 1) {
                    sd.addFirst(randomNum);
                    solve.addFirst(randomNum);
                    message += "addFirst(" + randomNum + ")" + "\n";
                } else {
                    sd.addLast(randomNum);
                    solve.addLast(randomNum);
                    message += "addLast(" + randomNum + ")\n";
                }
            } else {
                operation2 = StdRandom.uniform(0, 4);
                randomNum = StdRandom.uniform(0, 1000);
                switch (operation2) {
                    case 0: {
                        sd.addFirst(randomNum);
                        solve.addFirst(randomNum);
                        message += "addFirst(" + randomNum + ")\n";
                    }
                    break;

                    case 1: {
                        sd.addLast(randomNum);
                        solve.addLast(randomNum);
                        message += "addLast(" + randomNum + ")\n";
                    }
                    break;

                    case 2: {
                        act = sd.removeFirst();
                        exp = solve.removeFirst();
                        message += "removeFirst()\n";
                    }
                    break;

                    case 3: {
                        act = sd.removeLast();
                        exp = solve.removeLast();
                        message += "removeLast()\n";

                    }
                    default:
                }
            }

            assertEquals(message, exp, act);
        }
    }
}
