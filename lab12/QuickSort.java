import edu.princeton.cs.algs4.Queue;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     * <p>
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item : q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted A Queue of unsorted items
     * @param pivot    The item to pivot on
     * @param less     An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are less than the given pivot.
     * @param equal    An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are equal to the given pivot.
     * @param greater  An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        // implement in three-way
        for (Item item : unsorted) {
            if (item.compareTo(pivot) < 0) {
                less.enqueue(item);
            } else if (item.compareTo(pivot) == 0) {
                equal.enqueue(item);
            } else {
                greater.enqueue(item);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        // base case
        if (items.size() == 1 || items.isEmpty()) {
            return items;
        }
        Queue<Item> less = new Queue<>();
        Queue<Item> equal = new Queue<>();
        Queue<Item> greater = new Queue<>();
        // choose pivot
        Item randomItem = getRandomItem(items);
        // partition
        partition(items, randomItem, less, equal, greater);
        less = quickSort(less);
        greater = quickSort(greater);
        equal = catenate(less, equal);
        items = catenate(equal, greater);
        return items;
    }

    public static void main(String[] args) {
        Queue<Integer> stu = new Queue<>();
        stu.enqueue(4);
        stu.enqueue(5);
        stu.enqueue(6);
        stu.enqueue(1);
        stu.enqueue(7);
        stu.enqueue(0);
        stu.enqueue(4);

        stu = quickSort(stu);
        Queue<Integer> ans = new Queue<>();
        ans.enqueue(0);
        ans.enqueue(1);
        ans.enqueue(4);
        ans.enqueue(4);
        ans.enqueue(5);
        ans.enqueue(6);
        ans.enqueue(7);

        int size = stu.size();
        int v1 = stu.dequeue();
        int v2 = ans.dequeue();
        for (int i = 0; i < size; i++) {
            assertTrue("stu: " + v1 + " and " + "ans: " + v2, v1 == v2);
        }
    }
}
