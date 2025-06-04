package com.example.finalproject;


import com.example.finalproject.AVL.AVLTree;
import com.example.finalproject.Hash.Hash;
import com.example.finalproject.LinkList.LinkList;

public class Main {

    public static void main(String[] args) {


        Hash<Integer> hash = new Hash<>();

        hash.insert(5);
        hash.insert(6);
        hash.insert(15);
        hash.insert(21);
        hash.insert(22);
        hash.insert(23);
        hash.insert(24);
        hash.insert(8);
        hash.insert(9);
        hash.insert(11);
        hash.insert(12);
        hash.insert(13);
        hash.insert(14);
        hash.insert(15);
        hash.insert(16);
        hash.insert(17);
        hash.insert(18);
        hash.insert(19);
        hash.insert(20);
        hash.insert(32);
        hash.insert(38);
        hash.insert(42);
        hash.insert(44);
        hash.insert(57);
        hash.insert(58);


//        for (AVLTree<Integer> avlTree:hash.getArray()){
//            System.out.println(avlTree.levelTraverse());
//        }

        hash.insert(59);

        System.out.println(hash.find(8));
        System.out.println(hash.find(7));


        System.out.println("============================");

        for (AVLTree<Integer> avlTree:hash.getArray()){
            System.out.println(avlTree.levelTraverse());
        }

    }
}