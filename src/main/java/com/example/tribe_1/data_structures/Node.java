package com.example.tribe_1.data_structures;

public class Node<T> {

    T data;
    Node next;

    public Node(T data) {
        this.data = data;
        next = null;
    }
}
