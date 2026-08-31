/**
 * EquationSolver: Converts infix equations to postfix and evaluates them.
 */
public class EquationSolver {

    // Default Constructor
    public EquationSolver() {
    }

    // Doing Methods
    public double solve(String equation) {
        if (equation == null || equation.trim().isEmpty()) {
            throw new IllegalArgumentException("Equation cannot be empty");
        }
        DSAQueue postfixQueue = parseInfixToPostfix(equation);
        return evaluatePostfix(postfixQueue);
    }

    // Helper to allow Menu / TestHarness to inspect postfix form if needed
    public DSAQueue parseToPostfix(String equation) {
        return parseInfixToPostfix(equation);
    }

    // Internal / Private Methods
    private DSAQueue parseInfixToPostfix(String equation) {
        if (equation == null || equation.trim().isEmpty()) {
            throw new IllegalArgumentException("Equation cannot be null or empty");
        }

        DSAQueue postfixQueue = new DSACircularQueue();
        DSAStack opStack = new DSAStack();

        String[] tokens = equation.trim().split("\\s+");

        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            if (token.length() == 1 && isOperator(token.charAt(0))) {
                char currentOp = token.charAt(0);
                while (!opStack.isEmpty() && !opStack.top().equals('(')
                        && precedenceOf((Character) opStack.top()) >= precedenceOf(currentOp)) {
                    postfixQueue.enqueue(opStack.pop());
                }
                opStack.push(currentOp);
            } else if (token.equals("(")) {
                opStack.push('(');
            } else if (token.equals(")")) {
                boolean foundMatchingParen = false;
                while (!opStack.isEmpty()) {
                    char topOp = (Character) opStack.top();
                    if (topOp == '(') {
                        opStack.pop(); // discard '('
                        foundMatchingParen = true;
                        break;
                    } else {
                        postfixQueue.enqueue(opStack.pop());
                    }
                }
                if (!foundMatchingParen) {
                    throw new IllegalArgumentException("Mismatched parentheses: missing '('");
                }
            } else {
                try {
                    Double operand = Double.valueOf(token);
                    postfixQueue.enqueue(operand);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid token in equation: '" + token + "'");
                }
            }
        }

        while (!opStack.isEmpty()) {
            char topOp = (Character) opStack.pop();
            if (topOp == '(' || topOp == ')') {
                throw new IllegalArgumentException("Mismatched parentheses in equation");
            }
            postfixQueue.enqueue(topOp);
        }

        return postfixQueue;
    }

    private double evaluatePostfix(DSAQueue postfixQueue) {
        if (postfixQueue == null || postfixQueue.isEmpty()) {
            throw new IllegalArgumentException("Postfix queue is empty");
        }

        DSAStack operandStack = new DSAStack();

        while (!postfixQueue.isEmpty()) {
            Object term = postfixQueue.dequeue();

            if (term instanceof Character) {
                char op = (Character) term;
                if (operandStack.getCount() < 2) {
                    throw new IllegalArgumentException("Malformed postfix expression: insufficient operands for operator '" + op + "'");
                }
                double op2 = (Double) operandStack.pop();
                double op1 = (Double) operandStack.pop();
                double result = executeOperation(op, op1, op2);
                operandStack.push(result);
            } else if (term instanceof Double) {
                operandStack.push((Double) term);
            } else {
                throw new IllegalArgumentException("Unexpected term type in postfix queue: " + term.getClass().getSimpleName());
            }
        }

        if (operandStack.getCount() != 1) {
            throw new IllegalArgumentException("Malformed postfix expression: too many operands remaining");
        }

        return (Double) operandStack.pop();
    }

    private int precedenceOf(char theOp) {
        int prec = 0;
        switch (theOp) {
            case '+':
            case '-':
                prec = 1;
                break;
            case '*':
            case '/':
                prec = 2;
                break;
            default:
                prec = 0;
                break;
        }
        return prec;
    }

    private double executeOperation(char op, double op1, double op2) {
        double result = 0.0;
        switch (op) {
            case '+':
                result = op1 + op2;
                break;
            case '-':
                result = op1 - op2;
                break;
            case '*':
                result = op1 * op2;
                break;
            case '/':
                if (op2 == 0.0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = op1 / op2;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: '" + op + "'");
        }
        return result;
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
}
