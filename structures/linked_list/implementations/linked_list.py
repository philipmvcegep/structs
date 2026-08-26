## Python Implementation of Singly Linked List (Matching C Functionality)

class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

# Function to create a new node (handled automatically in Python, but mirrors C structure)
def createNode(data):
    return Node(data)

# Function to insert a new element at the beginning of the singly linked list
def insertAtFirst(head_ref, data):
    newNode = createNode(data)
    newNode.next = head_ref[0]
    head_ref[0] = newNode

# Function to insert a new element at the end of the singly linked list
def insertAtEnd(head_ref, data):
    newNode = createNode(data)
    if head_ref[0] is None:
        head_ref[0] = newNode
        return
    temp = head_ref[0]
    while temp.next is not None:
        temp = temp.next
    temp.next = newNode

# Function to insert a new element at a specific position in the singly linked list
def insertAtPosition(head_ref, data, position):
    if position == 0:
        insertAtFirst(head_ref, data)
        return
    
    newNode = createNode(data)
    temp = head_ref[0]
    for i in range(position - 1):
        if temp is None:
            print("Position out of range")
            return
        temp = temp.next
        
    if temp is None:
        print("Position out of range")
        return
        
    newNode.next = temp.next
    temp.next = newNode

# Function to delete the first node of the singly linked list
def deleteFromFirst(head_ref):
    if head_ref[0] is None:
        print("List is empty")
        return
    temp = head_ref[0]
    head_ref[0] = temp.next

# Function to delete the last node of the singly linked list
def deleteFromEnd(head_ref):
    if head_ref[0] is None:
        print("List is empty")
        return
    
    temp = head_ref[0]
    if temp.next is None:
        head_ref[0] = None
        return
        
    while temp.next.next is not None:
        temp = temp.next
    temp.next = None

# Function to delete a node at a specific position in the singly linked list
def deleteAtPosition(head_ref, position):
    if head_ref[0] is None:
        print("List is empty")
        return
        
    if position == 0:
        deleteFromFirst(head_ref)
        return
        
    temp = head_ref[0]
    for i in range(position - 1):
        if temp is None or temp.next is None:
            print("Position out of range")
            return
        temp = temp.next
        
    if temp is None or temp.next is None:
        print("Position out of range")
        return
        
    temp.next = temp.next.next

# Function to print the LinkedList
def print_list(head):
    temp = head
    while temp is not None:
        print(f"{temp.data} -> ", end="")
        temp = temp.next
    print("NULL")

# Driver Code
if __name__ == "__main__":
    # Using a list wrap [head] to mimic pass-by-reference (pointer to pointer) in C
    head = [None]
    
    insertAtFirst(head, 10)
    print("Linked list after inserting the node:10 at the beginning")
    print_list(head[0])
    
    print("Linked list after inserting the node:20 at the end")
    insertAtEnd(head, 20)
    print_list(head[0])
    
    print("Linked list after inserting the node:5 at the end")
    insertAtEnd(head, 5)
    print_list(head[0])
    
    print("Linked list after inserting the node:30 at the end")
    insertAtEnd(head, 30)
    print_list(head[0])
    
    print("Linked list after inserting the node:15 at position 2")
    insertAtPosition(head, 15, 2)
    print_list(head[0])
    
    print("Linked list after deleting the first node:")
    deleteFromFirst(head)
    print_list(head[0])
    
    print("Linked list after deleting the last node:")
    deleteFromEnd(head)
    print_list(head[0])
    
    print("Linked list after deleting the node at position 1:")
    deleteAtPosition(head, 1)
    print_list(head[0])
