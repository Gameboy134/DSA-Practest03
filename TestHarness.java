/**
 * TestHarness: Comprehensive unit test suite for DSAStack, DSAShufflingQueue,
 * DSACircularQueue, and EquationSolver.
 */
public class TestHarness {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   DSA PRACTICAL 03 COMPREHENSIVE TEST HARNESS   ");
        System.out.println("=================================================\n");

        testDSAStack();
        testDSAShufflingQueue();
        testDSACircularQueue();
        testQueuePolymorphism();
        testEquationSolver();

        System.out.println("\n=================================================");
        System.out.println("TEST SUMMARY:");
        System.out.println("  Tests Passed : " + testsPassed);
        System.out.println("  Tests Failed : " + testsFailed);
        System.out.println("  Total Tests  : " + (testsPassed + testsFailed));
        if (testsFailed == 0) {
            System.out.println("  ALL TESTS PASSED SUCCESSFULLY!");
        } else {
            System.out.println("  SOME TESTS FAILED - PLEASE REVIEW LOGS.");
        }
        System.out.println("=================================================");
    }

    private static void assertTrue(String testName, boolean condition) {
        if (condition) {
            System.out.println("  [PASS] " + testName);
            testsPassed++;
        } else {
            System.out.println("  [FAIL] " + testName);
            testsFailed++;
        }
    }

    private static void assertEqual(String testName, Object expected, Object actual) {
        boolean match;
        if (expected == null && actual == null) {
            match = true;
        } else if (expected != null && expected.equals(actual)) {
            match = true;
        } else if (expected instanceof Number && actual instanceof Number) {
            match = Math.abs(((Number) expected).doubleValue() - ((Number) actual).doubleValue()) < 0.0001;
        } else {
            match = false;
        }

        if (match) {
            System.out.println("  [PASS] " + testName + " (Value: " + actual + ")");
            testsPassed++;
        } else {
            System.out.println("  [FAIL] " + testName + " - Expected: " + expected + ", Got: " + actual);
            testsFailed++;
        }
    }

    // ==========================================
    // 1. DSAStack Tests
    // ==========================================
    private static void testDSAStack() {
        System.out.println(">>> TESTING DSAStack <<<");

        DSAStack stack = new DSAStack(3);
        assertTrue("Stack initially empty", stack.isEmpty());
        assertEqual("Stack initial count is 0", 0, stack.getCount());
        assertEqual("Stack capacity is 3", 3, stack.getCapacity());

        // Push test
        stack.push("Alpha");
        stack.push("Beta");
        assertEqual("Count after 2 pushes", 2, stack.getCount());
        assertEqual("Top is Beta", "Beta", stack.top());

        stack.push("Gamma");
        assertTrue("Stack is full at capacity 3", stack.isFull());

        // Push overflow test
        boolean overflowCaught = false;
        try {
            stack.push("Delta");
        } catch (IllegalStateException e) {
            overflowCaught = true;
        }
        assertTrue("Stack overflow exception caught", overflowCaught);

        // Pop test
        assertEqual("Pop 1 (Gamma)", "Gamma", stack.pop());
        assertEqual("Pop 2 (Beta)", "Beta", stack.pop());
        assertEqual("Pop 3 (Alpha)", "Alpha", stack.pop());
        assertTrue("Stack is empty after popping all", stack.isEmpty());

        // Underflow test
        boolean underflowCaught = false;
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            underflowCaught = true;
        }
        assertTrue("Stack underflow exception on pop() caught", underflowCaught);

        boolean topUnderflowCaught = false;
        try {
            stack.top();
        } catch (IllegalStateException e) {
            topUnderflowCaught = true;
        }
        assertTrue("Stack underflow exception on top() caught", topUnderflowCaught);

        // Object polymorphism (heterogeneous elements)
        DSAStack mixedStack = new DSAStack();
        mixedStack.push(42);
        mixedStack.push(3.14159);
        mixedStack.push("Hello");
        mixedStack.push('Z');
        assertEqual("Mixed stack pop Character", 'Z', mixedStack.pop());
        assertEqual("Mixed stack pop String", "Hello", mixedStack.pop());
        assertEqual("Mixed stack pop Double", 3.14159, mixedStack.pop());
        assertEqual("Mixed stack pop Integer", 42, mixedStack.pop());
        System.out.println();
    }

    // ==========================================
    // 2. DSAShufflingQueue Tests
    // ==========================================
    private static void testDSAShufflingQueue() {
        System.out.println(">>> TESTING DSAShufflingQueue <<<");

        DSAShufflingQueue queue = new DSAShufflingQueue(3);
        assertTrue("Queue initially empty", queue.isEmpty());
        assertEqual("Initial count is 0", 0, queue.getCount());

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertTrue("Queue is full", queue.isFull());
        assertEqual("Peek returns first element 10", 10, queue.peek());

        // Overflow test
        boolean overflowCaught = false;
        try {
            queue.enqueue(40);
        } catch (IllegalStateException e) {
            overflowCaught = true;
        }
        assertTrue("Shuffling queue overflow exception caught", overflowCaught);

        // Dequeue and shifting test
        assertEqual("Dequeue 1 (10)", 10, queue.dequeue());
        assertEqual("Peek after dequeue 10 is 20", 20, queue.peek());
        assertEqual("Dequeue 2 (20)", 20, queue.dequeue());

        // Re-enqueue after shifting
        queue.enqueue(99);
        assertEqual("Count after re-enqueue", 2, queue.getCount());
        assertEqual("Dequeue 3 (30)", 30, queue.dequeue());
        assertEqual("Dequeue 4 (99)", 99, queue.dequeue());
        assertTrue("Queue empty after dequeuing all", queue.isEmpty());

        // Underflow test
        boolean underflowCaught = false;
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            underflowCaught = true;
        }
        assertTrue("Shuffling queue underflow exception caught", underflowCaught);
        System.out.println();
    }

    // ==========================================
    // 3. DSACircularQueue Tests
    // ==========================================
    private static void testDSACircularQueue() {
        System.out.println(">>> TESTING DSACircularQueue <<<");

        DSACircularQueue queue = new DSACircularQueue(3);
        assertTrue("Circular queue initially empty", queue.isEmpty());
        assertEqual("Initial count is 0", 0, queue.getCount());

        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        assertTrue("Circular queue is full", queue.isFull());

        // Overflow test
        boolean overflowCaught = false;
        try {
            queue.enqueue("Fourth");
        } catch (IllegalStateException e) {
            overflowCaught = true;
        }
        assertTrue("Circular queue overflow exception caught", overflowCaught);

        // Dequeue and wrap-around test
        assertEqual("Dequeue 1 (First)", "First", queue.dequeue());
        assertEqual("Dequeue 2 (Second)", "Second", queue.dequeue());
        assertEqual("Count after 2 dequeues", 1, queue.getCount());

        // Now wrap around rear index past array length
        queue.enqueue("Fourth");
        queue.enqueue("Fifth");
        assertTrue("Circular queue full after wrap-around", queue.isFull());

        assertEqual("Dequeue 3 (Third)", "Third", queue.dequeue());
        assertEqual("Dequeue 4 (Fourth)", "Fourth", queue.dequeue());
        assertEqual("Dequeue 5 (Fifth)", "Fifth", queue.dequeue());
        assertTrue("Circular queue empty after all dequeued", queue.isEmpty());

        // Underflow test
        boolean underflowCaught = false;
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            underflowCaught = true;
        }
        assertTrue("Circular queue underflow exception caught", underflowCaught);
        System.out.println();
    }

    // ==========================================
    // 4. Queue Polymorphism Tests
    // ==========================================
    private static void testQueuePolymorphism() {
        System.out.println(">>> TESTING Queue Polymorphism (DSAQueue base class) <<<");

        DSAQueue shuffling = new DSAShufflingQueue(5);
        DSAQueue circular = new DSACircularQueue(5);

        testGenericQueueBehavior("Shuffling Queue Polymorphism", shuffling);
        testGenericQueueBehavior("Circular Queue Polymorphism", circular);
        System.out.println();
    }

    private static void testGenericQueueBehavior(String testName, DSAQueue queue) {
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        assertEqual(testName + " peek", "A", queue.peek());
        assertEqual(testName + " dequeue A", "A", queue.dequeue());
        assertEqual(testName + " dequeue B", "B", queue.dequeue());
        queue.enqueue("D");
        assertEqual(testName + " dequeue C", "C", queue.dequeue());
        assertEqual(testName + " dequeue D", "D", queue.dequeue());
        assertTrue(testName + " is empty", queue.isEmpty());
    }

    // ==========================================
    // 5. EquationSolver Tests
    // ==========================================
    private static void testEquationSolver() {
        System.out.println(">>> TESTING EquationSolver <<<");

        EquationSolver solver = new EquationSolver();

        // Simple arithmetic
        assertEqual("Simple addition '3 + 4'", 7.0, solver.solve("3 + 4"));
        assertEqual("Simple subtraction '10 - 4'", 6.0, solver.solve("10 - 4"));
        assertEqual("Simple multiplication '6 * 7'", 42.0, solver.solve("6 * 7"));
        assertEqual("Simple division '15 / 3'", 5.0, solver.solve("15 / 3"));

        // Operator Precedence (* / before + -)
        assertEqual("Precedence '3 + 4 * 2'", 11.0, solver.solve("3 + 4 * 2"));
        assertEqual("Precedence '10 - 3 * 2 + 8 / 4'", 6.0, solver.solve("10 - 3 * 2 + 8 / 4"));

        // Left-to-Right Associativity (critical for - and /)
        assertEqual("Left-to-right subtraction '10 - 4 - 2'", 4.0, solver.solve("10 - 4 - 2"));
        assertEqual("Left-to-right division '24 / 4 / 2'", 3.0, solver.solve("24 / 4 / 2"));

        // Parentheses
        assertEqual("Parentheses '( 3 + 4 ) * 2'", 14.0, solver.solve("( 3 + 4 ) * 2"));
        assertEqual("Parentheses '2 * ( 3 + 4 )'", 14.0, solver.solve("2 * ( 3 + 4 )"));
        assertEqual("Nested parentheses '( ( 2 + 3 ) * ( 4 - 1 ) ) / 5'", 3.0, solver.solve("( ( 2 + 3 ) * ( 4 - 1 ) ) / 5"));

        // Floating point numbers
        assertEqual("Decimals '2.5 * 4.0 + 1.25'", 11.25, solver.solve("2.5 * 4.0 + 1.25"));

        // Error handling tests
        boolean divByZeroCaught = false;
        try {
            solver.solve("10 / 0");
        } catch (ArithmeticException e) {
            divByZeroCaught = true;
        }
        assertTrue("Division by zero exception caught", divByZeroCaught);

        boolean parenMismatchCaught = false;
        try {
            solver.solve("( 3 + 4");
        } catch (IllegalArgumentException e) {
            parenMismatchCaught = true;
        }
        assertTrue("Mismatched opening parenthesis caught", parenMismatchCaught);

        boolean emptyEquationCaught = false;
        try {
            solver.solve("   ");
        } catch (IllegalArgumentException e) {
            emptyEquationCaught = true;
        }
        assertTrue("Empty equation exception caught", emptyEquationCaught);
    }
}
