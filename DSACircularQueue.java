/**
 * DSACircularQueue: Queue implementation using front and rear pointers
 * that wrap around the underlying array.
 */
public class DSACircularQueue extends DSAQueue {
    // Instance Variables
    private int front;
    private int rear;

    // Default Constructor
    public DSACircularQueue() {
        super();
        this.front = 0;
        this.rear = 0;
    }

    // Alternate Constructor
    public DSACircularQueue(int maxCapacity) {
        super(maxCapacity);
        this.front = 0;
        this.rear = 0;
    }

    // Accessor Methods
    public int getFront() {
        return this.front;
    }

    public int getRear() {
        return this.rear;
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.queue[this.front];
    }

    // Mutator / Doing Methods
    @Override
    public void enqueue(Object value) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        this.queue[this.rear] = value;
        this.rear = (this.rear + 1) % this.queue.length;
        this.count++;
    }

    @Override
    public Object dequeue() {
        Object frontVal = peek();
        this.queue[this.front] = null; // Clear reference for garbage collection
        this.front = (this.front + 1) % this.queue.length;
        this.count--;
        return frontVal;
    }
}
