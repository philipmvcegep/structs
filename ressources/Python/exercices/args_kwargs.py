"""
args_kwargs.py
Sujets : *args (arguments positionnels variables) et **kwargs (arguments nommés variables)
"""

# ==========================================
# PARTIE 1 : DEUX EXEMPLES INTRODUCTIFS
# ==========================================

# --- Exemple 1 : Utilisation de *args ---
# L'astérisque simple (*args) permet à une fonction d'accepter un nombre variable 
# d'arguments positionnels. À l'intérieur de la fonction, 'args' se comporte comme un tuple 
# contenant toutes les valeurs transmises.

def sum_all(*args):
    total = 0
    for num in args:
        total += num
    return total

print(sum_all(1, 2, 3))             # Résultat : 6
print(sum_all(10, 20, 30, 40, 50))  # Résultat : 150


# --- Exemple 2 : Utilisation de **kwargs ---
# Le double astérisque (**kwargs) permet d'accepter un nombre variable d'arguments nommés.
# À l'intérieur de la fonction, 'kwargs' se comporte comme un dictionnaire standard (clé-valeur).

def create_profile(**kwargs):
    print("Profil utilisateur généré :")
    for key, value in kwargs.items():
        print(f"- {key}: {value}")

create_profile(username="alice123", email="alice@example.com", age=25, role="Student")


# ==========================================
# PARTIE 2 : DEUX EXERCICES
# ==========================================

# Exercice 1 : Pratique de *args
# Écrivez une fonction nommée 'find_maximum(*args)' qui accepte un nombre indéterminé
# de nombres en arguments positionnels et retourne la valeur maximale.
# Si aucun argument n'est fourni, la fonction doit retourner None.
# TODO: Écrivez votre code ci-dessous


# Exercice 2 : Pratique de **kwargs
# Écrivez une fonction nommée 'build_car(**kwargs)' qui prend des arguments nommés 
# (comme make, model, year, color) et retourne un dictionnaire contenant ces informations.
# Si la clé "is_insured" n'est pas fournie dans les kwargs, ajoutez-la automatiquement avec la valeur True.
# TODO: Écrivez votre code ci-dessous