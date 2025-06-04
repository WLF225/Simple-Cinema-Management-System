package com.example.finalproject.Queue;

public class Queue<T> implements QueueInterface<T> {

    private NodeNotComp<T> front;
    private NodeNotComp<T> back;

    @Override
    public void enqueue(T data) {
        if (isEmpty()){
            front = new NodeNotComp<>(data);
            back = front;
        }else{
            back.setNext(new NodeNotComp<>(data));
            back = back.getNext();
        }
    }

    @Override
    public T dequeue() {
        if (isEmpty())
            return null;
        T data = front.getData();
        front = front.getNext();
        return data;
    }

    @Override
    public T getFront() {
        if (isEmpty())
            return null;
        return front.getData();
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public void clear() {
        front = null;
        back = null;
    }
}
