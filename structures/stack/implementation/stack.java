import java.util.Stack;

public class Main {
    
    // Function to push an element onto the stack (avec le message d'affichage du C)
    public static void push(Stack<Integer> stack, int value) {
        stack.push(value);
        System.out.println("Pushed " + value + " onto the stack");
    }

    // Function to pop an element from the stack
    public static int pop(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int popped = stack.pop();
        System.out.println("Popped " + popped + " from the stack");
        return popped;
    }

    // Function to peek the top element of the stack
    public static int peek(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        }
        return stack.peek();
    }

    // Driver Code
    public static void main(String[] args) {
        // Utilisation directe de la pile native de Java
        Stack<Integer> stack = new Stack<>();

        push(stack, 3);
        System.out.println("Top element: " + peek(stack));

        push(stack, 5);
        System.out.println("Top element: " + peek(stack));

        push(stack, 2);
        System.out.println("Top element: " + peek(stack));

        push(stack, 8);
        System.out.println("Top element: " + peek(stack));

        while (!stack.isEmpty()) {
            System.out.println("Top element: " + peek(stack));
            pop(stack);
        }
    }
}
