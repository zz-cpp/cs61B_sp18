import static org.junit.Assert.*;

import org.junit.Test;

import java.util.LinkedList;

public class TestArrayDequeGold {


    @Test
    public void testFirst() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque();
        LinkedList<Integer> solve = new ArrayDequeSolution();
        String message = "\n";
        boolean actBoolean;
        boolean expBoolean;
        Integer act;
        Integer exp;

        double numberBetweenZeroAndOne;
        for (int i = 0; i < 20; i++) {
            numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sd.addFirst(i);
                solve.addFirst(i);
                message += "addFirst(" + i + ")" + "\n";

                actBoolean = solve.isEmpty();
                expBoolean = solve.isEmpty();
                if (Boolean.compare(actBoolean, expBoolean) != 0) {
                    message += "isEmpty() " + actBoolean + "\n";
                }
                assertEquals(expBoolean, actBoolean);

                act = sd.size();
                exp = solve.size();
                if (!act.equals(exp)) {
                    message += "size() " + act + "\n";
                }
                assertEquals(exp, act);

            } else if (numberBetweenZeroAndOne >= 0.5) {
                sd.addLast(i);
                solve.addLast(i);
                message += "addLast(" + i + ")\n";

                actBoolean = solve.isEmpty();
                expBoolean = solve.isEmpty();
                if (Boolean.compare(actBoolean, expBoolean) != 0) {
                    message += "isEmpty() " + actBoolean + "\n";
                }
                assertEquals(expBoolean, actBoolean);

                act = sd.size();
                exp = solve.size();
                if (!act.equals(exp)) {
                    message += "size() " + act + "\n";
                }
                assertEquals(exp, act);

            }
        }

        for (int i = 0; i < 20; i++) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                act = sd.removeFirst();
                exp = solve.removeFirst();
                message += "removeFirst()\n";
                assertEquals(message, exp, act);

                act = sd.size();
                exp = solve.size();
                if (!act.equals(exp)) {
                    message += "size() " + act + "\n";
                }
                assertEquals(message, exp, act);

                actBoolean = solve.isEmpty();
                expBoolean = solve.isEmpty();
                if (Boolean.compare(actBoolean, expBoolean) != 0) {
                    message += "isEmpty() " + actBoolean + "\n";
                }
                assertEquals(message, expBoolean, actBoolean);

            } else if (numberBetweenZeroAndOne >= 0.5) {
                act = sd.removeLast();
                exp = solve.removeLast();
                message += "removeLast()\n";
                assertEquals(message, exp, act);

                act = sd.size();
                exp = solve.size();
                if (!act.equals(exp)) {
                    message += "size() " + act + "\n";
                }
                assertEquals(message, exp, act);

                actBoolean = solve.isEmpty();
                expBoolean = solve.isEmpty();
                if (Boolean.compare(actBoolean, expBoolean) != 0) {
                    message += "isEmpty() " + actBoolean + "\n";
                }
                assertEquals(message, expBoolean, actBoolean);
            }

        }


    }

}
