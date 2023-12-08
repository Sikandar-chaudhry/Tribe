package com.example.tribe_1.data_structures;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>{

    Node head;
    public LinkedList() {
        head = null;
    }
    public void insertAtEnd(T value){
        Node<T> newNode = new Node<>(value);
        if (head == null){
            head = newNode;
            return;
        }
        else{
            Node<T> current  = head;
            while (current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
    } //End insertAtEnd
    public void insertAtStart(T E){
        Node<T> newNode = new Node<>(E);
        newNode.next = head;
        head = newNode;
    } //End insertAtStart
    public void delete(T E){
        if (head == null){
        return;
        }
        else{
            if(head.data.equals(E)){
                head= head.next;
            } else {
                Node<T> current = head;
                while (current.next != null){
                    if(current.next.data.equals(E)){
                        current.next = current.next.next;
                        return;
                    }
                    current = current.next;
                }
            }
        }
    } //End delete
    public int countTotal(){
        Node<T> current = head;
        int totalNumberOfDataNodes = 0;
        while (current != null){
            totalNumberOfDataNodes++;
            current = current.next;
        }
        return totalNumberOfDataNodes;
    } //End countTotal

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
