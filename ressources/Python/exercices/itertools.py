"""
itertools.py
Sujets : itertools (groupby avec lambda, product, permutations/combinations, chain)
Chaque section présente un exemple direct suivi de son exercice pratique associé.
"""

import itertools

# ==========================================
# SECTION 1 : GROUPBY AVEC LAMBDA
# ==========================================

# --- Exemple ---
# 'groupby' regroupe les éléments adjacents selon une clé. 
# On utilise une fonction 'lambda' pour définir cette règle de regroupement à la volée.
# Note : La source doit obligatoirement être triée selon la même clé avant d'être groupée !

words = ["bat", "car", "apple", "cat", "ant", "banana"]
# Tri des mots par leur longueur
words_sorted = sorted(words, key=len)

print("--- Exemple : groupby avec lambda (longueur des mots) ---")
for length, group in itertools.groupby(words_sorted, key=lambda w: len(w)):
    print(f"Mots de longueur {length} : {list(group)}")
print()

# --- Exercice 1 ---
# Contexte : Vous avez une liste de notes d'étudiants.
# 1. Triez cette liste de notes par ordre croissant.
# 2. Utilisez 'itertools.groupby' avec une fonction 'lambda' pour regrouper les notes 
#    selon qu'elles sont réussies (>= 60) ou échouées (< 60). (Indice : la lambda retourne True ou False).
# 3. Affichez les groupes formés.
grades = [45, 82, 90, 55, 70, 30, 88]
# TODO: Écrivez votre code ci-dessous


# ==========================================
# SECTION 2 : PRODUCT (Produit cartésien)
# ==========================================

# --- Exemple ---
# 'product' génère toutes les combinaisons possibles entre plusieurs itérables,
# remplaçant efficacement des boucles 'for' imbriquées.

print("--- Exemple : product ---")
colors = ["Red", "Blue"]
sizes = ["S", "M"]

for color, size in itertools.product(colors, sizes):
    print(f"Option : {color} / {size}")
print()

# --- Exercice 2 ---
# Contexte : Configuration de matériel informatique.
# Utilisez 'itertools.product' pour générer et afficher toutes les configurations possibles 
# en combinant les processeurs, la RAM et le stockage ci-dessous. 
# Affichez chaque configuration et comptez le nombre total de combinaisons.
cpus = ["i5", "i7"]
rams = [16, 32]
storages = [512, 1024]
# TODO: Écrivez votre code ci-dessous


# ==========================================
# SECTION 3 : COMBINATIONS ET PERMUTATIONS
# ==========================================

# --- Exemple ---
# 'permutations' prend en compte l'ordre des éléments, tandis que 'combinations' l'ignore.

print("--- Exemple : permutations et combinations ---")
items = ["A", "B", "C"]

# Permutations (l'ordre importe, ex: AB != BA)
print("Permutations (taille 2) :")
for p in itertools.permutations(items, 2):
    print(p)

# Combinations (l'ordre n'importe pas, ex: AB == BA)
print("\nCombinations (taille 2) :")
for c in itertools.combinations(items, 2):
    print(c)
print()

# --- Exercice 3 ---
# Contexte : Équipes de travail et podiums.
# 1. Utilisez 'itertools.combinations' pour trouver toutes les façons de former 
#    un duo parmi 4 candidats : ["Alice", "Bob", "Charlie", "Diana"].
# 2. Utilisez 'itertools.permutations' pour déterminer tous les classements possibles 
#    (or, argent, bronze) pour une course finale avec 3 coureurs : ["Sprinter1", "Sprinter2", "Sprinter3"].
candidates = ["Alice", "Bob", "Charlie", "Diana"]
runners = ["Sprinter1", "Sprinter2", "Sprinter3"]
# TODO: Écrivez votre code ci-dessous


# ==========================================
# SECTION 4 : CHAIN
# ==========================================

# --- Exemple ---
# 'chain' permet de parcourir plusieurs listes ou tuples séquentiellement 
# comme s'ils ne formaient qu'un seul grand itérable, sans les fusionner en mémoire.

print("--- Exemple : chain ---")
list1 = [1, 2]
list2 = [3, 4]
list3 = [5, 6]

for val in itertools.chain(list1, list2, list3):
    print(val, end=" ")
print("\n")

# --- Exercice 4 ---
# Contexte : Fusion de flux de données provenant de différents serveurs.
# Vous avez trois listes de clients connectés sur des serveurs distincts. 
# Utilisez 'itertools.chain' pour parcourir tous les clients d'un seul coup 
# et afficher un message de bienvenue pour chacun d'eux.
server_1 = ["Alice", "Bob"]
server_2 = ["Charlie", "Diana"]
server_3 = ["Eve", "Frank"]
# TODO: Écrivez votre code ci-dessous