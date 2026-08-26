# Python Implementation of a Stack using an Array / Fixed Capacity

MAX_SIZE = 100

class Stack:
    def __init__(self):
        # Initialisation du tableau avec une taille fixe et de top à -1
        self.arr = [0] * MAX_SIZE
        self.top = -1

# Function to initialize the stack (géré par le constructeur en Python, mais recréé pour correspondre au C)
def initialize(stack):
    stack.top = -1

# Function to check if the stack is empty
def isEmpty(stack):
    return stack.top == -1

# Function to check if the stack is full
def isFull(stack):
    return stack.top >= MAX_SIZE - 1

# Function to push an element onto the stack
def push(stack, value):
    if isFull(stack):
        print("Stack Overflow")
        return
    stack.top += 1
    stack.arr[stack.top] = value
    print(f"Pushed {value} onto the stack")

# Function to pop an element from the stack
def pop(stack):
    if isEmpty(stack):
        print("Stack Underflow")
        return -1

    popped = stack.arr[stack.top]
    stack.top -= 1
    print(f"Popped {popped} from the stack")
    return popped

# Function to peek the top element of the stack
def peek(stack):
    if isEmpty(stack):
        print("Stack is empty")
        return -1
    return stack.arr[stack.top]

# Main
if __name__ == "__main__":
    stack = Stack()
    initialize(stack)

    push(stack, 3)
    print(f"Top element: {peek(stack)}")

    push(stack, 5)
    print(f"Top element: {peek(stack)}")

    push(stack, 2)
    print(f"Top element: {peek(stack)}")

    push(stack, 8)
    print(f"Top element: {peek(stack)}")

    while not isEmpty(stack):
        print(f"Top element: {peek(stack)}")
        print(f"Popped element: {pop(stack)}")
