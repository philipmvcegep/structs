"""
01_oop_basics.py
Sujets : self, super() et la convention de l'underscore _ pour les attributs privés/protégés
"""

# ==========================================
# PARTIE 1 : TROIS EXEMPLES INTRODUCTIFS
# ==========================================

# --- Exemple 1 : Comprendre 'self' ---
# 'self' est une référence à l'instance spécifique de l'objet en cours de création ou de manipulation.
# Il permet à chaque objet de maintenir son propre état (ses données).

class Dog:
    def __init__(self, name, breed):
        self.name = name          # Variable d'instance unique à cet objet
        self.breed = breed        # Variable d'instance unique à cet objet

    def bark(self):
        # self.name accède au nom spécifique de ce chien
        return f"{self.name} says Woof!"

dog1 = Dog("Rex", "German Shepherd")
dog2 = Dog("Luna", "Husky")

print(dog1.bark())  # Résultat : Rex says Woof!
print(dog2.bark())  # Résultat : Luna says Woof!


# --- Exemple 2 : Encapsulation et la convention de l'underscore '_' ---
# Python ne possède pas de modificateurs d'accès stricts comme 'private' en Java ou C++.
# À la place, un simple underscore en début de nom (_) est une convention forte dans la communauté.
# Elle indique aux développeurs : "Considérez cet attribut comme interne/privé, ne l'utilisez pas directement."

class BankAccount:
    def __init__(self, owner, balance):
        self.owner = owner
        self._balance = balance   # Attribut privé selon la convention

    def deposit(self, amount):
        if amount > 0:
            self._balance += amount
        return self._balance

    def get_balance(self):
        # Méthode "getter" sécurisée pour consulter l'attribut protégé
        return self._balance

account = BankAccount("Alice", 1000)
account.deposit(500)
print(f"Solde de {account.owner} : {account.get_balance()} $")
# Remarque : Techniquement, Python permet d'accéder à account._balance, 
# mais cela viole la convention de développement !


# --- Exemple 3 : Héritage et 'super()' ---
# 'super()' permet à une classe fille d'appeler les méthodes et le constructeur (__init__) 
# de sa classe parente, évitant ainsi la duplication de code.

class Animal:
    def __init__(self, name):
        self.name = name

    def make_sound(self):
        return "Generic sound"

class Cat(Animal):
    def __init__(self, name, lives_remaining=9):
        # super() appelle Animal.__init__ pour gérer l'initialisation de 'name'
        super().__init__(name)
        self.lives_remaining = lives_remaining

    def make_sound(self):
        # On peut redéfinir la méthode parente tout en faisant appel à sa version d'origine
        parent_sound = super().make_sound()
        return f"{self.name} meows! (Base sound was: {parent_sound})"

my_cat = Cat("Whiskers")
print(my_cat.make_sound())
print(f"Vies restantes : {my_cat.lives_remaining}")


# ==========================================
# PARTIE 2 : TROIS EXERCICES
# ==========================================

# Exercice 1 : Pratique de 'self'
# Créez une classe nommée 'Rectangle' avec une méthode __init__ qui prend 'width' et 'height'.
# Ajoutez une méthode nommée 'calculate_area' qui retourne width * height en utilisant self.
# TODO: Écrivez votre code ci-dessous


# Exercice 2 : Pratique de la convention '_'
# Créez une classe nommée 'UserSession' qui prend un 'username'.
# Attribuez-lui un attribut respectant la convention privée '_is_logged_in' initialisé à False.
# Ajoutez une méthode 'login()' qui le passe à True, et une méthode 'check_status()'
# qui indique si l'utilisateur est connecté.
# TODO: Écrivez votre code ci-dessous


# Exercice 3 : Pratique de l'héritage et de 'super()'
# Créez une classe parente 'Employee' avec un __init__ prenant 'name' et 'salary'.
# Créez une classe fille 'Manager' qui hérite d'Employee, ajoute un attribut 'department',
# et utilise 'super()' pour initialiser 'name' et 'salary'. Ajoutez une méthode 'get_details()' 
# qui retourne une chaîne formatée contenant ces trois informations.
# TODO: Écrivez votre code ci-dessous