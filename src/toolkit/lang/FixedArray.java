package toolkit.lang;

public abstract class FixedArray<T> {
    class Node {
        T[] array;
        int length;
        Node next;
    }

    public final int blockSize;
    private int blocks;
    private Node begin;

    public FixedArray(int blockSize) {
        this.blockSize = blockSize;
    }

    public void add(T item) {

    }

    public void removeAt(int index) {

    }

    public T get(int index) {
        int blockIndex = (int) Math.ceil(index / blockSize);
        if (blockIndex < 0 || blockIndex >= blocks) {
            return null;
        }
        if (blockIndex == 0) {
            return begin.array[blockIndex];
        }
        return null;
    }

    protected abstract T[] makeArray(int length);
}
