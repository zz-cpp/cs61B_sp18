import static org.junit.Assert.*;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.LinkedList;

public class TestArrayDequeGold {


    @Test
    public void testFirst() {
        boolean actBoolean;
        boolean expBoolean;
        Integer act;
        Integer exp;
        StudentArrayDeque<Integer> sd = new StudentArrayDeque();
        LinkedList<Integer> solve = new ArrayDequeSolution();
        String message = "";
        int operation1;
        int operation2;
        int randomNum;


        for (int i = 0; i < 1000; i++) {
            operation1 = StdRandom.uniform(0, 2);
            randomNum = StdRandom.uniform(0, 100);
            if (operation1 == 1) {
                sd.addFirst(randomNum);
                solve.addFirst(randomNum);
                message += "addFirst(" + randomNum + ")" + "\n";
            } else {
                sd.addLast(randomNum);
                solve.addLast(randomNum);
                message += "addLast(" + randomNum + ")\n";
            }

            operation2 = StdRandom.uniform(0, 4);
            switch (operation2) {
                case 0: {
                    actBoolean = sd.isEmpty();
                    expBoolean = solve.isEmpty();
                    message += "isEmpty() " + actBoolean + "\n";
                    assertEquals(expBoolean, actBoolean);
                }
                break;

                case 1: {
                    act = sd.size();
                    exp = solve.size();
                    message += "size() " + act + "\n";
                    assertEquals(exp, act);
                }
                break;

                case 2: {
                    act = sd.removeFirst();
                    exp = solve.removeFirst();
                    message += "removeFirst()\n";
                    assertEquals(message, exp, act);
                }
                break;

                case 3: {
                    act = sd.removeLast();
                    exp = solve.removeLast();
                    message += "removeLast()\n";
                    assertEquals(message, exp, act);
                }
                default:
            }
        }
    }
}
