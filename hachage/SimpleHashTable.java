public class SimpleHashTable<K, V> {
    
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public SimpleHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
    }

    private int getIndex(K key) {
        int hash = Math.abs(key.hashCode());
        return hash % capacity;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        Entry<K, V> head = buckets[index];

        Entry<K, V> current = head;
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // Insert new entry at the head of the bucket's chain
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = head;
        buckets[index] = newEntry;
        size++;
    }

    public V get(K key) {
        int index = getIndex(key);
        Entry<K, V> current = buckets[index];

        // Traverse the chain in the bucket
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null; 
    }

    public int size() {
        return size;
    }
}