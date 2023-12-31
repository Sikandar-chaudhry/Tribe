package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.Stack;

public class CommentStorage {
    private static Stack<Comment> commentStack;

    public CommentStorage() {
        commentStack = new Stack<>(50);
    }
    public void addInStack(Comment comment) {
        commentStack.push(comment);
    }
    public Comment getFromStack(){
        Comment comment = commentStack.pop();
        return comment;
    }
    public boolean isEmpty(){
        return commentStack.isEmpty();
    }

}
