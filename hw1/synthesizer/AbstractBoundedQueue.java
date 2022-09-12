package synthesizer;

public abstract class  AbstractBoundedQueue<T> implements BoundedQueue<T> {

    /**
     * The number of the queue containing elements.
     * */
    protected int fillCount;

    /**
     * The capacity of the queue.
     * */
    protected int capacity;

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int fillCount() {
        return this.fillCount;
    }

    @Override
    public boolean isEmpty() {
        return fillCount() == 0;
    }

    @Override
    public boolean isFull() {
        return fillCount() == capacity();
    }

}
