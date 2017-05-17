package pl.krispy1396;

import java.util.LinkedList;

public class BST<Key extends Comparable<Key>> {
    private class Node {
        private Key key;
        private Node left, right, parent;

        public Node(Key key) {
            this.key = key;
        }

        @Override
        public String toString() {
            String string = "";
            if (left != null) string += left.toString();
            string += key.toString() + " ";
            if (right != null) string += right.toString();
            return string;
        }
    }


    private Node root;

    public BST() {
        root = null;
    }

    public BST(Key[] keys){
        for (Key key: keys) {
            try {
                add(key);
            } catch (DuplicateElementException e) {
                System.out.println("Adding duplicate elements are not allowed");
            }
        }
    }

    public Node add(Key key) throws DuplicateElementException {
        Node current = root;
        Node parent = null;
        if (root == null) root = new Node(key);
        else {
            while (current != null) {
                parent = current;
                if (current.key.compareTo(key)==0) throw new DuplicateElementException("Adding duplicate elements are not allowed");
                current = (current.key.compareTo(key) > 0) ? current.left : current.right;
            }
            if (parent.key.compareTo(key) > 0) {
                parent.left = new Node(key);
                parent.left.parent = parent;
            } else {
                parent.right = new Node(key);
                parent.right.parent = parent;
            }
        }
        return current;
    }

    public boolean remove(Key key) {
        Node node = find(key);
        if (node == null) return false;
        else {
            if(node.left!=null&&node.right!=null){
                Node next = null;
                while (node.right != null) {
                    next = node.right;
                    while (next.left != null) next = next.left;
                    Key temp = next.key;
                    next.key = node.key;
                    node.key = temp;
                    node = next;
                }
                node.parent.right = null;
            }
            else{
                Node next;
                if(node.left!=null) next = node.left;
                else next = node.right;
                Node parent = node.parent;
                if (parent.left == node) parent.left = next;
                else parent.right = next;
            }
            return true;
        }
    }


    public int size() {
        return size(root, 0);
    }

    public int size(Node node, int x) {
        if (node == null) return x - 1;
        int y, z;
        return ((y = size(node.left, x + 1)) > (z = size(node.right, x + 1))) ? y : z;
    }

    public Node root() {
        return root;
    }

    public BST subtree(Key key) {
        BST tree = new BST();
        tree.root = find(key);
        return tree;
    }

    public String path(Key key1, Key key2) {
        Node current = root;
        if (key1.compareTo(key2) > 0) {
            Key x = key1;
            key1 = key2;
            key2 = x;
        }
        LinkedList<Key> list = new LinkedList<>();
        if (find(key1) == null || find(key2) == null) return "There is no path for selected keys";
        else {
            boolean isDone = false;
            while (isDone == false) {
                isDone = true;
                while (current.key.compareTo(key1) > 0 && current.key.compareTo(key2) > 0) {
                    current = current.left;
                    isDone = false;
                }
                while (current.key.compareTo(key1) < 0 && current.key.compareTo(key2) < 0) {
                    current = current.right;
                    isDone = false;
                }
            }
            list.add(current.key);
            Node currentLeft = current;
            while (currentLeft.key.compareTo(key1) != 0) {
                currentLeft = (currentLeft.key.compareTo(key1) > 0) ? currentLeft.left : currentLeft.right;
                list.addFirst(currentLeft.key);
            }
            while (current.key.compareTo(key2) != 0) {
                current = (current.key.compareTo(key2) > 0) ? current.left : current.right;
                list.addLast(current.key);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Key element : list) {
            stringBuilder.append(element + " ");
        }
        return stringBuilder.toString();
    }

    public Key min() {
        Node node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    public Key max() {
        Node node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    public Node find(Key key) {
        Node current = root;
        while (current != null && current.key.compareTo(key) != 0) {
            current = (current.key.compareTo(key) > 0) ? current.left : current.right;
        }
        return current;
    }

    @Override
    public String toString() {
        if (root == null) return "The tree does not exist.";
        return root.toString();
    }

}

class DuplicateElementException extends Throwable {
    public DuplicateElementException(String message) {
        super(message);
    }
}