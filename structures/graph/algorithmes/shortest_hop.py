def matrix_multiply(A, B):
    n = len(A)
    result = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            for k in range(n):
                result[i][j] += A[i][k] * B[k][j]
    return result

def find_min_steps_matrix(adjacency_matrix, start_idx, target_idx, max_steps=5):
    current_matrix = [row[:] for row in adjacency_matrix]
    
    if current_matrix[start_idx][target_idx] > 0:
        return 1
        
    for step in range(2, max_steps + 1):
        current_matrix = matrix_multiply(current_matrix, adjacency_matrix)
        if current_matrix[start_idx][target_idx] > 0:
            return step
            
    return -1

server_matrix = [
    [0, 1, 1, 0, 0, 0],  # Server 0
    [0, 0, 0, 1, 0, 0],  # Server 1
    [0, 0, 0, 1, 1, 0],  # Server 2
    [0, 0, 0, 0, 0, 1],  # Server 3
    [0, 0, 0, 0, 0, 1],  # Server 4
    [0, 0, 0, 0, 0, 0]   # Server 5 (Terminal)
]

print("Minimum steps from Server 0 to Server 5:")
print(find_min_steps_matrix(server_matrix, 0, 5))