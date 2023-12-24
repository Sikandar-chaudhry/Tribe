package com.example.tribe_1.data_structures;

import java.util.Scanner;

public class Stack<T> {

    private int maxSize;
    private int top;
    private T[] stackArray;

    public Stack(int size){
        maxSize = size;
        stackArray = (T[]) new Object[maxSize];
        top = -1;
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }
    public T pop(){
        if(isEmpty()) {
            System.out.println("Stack is empty.");
            System.exit(1);
        } else {
            T poppedObject = stackArray[top];
            top--;
            return poppedObject;
        }
        return null;
    }
    public void push(T item) {
        if (isFull()) {
            System.out.println("Stack is full. Cannot push " + item);
        } else {
            stackArray[++top] = item;
        }
    }

    public void delete(T item) {
        int index = -1;
        for (int i = 0; i <= top; i++) {
            if (stackArray[i].equals(item)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            for (int i = index; i < top; i++) {
                stackArray[i] = stackArray[i + 1];
            }
            top--;
        } else {
            System.out.println("Item not found in the stack: " + item);
        }
    }
    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }

        System.out.println("Stack elements: ");
        for (int i = 0; i <= top; i++) {
            System.out.println(stackArray[i]);
        }
        System.out.println(); // Add a newline for readability
    }

}
