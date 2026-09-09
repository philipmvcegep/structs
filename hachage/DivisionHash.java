public class DivisionHash {
    private final int tableSize;

    public DivisionHash(int tableSize) {
        // tableSize should ideally be a prime number
        this.tableSize = tableSize; 
    }

    public int hash(int key) {
        // Math.abs ensures positive index for negative keys
        return Math.abs(key) % tableSize;
    }
}