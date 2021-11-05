package com.myslyv4uk.binary.tree;

import java.util.function.Consumer;
import java.util.stream.Stream;

public class BinaryTreeSearchAlgorithm<T extends Comparable<T>> implements BinaryTreeSearch<T> {

    private static class Node<T> {

        T element;
        Node<T> right;
        Node<T> left;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> root;
    int size;

    public static <T extends Comparable<T>> BinaryTreeSearchAlgorithm<T> of(T... elements) {
        BinaryTreeSearchAlgorithm<T> binaryTreeSearch = new BinaryTreeSearchAlgorithm<>();
        Stream.of(elements).forEach(binaryTreeSearch::insert);
        return binaryTreeSearch;
    }

    @Override
    public boolean insert(T element) {
        if (root == null) {
            root = new Node<>(element);
            return true;
        } else {
            return insert(root, element);
        }
    }

    private boolean insert(Node<T> current, T element) {
        if (element.compareTo(current.element) < 0) { //go left
            if (current.left == null) {
                current.left = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(current.left, element); // recursive call
            }
        } else if (element.compareTo(current.element) > 0) { //go right
            if (current.right == null) {
                current.right = new Node<>(element);
                size++;
                return true;
            } else {
                return insert(current.right, element); // recursive call
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node<T> current, T element) {
        if (current == null) {
            return false;
        } else if (current.element.compareTo(element) < 0) { // go left
            return contains(current.left, element);
        } else if (current.element.compareTo(element) > 0) { // go right
            return contains(current.right, element);
        } else {
            return true;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root != null? depth(root) - 1 : 0;
    }

    private int depth(Node<T> current) {
        if (current == null) {
            return 0;
        } else {
            return 1 + Math.max(depth(current.left), depth(current.right));
        }
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    public void inOrderTraversal(Node<T> current, Consumer<T> consumer) {
        if(current != null) {
            inOrderTraversal(current.left, consumer);
            consumer.accept(current.element);
            inOrderTraversal(current.right, consumer);
        }
    }


}
