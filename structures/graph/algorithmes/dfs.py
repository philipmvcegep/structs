def dfs(graph, node, visited=None):
    if visited is None:
        visited = set()
    
    visited.add(node)
    print(node, end=" -> ")
    
    for neighbor in graph[node]:
        if neighbor not in visited:
            dfs(graph, neighbor, visited)
            
    return visited

# Example usage
expanded_network_graph = {
    'Alice': ['Bob', 'Charlie', 'Eve'],
    'Bob': ['Alice', 'Dave', 'Frank'],
    'Charlie': ['Alice', 'Eve'],
    'Dave': ['Bob', 'Frank'],
    'Eve': ['Alice', 'Charlie', 'Grace'],
    'Frank': ['Bob', 'Dave'],
    'Grace': ['Eve'],
    'Isolated_User': []
}

print("BFS Traversal (Expanded Network):")
print(bfs(expanded_network_graph, 'Alice'))
print("DONE\n")