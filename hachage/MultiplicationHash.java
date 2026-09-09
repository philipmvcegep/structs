public class MultiplicationHash {
    private final int tableSize;
    private static final double KNUTH_CONSTANT = 0.6180339887; // (sqrt(5) - 1) / 2

    public MultiplicationHash(int tableSize) {
        this.tableSize = tableSize;
    }

    public int hash(int key) {
        double product = key * KNUTH_CONSTANT;
        double fractionalPart = product - Math.floor(product);
        return (int) Math.floor(tableSize * fractionalPart);
    }
}