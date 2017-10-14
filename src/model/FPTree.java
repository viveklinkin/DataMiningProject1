/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vivek
 */
public class FPTree {

    private Node root;
    private Map<Integer, List<Node>> linkedList;

    public FPTree() {
        root = new Node(this, null, -1);
        linkedList = new HashMap<>();
    }

    public Map<Integer, List<Node>> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(Map<Integer, List<Node>> linkedList) {
        this.linkedList = linkedList;
    }

    public void addTransaction(List<Integer> items) {
        if (items.size() == 0) {
            return;
        }
        Node tempNode = null;
        int temp = items.get(0);
        if (this.root.getChild(temp) == null) {
            tempNode = this.root.createChild(temp);
        } else {
            tempNode = this.root.getChild(temp);
        }
        items.remove(0);
        tempNode.propagate(items);
    }

    public void addNode(Integer iid, Node newNode) {
        if (linkedList.containsKey(iid)) {
            linkedList.get(iid).add(newNode);
            return;
        }
        linkedList.put(iid, new ArrayList<Node>() {
            {
                add(newNode);
            }
        });
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }
    
}
