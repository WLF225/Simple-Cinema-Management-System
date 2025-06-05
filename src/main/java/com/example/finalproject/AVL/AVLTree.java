package com.example.finalproject.AVL;

import com.example.finalproject.LinkList.LinkList;
import com.example.finalproject.Queue.Queue;

public class AVLTree<T extends Comparable<T>> {

    private TNode<T> root;

    public String inOrderTraverse() {
        if (root == null)
            return "Null";
        return inOrderTraverse(root, "");
    }

    private String inOrderTraverse(TNode<T> node, String string) {
        if (node != null) {
            if (node.isLeaf())
                string += node + "\t";
            else {
                string = inOrderTraverse(node.getLeft(), string);
                string += node + "\t";
                string = inOrderTraverse(node.getRight(), string);
            }
        }
        return string;
    }

    public String levelTraverse() {
        String string = "";
        Queue<TNode<T>> queue = new Queue<>();

        if (root == null)
            return string;

        queue.enqueue(root);

        while (!queue.isEmpty()) {
            TNode<T> node = queue.dequeue();

            string += node + "\t";

            if (node.hasLeft())
                queue.enqueue(node.getLeft());
            if (node.hasRight())
                queue.enqueue(node.getRight());
        }


        return string;
    }

    public LinkList<T> toLinkList(){
        return toLinkList(root,new LinkList());
    }

    private LinkList<T> toLinkList(TNode<T> node, LinkList<T> list) {
        if (node != null) {
            if (node.isLeaf())
                list.insertFirst(node.getData());
            else {
                toLinkList(node.getLeft(), list);
                list.insertFirst(node.getData());
                toLinkList(node.getRight(), list);
            }
        }
        return list;
    }

    public int size() {
        if (root == null)
            return 0;
        return size(root, 0);
    }

    private int size(TNode<T> node, int n) {
        if (node != null) {
            if (node.isLeaf())
                n++;
            else {
                n = size(node.getLeft(), n);
                n++;
                n = size(node.getRight(), n);
            }
        }
        return n;
    }

    public int countLeafs() {
        if (root == null)
            return 0;
        return countLeafs(root, 0);
    }

    private int countLeafs(TNode<T> node, int n) {
        if (node != null) {
            if (node.isLeaf())
                n++;
            else {
                n = countLeafs(node.getLeft(), n);
                n = countLeafs(node.getRight(), n);
            }
        }
        return n;
    }


    public int countParents() {
        if (root == null)
            return 0;
        return countParents(root, 0);
    }

    private int countParents(TNode<T> node, int n) {
        if (node != null) {
            if (!node.isLeaf()) {
                n = countParents(node.getLeft(), n);
                n++;
                n = countParents(node.getRight(), n);
            }
        }
        return n;
    }

    public boolean isFull() {
        if (root == null || root.isLeaf()) {
            return true;
        }
        return isFull(root);
    }

    private boolean isFull(TNode<T> node) {
        if (node.isLeaf())
            return true;

        if ((node.hasLeft() && !node.hasRight()) || (!node.hasLeft() && node.hasRight()))
            return false;
        return isFull(node.getLeft()) && isFull(node.getRight());
    }

    public boolean isComplete() {
        if (root == null)
            return true; // Empty tree is considered complete

        Queue<TNode<T>> queue = new Queue<>();
        queue.enqueue(root);

        boolean cond = false;

        while (!queue.isEmpty()) {
            TNode<T> current = queue.dequeue();
            if (current.hasLeft()) {
                if (cond)
                    return false;
                queue.enqueue(current.getLeft());
            } else {
                cond = true;
            }
            if (current.hasRight()) {
                if (cond)
                    return false;
                queue.enqueue(current.getRight());
            } else {
                cond = true;
            }
        }
        return true;
    }

    private int height(TNode<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    private int heightDiff(TNode<T> node) {
        return height(node.getLeft()) - height(node.getRight());
    }

    private TNode<T> rotateRight(TNode<T> node) {
        TNode<T> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        return left;
    }

    private TNode<T> rotateLeft(TNode<T> node) {
        TNode<T> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        return right;
    }

    private TNode<T> rebalance(TNode<T> node) {
        int balance = heightDiff(node);
        if (balance > 1) {
            if (heightDiff(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        } else if (balance < -1) {
            if (heightDiff(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    public void insert(T data) {
        if (root == null) {
            root = new TNode<>(data);
        } else {
            root = insert(data, root);
        }
    }
    private TNode<T> insert(T data, TNode<T> node) {
        if (node == null) {
            return new TNode<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            node.setLeft(insert(data, node.getLeft()));
        } else {
            node.setRight(insert(data, node.getRight()));
        }
        node = rebalance(node);
        return node;
    }

    public void clear() {
        root = null;
    }

   public T find(T data) {
        return find(data,root);
   }

   public T find(T data, TNode<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getData()) < 0) {
            return find(data, node.getLeft());
        }else if (data.compareTo(node.getData()) > 0) {
            return find(data, node.getRight());
        }else {
            return node.getData();
        }
   }

    public T delete(T data) {
        if(find(data) == null)
            return null;

        root = delete(root,data);
        return data;
    }

    private TNode<T> delete(TNode<T> node, T data) {
        if (node == null)
            return node;

        int cmp = data.compareTo(node.getData());

        // Recursively delete from left/right subtree
        if (cmp < 0) {
            node.setLeft(delete(node.getLeft(), data));
        } else if (cmp > 0) {
            node.setRight(delete(node.getRight(), data));
        } else {
            // Case 1: No children (leaf node)
            if (!node.hasLeft() && !node.hasRight()) {
                return null;
            }
            // Case 2: One child
            else if (!node.hasLeft()) {
                return node.getRight();
            } else if (!node.hasRight()) {
                return node.getLeft();
            }
            // Case 3: Two children - Get inorder successor (smallest in right subtree)
            else {
                TNode<T> temp = getMinNode(node.getRight());
                node.setData(temp.getData());
                node.setRight(delete(node.getRight(), temp.getData()));
            }
        }

        // Rebalance this node if needed
        return rebalance(node);
    }

    private TNode<T> getMinNode(TNode<T> node) {
        while (node.hasLeft()) {
            node = node.getLeft();
        }
        return node;
    }

//    public TNode<T>[] findMin(TNode<T> node) {
//        return findMin(node,node);
//    }
//
//    private TNode<T>[] findMin(TNode<T> node, TNode<T> parent) {
//
//        if (node.hasLeft())
//            return findMin(node.getRight(),node);
//        else {
//            TNode<T>[] array = new TNode[2];
//            array[0] = node;
//            array[1] = parent;
//            return array;
//        }
//    }

//    private TNode<T> getSuccessor(TNode<T> node) {
//        TNode<T> parentOfSuccessor = node;
//        TNode<T> successor = node;
//        TNode<T> current = node.getRight();
//        while (current != null) {
//            parentOfSuccessor = successor;
//            successor = current;
//            current = current.getLeft();
//        }
//        if (successor != node.getRight()) { // fix successor connections
//            parentOfSuccessor.setLeft(successor.getRight());
//            successor.setRight(node.getRight());
//        }
//        return successor;
//    }
}
