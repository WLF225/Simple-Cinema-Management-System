package com.example.finalproject.Queue;

public class NodeNotComp<T> {

    private T data;
    private NodeNotComp<T> next;


    public NodeNotComp(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
    public NodeNotComp<T> getNext() {
        return next;
    }

    public void setNext(NodeNotComp<T> next) {
        this.next = next;
    }


}
