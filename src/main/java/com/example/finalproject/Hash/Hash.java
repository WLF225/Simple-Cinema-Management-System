package com.example.finalproject.Hash;

import com.example.finalproject.AVL.AVLTree;
import com.example.finalproject.LinkList.LinkList;

public class Hash<T extends Comparable<T>> {

    private AVLTree<T>[] array;
    private int itemNumber = 0;
    private int size;

    public Hash(){

        array = new AVLTree[5];
        size = 5;

        for(int i = 0; i < 5; i++){
            array[i] = new AVLTree<>();
        }

    }

    public void insert(T data){
        if (itemNumber >= size*5){
            AVLTree<T>[] newArray = array.clone();
            size = getNextPrime(size*2);
            array = new AVLTree[size];
            for(int i = 0; i < size; i++){
                array[i] = new AVLTree<>();
            }
            for (AVLTree<T> avlTree : newArray) {
                LinkList<T> linkList = avlTree.toLinkList();
                for (T temp:linkList){
                    insert(temp);
                }
            }
        }
        array[data.hashCode()%size].insert(data);
        itemNumber++;
    }

    public T find(T data){
        return array[data.hashCode()%size].find(data);
    }

    public T delete(T data){
        return array[data.hashCode()%size].delete(data);
    }

    public int getNextPrime(int n) {

        if (n % 2 == 0) {
            n++;
        }

        while (true) {
            if (isPrime(n)) {
                return n;
            }
            n += 2;
        }
    }

    private boolean isPrime(int num) {

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }


}
