from collections import deque

# Initialisation d'une deque
d = deque()

# Ajout aux deux extrémités ($O(1)$)
d.appendleft("Début 1")  # Ajoute au début
d.append("Fin 1")       # Ajoute à la fin
d.appendleft("Début 2")

print("Deque actuelle :", d)

# Suppression aux deux extrémités ($O(1)$)
print("Pop du début :", d.popleft())  # Retire "Début 2"
print("Pop de la fin :", d.pop())     # Retire "Fin 1"

print("Deque finale :", d)