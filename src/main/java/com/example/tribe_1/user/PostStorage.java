package com.example.tribe_1.user;

import com.example.tribe_1.data_structures.Stack;

public class PostStorage {

    private static Stack<Post> postStack ;

    public PostStorage() {
        postStack = new Stack<>(50);
    }

    public void addInStack(Post post){
        postStack.push(post);
    }
    public Post getFromStack(){
        Post post = postStack.pop();
        return post;
    }
    public boolean isEmpty(){
        return postStack.isEmpty();
    }


}
