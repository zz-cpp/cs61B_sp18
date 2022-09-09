import static org.junit.Assert.*;

import org.junit.Test;

import java.util.LinkedList;

public class TestArrayDequeGold {


    @Test
    public void testFirst() {
        StudentArrayDeque<Integer> sd = new StudentArrayDeque();
        LinkedList<Integer> solve = new ArrayDequeSolution();
        String message = "\n";
        double numberBetweenZeroAndOne;
        for (int i = 0; i < 20; i++) {
            numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                sd.addFirst(i);
                solve.addFirst(i);
                message += "addFirst(" + i + ")" + "\n";

            } else if (numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne < 0.5) {
                sd.addLast(i);
                solve.addLast(i);
                message += "addLast(" + i + ")" + "\n";
            } else if (numberBetweenZeroAndOne >= 0.5 || numberBetweenZeroAndOne < 0.75) {
                Integer act = sd.size();
                Integer exp = solve.size();
                if (!act.equals(exp)) {
                    message += "size() " + act + "\n";
                }

                assertEquals(exp, act);
            } else {
                boolean act = solve.isEmpty();
                boolean exp = solve.isEmpty();
                if (Boolean.compare(act,exp) != 0) {
                    message += "isEmpty() " + act + "\n";
                }
                assertEquals(exp, act);
            }
        }

        for (int i = 0; i < 20; i++) {
            numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.25) {
                Integer act = sd.removeFirst();
                Integer exp = solve.removeFirst();
                message += "removeFirst(): " + act + "\n";
                assertEquals(message,exp,act);

            } else if (numberBetweenZeroAndOne >= 0.25 && numberBetweenZeroAndOne < 0.5) {
                Integer act = sd.removeLast();
                Integer exp = solve.removeLast();
                message += "removeLast(): " + act + "\n";
                assertEquals(message,exp,act);

            } else if (numberBetweenZeroAndOne >= 0.5 || numberBetweenZeroAndOne < 0.75) {
                Integer act = sd.size();
                Integer exp = solve.size();
                if (!act.equals(exp)) {
                    message += "size() " + act + "\n";
                }

                assertEquals(exp, act);
            } else {
                boolean act = solve.isEmpty();
                boolean exp = solve.isEmpty();
                if (Boolean.compare(act,exp) != 0) {
                    message += "isEmpty() " + act + "\n";
                }
                assertEquals(exp, act);
            }

        }


    }

}
