from collections import deque

def kahn_topological_sort(graph):
    in_degree = {node: 0 for node in graph}
    for node in graph:
        for neighbor in graph[node]:
            in_degree[neighbor] += 1

    queue = deque([node for node in in_degree if in_degree[node] == 0])
    topo_order = []

    while queue:
        current = queue.popleft()
        topo_order.append(current)

        for neighbor in graph[current]:
            in_degree[neighbor] -= 1
            if in_degree[neighbor] == 0:
                queue.append(neighbor)

    if len(topo_order) == len(graph):
        return topo_order
    else:
        return "Error: Cycle detected! Cannot resolve dependencies."

# Example usage
course_dag = {
    'Math 101': ['Data Structures', 'Stats'],
    'English 101': ['Technical Writing'],
    'Intro Programming': ['Data Structures', 'Object-Oriented Programming'],
    'Data Structures': ['Algorithms', 'AI Basics'],
    'Object-Oriented Programming': ['Web Dev'],
    'Stats': ['Machine Learning'],
    'AI Basics': ['Machine Learning'],
    'Algorithms': [],
    'Technical Writing': [],
    'Web Dev': [],
    'Machine Learning': []
}

print("Kahn's Topological Sort (Expanded Curriculum):")
print(kahn_topological_sort(course_dag))
print()
