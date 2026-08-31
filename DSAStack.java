/**
 * DSAStack: Array-based implementation of a generic Stack data structure.
 */
public class DSAStack {
    // Class Constants
    public static final int DEFAULT_CAPACITY = 100;

    // Instance Variables
    private Object[] stack;
    private int count;

    // Default Constructor
    public DSAStack() {
        this.stack = new Object[DEFAULT_CAPACITY];
        this.count = 0;
    }

    // Alternate Constructor
    public DSAStack(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.stack = new Object[maxCapacity];
        this.count = 0;
    }

    // Accessor Methods
    public int getCount() {
        return this.count;
    }

    public int getCapacity() {
        return this.stack.length;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public boolean isFull() {
        return this.count == this.stack.length;
    }

    public Object top() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return this.stack[this.count - 1];
    }

    // Mutator / Doing Methods
    public void push(Object value) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full");
        }
        this.stack[this.count] = value;
        this.count++;
    }

    public Object pop() {
        Object topVal = top();
        this.count--;
        this.stack[this.count] = null; // Clear reference for garbage collection
        return topVal;
    }
}
