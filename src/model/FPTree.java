/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vivek
 */
public class FPTree {

    private Node root;
    private List<List<Node>> linkedList;

    public FPTree() {
        root = new Node(null);
        linkedList = new ArrayList<>();
    }

    public List<List<Node>> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(List<List<Node>> linkedList) {
        this.linkedList = linkedList;
    }
    
    public void addTransaction(){
        
    }

}
