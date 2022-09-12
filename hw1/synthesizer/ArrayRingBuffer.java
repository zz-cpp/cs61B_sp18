// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;


import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return fillCount() == 0;
    }

    @Override
    public boolean isFull() {
        return fillCount() == capacity();
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity and init the rb with given value.
     */
    public ArrayRingBuffer(int capacity, T value) {
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];

        for (int i = 0; i < rb.length; i++) {
            rb[i] = value;
        }
    }


    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[this.last] = x;
        this.last++;
        if (last == capacity) {
            last = 0;
        }
        this.fillCount++;
    }

    /**
     * Dequeue the oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update

        if (fillCount == 0) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T value = rb[first];
        rb[first] = null;
        first += 1;

        if (this.first == this.capacity) {
            first = 0;
        }
        this.fillCount--;
        return value;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[this.first];
    }

    @Override
    public Iterator<T> iterator() {


        return new BufferIterator();
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    private class BufferIterator implements Iterator<T> {

        int ptr;
        int size;

        public BufferIterator() {
            this.ptr = first;
            size = 0;
        }

        @Override
        public boolean hasNext() {
            return size != fillCount();
        }

        @Override
        public T next() {

            T value;
            value = rb[ptr];
            ptr++;
            size++;
            return value;
        }
    }
}
