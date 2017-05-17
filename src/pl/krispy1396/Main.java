package pl.krispy1396;

public class Main {

    public static void main(String[] args) {
        BST<Integer> tree = new BST(new Integer[]{5,10,9,1,15,4,6,8});
        Heap<Integer> heap = new Heap(new Integer[]{5,10,9,1,15,4,6,8});
        System.out.println("***** Tree tests: *****");
        System.out.println("Tree: "+ tree);
        tree.remove(5);
        System.out.println("Tree after removing 5: "+tree);
        System.out.println("Max: "+tree.max());
        System.out.println("Min: "+tree.min());
        System.out.println("Find 9: "+tree.find(9));
        System.out.println("Subtree with root in 10: "+tree.subtree(10));
        System.out.println("Path between 4 & 15: "+ tree.path(4,15));
        System.out.println("Path between 4 & 5: "+ tree.path(4,5));
        System.out.println("Size: "+tree.size());
        System.out.println("Size of empty tree: "+ new BST<Integer>().size());

        System.out.println();
        System.out.println("***** Heap tests: *****");
        System.out.println("Heap: "+ heap);
        heap.add(16);
        System.out.println("Heap after adding 16: "+heap);
        heap.remove(1);
        System.out.println("Heap after deleting element at 1 index: ");
        System.out.println("Max: "+heap.max());
        System.out.println("Min: "+heap.min());
        System.out.println("Size: "+heap.size());
        System.out.println("Find index of first occurance of 16: "+heap.find(16));
        System.out.println("Subheap with root at index 5: "+heap.subHeap(5));
        System.out.println("Size of empty heap: "+ new Heap<Integer>().size());
    }
}
