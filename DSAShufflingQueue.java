/**
 * DSAShufflingQueue: Queue implementation that shifts (shuffles)
 * all remaining elements left by one position upon dequeue.
 */
public class DSAShufflingQueue extends DSAQueue {

    // Default Constructor
    public DSAShufflingQueue() {
        super();
    }

    // Alternate Constructor
    public DSAShufflingQueue(int maxCapacity) {
        super(maxCapacity);
    }

    // Accessor / Doing Methods
    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.queue[0];
    }

    @Override
    public void enqueue(Object value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        this.queue[this.count] = value;
        this.count++;
    }

    @Override
    public Object dequeue() {
        Object frontVal = peek();
        for (int i = 0; i < this.count - 1; i++) {
            this.queue[i] = this.queue[i + 1];
        }
        this.count--;
        this.queue[this.count] = null; // Clear reference for garbage collection
        return frontVal;
    }
}
