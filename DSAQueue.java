/**
 * DSAQueue: Abstract base class for Queue data structures.
 */
public abstract class DSAQueue {
    // Class Constants
    public static final int DEFAULT_CAPACITY = 100;

    // Instance Variables
    protected Object[] queue;
    protected int count;

    // Default Constructor
    public DSAQueue() {
        this.queue = new Object[DEFAULT_CAPACITY];
        this.count = 0;
    }

    // Alternate Constructor
    public DSAQueue(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.queue = new Object[maxCapacity];
        this.count = 0;
    }

    // Accessor Methods
    public int getCount() {
        return this.count;
    }

    public int getCapacity() {
        return this.queue.length;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull() {
        return this.count == this.queue.length;
    }

    // Abstract Methods for Subclasses
    public abstract void enqueue(Object value);
    public abstract Object dequeue();
    public abstract Object peek();
}
