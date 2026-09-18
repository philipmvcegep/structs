class Node:

  def __init__(self, val):
    self.val = val
    self.left = None
    self.right = None


# 1. Pré-ordre (Racine -> Gauche -> Droite) [DFS]
def pre_order(node):
  if node is None:
    return
  print(node.val, end=" ")
  pre_order(node.left)
  pre_order(node.right)


# 2. In-ordre (Gauche -> Racine -> Droite) [DFS]
def in_order(node):
  if node is None:
    return
  in_order(node.left)
  print(node.val, end=" ")
  in_order(node.right)


# 3. Post-ordre (Gauche -> Droite -> Racine) [DFS]
def post_order(node):
  if node is None:
    return
  post_order(node.left)
  post_order(node.right)
  print(node.val, end=" ")


# 4. Parcours en largeur (Level-order / BFS) de manière récursive
def level_order_recursive(root):

  def get_height(node):
    if not node:
      return 0
    return 1 + max(get_height(node.left), get_height(node.right))

  def print_given_level(node, level):
    if not node:
      return
    if level == 1:
      print(node.val, end=" ")
    elif level > 1:
      print_given_level(node.left, level - 1)
      print_given_level(node.right, level - 1)

  height = get_height(root)
  for i in range(1, height + 1):
    print_given_level(root, i)


# --- Exemple d'utilisation ---
if __name__ == "__main__":
  # Construction d'un arbre binaire simple :
  #       1
  #      / \
  #     2   3
  #    / \
  #   4   5

  root = Node(1)
  root.left = Node(2)
  root.right = Node(3)
  root.left.left = Node(4)
  root.left.right = Node(5)

  print("Pré-ordre (DFS):        ", end="")
  pre_order(root)  # Sortie : 1 2 4 5 3
  print()

  print("In-ordre (DFS):         ", end="")
  in_order(root)  # Sortie : 4 2 5 1 3
  print()

  print("Post-ordre (DFS):       ", end="")
  post_order(root)  # Sortie : 4 5 2 3 1
  print()

  print("Largeur (BFS) récursif: ", end="")
  level_order_recursive(root)  # Sortie : 1 2 3 4 5
  print()