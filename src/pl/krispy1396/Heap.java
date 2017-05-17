package pl.krispy1396;

import java.util.ArrayList;

public class Heap<Key extends Comparable<Key>> {
    private ArrayList<Key> heapList;

    public Heap() {
        heapList = new ArrayList<>();
    }

    public Heap(Key[] keys) {
        heapList = new ArrayList<Key>();
        for (Key key : keys) {
            add(key);
        }
    }

    public boolean add(Key key) {
        heapList.add(key);
        int index = heapList.size() - 1;
        while (index != 0 && heapList.get(index).compareTo(heapList.get((index - 1) / 2)) > 0) {
            Key temp = heapList.get(index);
            heapList.set(index, heapList.get((index - 1) / 2));
            heapList.set((index - 1) / 2, temp);
            index = (index - 1) / 2;
        }
        return true;
    }

    public boolean remove(int index) {
        if (index > heapList.size() - 1) return false;
        heapList.set(index, heapList.get(heapList.size() - 1));
        heapList.remove(heapList.size() - 1);
        boolean isDone = false;
        int next;
        while (!isDone && (next = 2 * index + 1) < heapList.size()) {
            if (next < heapList.size() - 1 && heapList.get(next).compareTo(heapList.get(next + 1)) < 0) ++next;
            if (heapList.get(index).compareTo(heapList.get(next)) < 0) {
                Key temp = heapList.get(index);
                heapList.set(index, heapList.get(next));
                heapList.set(next, temp);
                index = next;
            } else isDone = true;
        }
        return true;
    }

    public Key max() {
        if (heapList.isEmpty()) return null;
        return heapList.get(0);
    }

    public Key min() {
        if (heapList.isEmpty()) return null;
        Key min = heapList.get(heapList.size() - 1);
        int parentIndex = ((heapList.size() - 1) / 2 >= 0) ? (heapList.size() - 1) / 2 : 0;
        if (heapList.size() > 1) for (int i = heapList.size() - 2; i >= parentIndex; --i) {
            if (heapList.get(i).compareTo(min) < 0) min = heapList.get(i);
        }
        return min;
    }

    public int size() {
        if (heapList.isEmpty()) return -1;
        if (heapList.size() == 1) return 0;
        int size = 0;
        int index = heapList.size() - 1;
        while (index > 0) {
            index = (index - 1) / 2;
            ++size;
        }
        return size;
    }

    public Heap subHeap(int index) {
        if (index > heapList.size() - 1) return null;
        Heap<Key> heap = new Heap<>();
        ArrayList<Key> list = heap.heapList;
        list.add(heapList.get(index));
        int i = index;
        while (i < list.size()) {
            if (2 * i + 1 < heapList.size()) list.add(heapList.get(2 * i + 1));
            if (2 * i + 2 < heapList.size()) list.add(heapList.get(2 * i + 2));
            ++i;
        }
        return heap;
    }

    public int find(Key key) {
        if (heapList.isEmpty()) return -1;
        for (int i = 0; i < heapList.size(); ++i) {
            if (heapList.get(i).compareTo(key) == 0) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (Key key : heapList) string.append(key+" ");
        return string.toString();
    }
}
