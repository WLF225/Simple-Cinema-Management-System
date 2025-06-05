package com.example.finalproject.LinkList;

import com.example.finalproject.Queue.NodeNotComp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Iterator;

public class LinkList<T> implements Iterable<T>{


    private NodeNotComp<T> head;

    public void insertFirst(T data) {
        NodeNotComp<T> node = new NodeNotComp<>(data);
        node.setNext(head);
        head = node;
    }

    public void addToObservableList(ObservableList<T> list) {
        for (T data: this)
            list.add(data);
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorClass();
    }

    public class IteratorClass implements Iterator<T>{

        NodeNotComp<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
