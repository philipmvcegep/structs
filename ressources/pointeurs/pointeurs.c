#include <stdio.h>

int main() {
    // 1. Déclaration d'une variable classique
    int score = 42;

    // 2. Déclaration d'un pointeur qui stocke l'adresse de 'score'
    int *ptr = &score;

    printf("--- 1. LES BASES ---\n");
    printf("Valeur de score                : %d\n", score);
    printf("Adresse de score (&score)      : %p\n", (void*)&score);
    printf("Valeur stockée dans le pointeur : %p\n", (void*)ptr);
    printf("Adresse du pointeur lui-même   : %p\n", (void*)&ptr);

    printf("\n--- 2. LE DEFERENCEMENT (*)---\n");
    // *ptr signifie : "va chercher la valeur située à l'adresse pointée"
    printf("Valeur via le pointeur (*ptr)  : %d\n", *ptr);

    // 3. Modifier la valeur via le pointeur
    *ptr = 100;
    printf("\n* On modifie *ptr = 100 *\n");
    printf("Nouvelle valeur de score       : %d\n", score);

    return 0;
}
