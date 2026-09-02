#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// Structure pour un nœud de la file
typedef struct Node {
    int data;
    struct Node* next;
} Node;

// Structure de la File (Queue)
typedef struct {
    Node* front;
    Node* rear;
} Queue;

// Initialisation de la file
Queue* createQueue() {
    Queue* q = (Queue*)malloc(sizeof(Queue));
    q->front = q->rear = NULL;
    return q;
}

// Vérifier si la file est vide
bool isEmpty(Queue* q) {
    return q->front == NULL;
}

// Ajouter un élément à l'arrière (enqueue)
void enqueue(Queue* q, int value) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->data = value;
    newNode->next = NULL;
    
    if (q->rear == NULL) {
        q->front = q->rear = newNode;
        return;
    }
    
    q->rear->next = newNode;
    q->rear = newNode;
}

// Retirer un élément de l'avant (dequeue)
int dequeue(Queue* q) {
    if (isEmpty(q)) {
        printf("Erreur : Queue Underflow !\n");
        return -1;
    }
    
    Node* temp = q->front;
    int data = temp->data;
    
    q->front = q->front->next;
    
    if (q->front == NULL) {
        q->rear = NULL;
    }
    
    free(temp);
    return data;
}