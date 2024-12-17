package ds.project.Huffman;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<E extends Comparable<E>> {
    private List<E> list = new ArrayList<>();

    public MinHeap() {}

    public MinHeap(E[] array) {
        for (E o : array)
            if (o != null)
                add(o);
    }

    public void add(E newObject) {
        list.add(newObject);
        int newObjectIndex = list.size() - 1;
        int parent = parent(newObjectIndex);

        E temp;
        while (parent >= 0 && newObject.compareTo(list.get(parent)) < 0) {
            temp = list.get(parent);
            list.set(parent, newObject);
            list.set(newObjectIndex, temp);
            newObjectIndex = parent;
            parent = parent(newObjectIndex);
        }
    }

    public E removeRoot() {
        E removed = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        heapify(0);

        return removed;
    }

    public E top() {
        return (!list.isEmpty() ? list.get(0) : null);
    }

    public int getSize() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    private void heapify(int root) {
        int leftChild, rightChild, smallest = root;

        while (root < list.size()) {
            leftChild = leftChild(root);
            rightChild = rightChild(root);

            if (leftChild < list.size() && list.get(leftChild).compareTo(list.get(smallest)) < 0)
                smallest = leftChild;
            if (rightChild < list.size() && list.get(rightChild).compareTo(list.get(smallest)) < 0)
                smallest = rightChild;

            if (smallest != root) {
                E tmp = list.get(smallest);
                list.set(smallest, list.get(root));
                list.set(root, tmp);
                root = smallest;
            } else
                break;
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }
}
