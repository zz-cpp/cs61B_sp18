import edu.princeton.cs.algs4.Queue;

import static junit.framework.Assert.assertTrue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
    makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> res = new Queue<>();
        Queue<Item> single;
        for (Item item : items) {
            single = new Queue<>();
            single.enqueue(item);
            res.enqueue(single);
        }
        return res;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> returnItem = new Queue<>();
        while (true) {
            if (q1.isEmpty() || q2.isEmpty()) {
                break;
            }
            returnItem.enqueue(getMin(q1, q2));
        }

        while (!q1.isEmpty()) {
            returnItem.enqueue(q1.dequeue());
        }

        while (!q2.isEmpty()) {
            returnItem.enqueue(q2.dequeue());
        }

        return returnItem;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> singleItem = makeSingleItemQueues(items);
        Queue<Item> q1;
        Queue<Item> q2;
        int end = 1;
        // use queue to simulate running function stack in recursive order, amazing!!!
        while (singleItem.size() != end) {
            q1 = singleItem.dequeue();
            q2 = singleItem.dequeue();
            singleItem.enqueue(mergeSortedQueues(q1, q2));
        }
        items = singleItem.dequeue();
        return items;
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Bsdsas");
        students.enqueue("Lojgug");

        students = mergeSort(students);

        Queue<String> answers = new Queue<>();
        answers.enqueue("Alice");
        answers.enqueue("Bsdsas");
        answers.enqueue("Ethan");
        answers.enqueue("Lojgug");
        answers.enqueue("Vanessa");

        String stu;
        String ans;
        boolean flag;
        int N = students.size();
        for (int i = 0; i < N; i++) {
            stu = students.dequeue();
            ans = answers.dequeue();
            flag = stu.equals(ans);
            assertTrue("i: " + i + " " + "stu: " + stu + " " + "ans: " + ans + "\n", flag);
        }

    }
}
