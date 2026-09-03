import java.util.Scanner;

/**
 * Menu: Interactive CLI menu interface for Practical 03 (Stacks, Queues, and Equation Solver).
 */
public class Menu {
    private static Scanner sc = new Scanner(System.in);
    private static DSAStack stack = new DSAStack(10);
    private static DSAShufflingQueue shufflingQueue = new DSAShufflingQueue(10);
    private static DSACircularQueue circularQueue = new DSACircularQueue(10);
    private static EquationSolver solver = new EquationSolver();

    public static void main(String[] args) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=================================");
            System.out.println("   DSA PRACTICAL 03 - MAIN MENU  ");
            System.out.println("=================================");
            System.out.println("1. Interactive Stack Operations");
            System.out.println("2. Interactive Shuffling Queue Operations");
            System.out.println("3. Interactive Circular Queue Operations");
            System.out.println("4. Infix Equation Solver");
            System.out.println("0. Exit");
            System.out.print("Select an option (0-4): ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                handleSelection(choice);
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // clear invalid input
            }
        }
        System.out.println("Exiting Practical 03 program. Goodbye!");
        sc.close();
    }

    private static void handleSelection(int choice) {
        switch (choice) {
            case 1:
                stackMenu();
                break;
            case 2:
                shufflingQueueMenu();
                break;
            case 3:
                circularQueueMenu();
                break;
            case 4:
                equationSolverMenu();
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid option! Please enter a valid number (0-4).");
        }
    }

    // ==========================================
    // Interactive Stack Submenu
    // ==========================================
    private static void stackMenu() {
        int subChoice = -1;
        while (subChoice != 0) {
            System.out.println("\n--- STACK OPERATIONS (Count: " + stack.getCount() + "/" + stack.getCapacity() + ") ---");
            System.out.println("1. Push element");
            System.out.println("2. Pop element");
            System.out.println("3. Top / Peek element");
            System.out.println("4. Check isEmpty / isFull");
            System.out.println("5. Reset / Create new stack with custom capacity");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                subChoice = sc.nextInt();
                sc.nextLine();
                try {
                    switch (subChoice) {
                        case 1:
                            System.out.print("Enter value to push (String or Number): ");
                            String val = sc.nextLine();
                            stack.push(val);
                            System.out.println("Pushed '" + val + "' successfully.");
                            break;
                        case 2:
                            Object popped = stack.pop();
                            System.out.println("Popped element: " + popped);
                            break;
                        case 3:
                            System.out.println("Top element: " + stack.top());
                            break;
                        case 4:
                            System.out.println("isEmpty(): " + stack.isEmpty() + ", isFull(): " + stack.isFull());
                            break;
                        case 5:
                            System.out.print("Enter new stack capacity: ");
                            int cap = sc.nextInt();
                            sc.nextLine();
                            stack = new DSAStack(cap);
                            System.out.println("Created new DSAStack with capacity " + cap);
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid selection.");
                    }
                } catch (Exception e) {
                    System.out.println("Operation Error: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    // ==========================================
    // Interactive Shuffling Queue Submenu
    // ==========================================
    private static void shufflingQueueMenu() {
        int subChoice = -1;
        while (subChoice != 0) {
            System.out.println("\n--- SHUFFLING QUEUE (Count: " + shufflingQueue.getCount() + "/" + shufflingQueue.getCapacity() + ") ---");
            System.out.println("1. Enqueue element");
            System.out.println("2. Dequeue element");
            System.out.println("3. Peek front element");
            System.out.println("4. Check isEmpty / isFull");
            System.out.println("5. Reset / Create new queue with custom capacity");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                subChoice = sc.nextInt();
                sc.nextLine();
                try {
                    switch (subChoice) {
                        case 1:
                            System.out.print("Enter value to enqueue: ");
                            String val = sc.nextLine();
                            shufflingQueue.enqueue(val);
                            System.out.println("Enqueued '" + val + "' successfully.");
                            break;
                        case 2:
                            Object dequeued = shufflingQueue.dequeue();
                            System.out.println("Dequeued element: " + dequeued);
                            break;
                        case 3:
                            System.out.println("Front element (peek): " + shufflingQueue.peek());
                            break;
                        case 4:
                            System.out.println("isEmpty(): " + shufflingQueue.isEmpty() + ", isFull(): " + shufflingQueue.isFull());
                            break;
                        case 5:
                            System.out.print("Enter new queue capacity: ");
                            int cap = sc.nextInt();
                            sc.nextLine();
                            shufflingQueue = new DSAShufflingQueue(cap);
                            System.out.println("Created new DSAShufflingQueue with capacity " + cap);
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid selection.");
                    }
                } catch (Exception e) {
                    System.out.println("Operation Error: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    // ==========================================
    // Interactive Circular Queue Submenu
    // ==========================================
    private static void circularQueueMenu() {
        int subChoice = -1;
        while (subChoice != 0) {
            System.out.println("\n--- CIRCULAR QUEUE (Count: " + circularQueue.getCount() + "/" + circularQueue.getCapacity() + ") ---");
            System.out.println("1. Enqueue element");
            System.out.println("2. Dequeue element");
            System.out.println("3. Peek front element");
            System.out.println("4. View internal pointers (front/rear)");
            System.out.println("5. Check isEmpty / isFull");
            System.out.println("6. Reset / Create new circular queue with custom capacity");
            System.out.println("0. Back to Main Menu");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                subChoice = sc.nextInt();
                sc.nextLine();
                try {
                    switch (subChoice) {
                        case 1:
                            System.out.print("Enter value to enqueue: ");
                            String val = sc.nextLine();
                            circularQueue.enqueue(val);
                            System.out.println("Enqueued '" + val + "' successfully.");
                            break;
                        case 2:
                            Object dequeued = circularQueue.dequeue();
                            System.out.println("Dequeued element: " + dequeued);
                            break;
                        case 3:
                            System.out.println("Front element (peek): " + circularQueue.peek());
                            break;
                        case 4:
                            System.out.println("Front index: " + circularQueue.getFront() + ", Rear index: " + circularQueue.getRear() + ", Count: " + circularQueue.getCount());
                            break;
                        case 5:
                            System.out.println("isEmpty(): " + circularQueue.isEmpty() + ", isFull(): " + circularQueue.isFull());
                            break;
                        case 6:
                            System.out.print("Enter new queue capacity: ");
                            int cap = sc.nextInt();
                            sc.nextLine();
                            circularQueue = new DSACircularQueue(cap);
                            System.out.println("Created new DSACircularQueue with capacity " + cap);
                            break;
                        case 0:
                            break;
                        default:
                            System.out.println("Invalid selection.");
                    }
                } catch (Exception e) {
                    System.out.println("Operation Error: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    // ==========================================
    // Interactive Equation Solver Menu
    // ==========================================
    private static void equationSolverMenu() {
        System.out.println("\n--- EQUATION SOLVER ---");
        System.out.println("Note: Ensure spaces separate all operands, operators, and parentheses.");
        System.out.println("Example: ( 10.5 + 4.5 ) * 2 - 6 / 2");
        System.out.print("Enter infix equation: ");
        String equation = sc.nextLine();

        try {
            // Display Postfix queue terms
            DSAQueue postfixQ = solver.parseToPostfix(equation);
            StringBuilder sb = new StringBuilder();
            DSACircularQueue tempQ = new DSACircularQueue(postfixQ.getCount() + 10);
            while (!postfixQ.isEmpty()) {
                Object term = postfixQ.dequeue();
                sb.append(term).append(" ");
                tempQ.enqueue(term);
            }
            System.out.println("\nPostfix Representation: " + sb.toString().trim());

            double result = solver.solve(equation);
            System.out.println("Evaluation Result      : " + result);
        } catch (Exception e) {
            System.out.println("Error solving equation: " + e.getMessage());
        }
    }
}
