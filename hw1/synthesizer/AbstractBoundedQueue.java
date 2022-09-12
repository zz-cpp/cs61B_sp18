package synthesizer;

public abstract class  AbstractBoundedQueue<T>implements BoundedQueue<T> {

    /**
     * The number of the queue containing elements.
     * */
    protected int fillCount;

    /**
     * The capacity of the queue.
     * */
    protected int capacity;




}
