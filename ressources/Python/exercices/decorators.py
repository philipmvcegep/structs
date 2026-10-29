"""
decorators.py
Sujets : Décorateurs réalistes (Mesure de performance et Mécanisme de Retry)
"""

import time
import random

# ==========================================
# PARTIE 1 : DEUX EXEMPLES INTRODUCTIFS (CAS RÉELS)
# ==========================================

# --- Exemple 1 : Le chronomètre de performance (@measure_time) ---
# En production, on utilise souvent des décorateurs pour mesurer le temps d'exécution 
# d'une fonction (par exemple, une requête SQL ou un traitement lourd) sans polluer le code métier.

def measure_time(func):
    def wrapper(*args, **kwargs):
        start_time = time.time()
        result = func(*args, **kwargs)
        end_time = time.time()
        duration = end_time - start_time
        print(f"[PERF] La fonction '{func.__name__}' a pris {duration:.4f} secondes à s'exécuter.")
        return result
    return wrapper

@measure_time
def heavy_computation(n):
    total = 0
    for i in range(n):
        total += i
    return total

# Appel de la fonction mesurée
print("Lancement du calcul lourd...")
result = heavy_computation(10_000_000)
print(f"Résultat : {result}\n")


# --- Exemple 2 : Le contrôle d'accès / Authentification simulée (@require_auth) ---
# Un autre grand classique : vérifier si un utilisateur est connecté ou a les bons 
# droits avant d'exécuter une fonction sensible (ex: route web / API).

current_user = {"username": "alice", "is_admin": False}

def require_admin(func):
    def wrapper(*args, **kwargs):
        if not current_user.get("is_admin", False):
            raise PermissionError(f"Accès refusé : l'utilisateur '{current_user['username']}' n'est pas admin.")
        return func(*args, **kwargs)
    return wrapper

@require_admin
def delete_database():
    return "Base de données supprimée avec succès !"

# Test du décorateur d'autorisation
try:
    print(delete_database())
except PermissionError as e:
    print(f"Bloqué par le décorateur : {e}")


# ==========================================
# PARTIE 2 : DEUX EXERCICES POUR LES ÉTUDIANTS (CAS RÉELS)
# ==========================================

# Exercice 1 : Le compteur d'appels (@count_calls)
# Contexte : On veut savoir combien de fois une fonction critique a été exécutée.
# 1. Créez un décorateur nommé 'count_calls' qui garde en mémoire le nombre de fois 
#    qu'une fonction est appelée (indice : on peut utiliser un attribut sur la fonction wrapper, 
#    ou une variable, mais en Python on peut attacher un compteur à la fonction ou utiliser 
#    des variables non-locales).
# 2. À chaque appel de la fonction décorée, le wrapper doit afficher : 
#    "La fonction '[nom_de_la_fonction]' a été appelée [X] fois."
# 3. Créez une fonction simple, par exemple 'send_email(recipient)', décorez-la, et testez-la 
#    en l'appelant 3 de suite.
# TODO: Écrivez votre code ci-dessous


# Exercice 2 : Le décorateur de nouvelle tentative en cas d'échec (@retry)
# Contexte : En programmation réseau, une API externe peut échouer temporairement.
# 1. Créez un décorateur nommé 'retry(max_attempts=3)' (attention, c'est un décorateur qui prend des arguments !).
# 2. Le wrapper doit tenter d'exécuter la fonction. Si une exception (ex: ConnectionError) est levée,
#    le décorateur attrape l'erreur, attend 1 seconde, et réessaie, jusqu'à atteindre 'max_attempts'.
# 3. Si toutes les tentatives échouent, il relève l'exception finale.
# Testez-le avec une fonction qui simule un échec aléatoire (random.random()).
# TODO: Écrivez votre code ci-dessous