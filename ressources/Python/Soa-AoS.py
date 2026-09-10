# Exercices pratiques : AoS vs SoA et Manipulations Avancées en Python
# Complétez les trois TODOs ci-dessous en utilisant dicts, listes, zip, enumerate et .items().

# Données initiales (AoS)
inventaire_aos = [
    {"item": "Épée", "prix": 150, "stock": 5},
    {"item": "Bouclier", "prix": 100, "stock": 2},
    {"item": "Potion", "prix": 25, "stock": 20}
]

# TODO 1: Convertir inventaire_aos (liste de dicts) en format SoA (dict de listes).
# Indice : Initialisez un dictionnaire de listes vides et remplissez-le en parcourant l'AoS.
inventaire_soa = {"item": [], "prix": [], "stock": []}
# --- VOTRE CODE ICI ---


# Données initiales (SoA)
joueurs_soa = {
    "pseudo": ["Hero99", "MageX", "Rogue01"],
    "score": [1200, 3400, 2100],
    "niveau": [15, 42, 28]
}

# TODO 2: Convertir joueurs_soa en format AoS (liste de dictionnaires) 
# en combinant les listes du dictionnaire avec zip().
joueurs_aos = []
# --- VOTRE CODE ICI ---


# TODO 3: Manipulation avancée SoA
# En utilisant enumerate sur la liste des scores, trouvez l'index du score le plus élevé,
# puis ajoutez un bonus de 500 points à cet index précis dans joueurs_soa["score"].
# Ensuite, parcourez joueurs_soa avec .items() pour afficher chaque attribut et ses valeurs mises à jour.
print("\n--- TODO 3 : Bonus au meilleur joueur et affichage .items() ---")
# --- VOTRE CODE ICI ---