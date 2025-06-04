package com.example.finalproject.Queue;

public interface QueueInterface<T> {

    public void enqueue(T data);
    public T dequeue();
    public T getFront();
    public boolean isEmpty();
    public void clear();

}
