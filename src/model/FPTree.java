/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import imodelimpl.AFPTree;
import imodel.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author vivek
 */
public class FPTree extends AFPTree<Node, Integer> {

    protected Map<Integer, List<Node>> linkedList;
        
    private Map<Integer, Integer> itemFrequency;

    public FPTree() {
        root = new FPNode(this, null, -1);
        linkedList = new HashMap<>();
    }

    @Override
    public void addNodeToLinkedList(Integer iid, Node newNode) {
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

    public Map<Integer, List<Node>> getLinkedLists() {
        return linkedList;
    }

    public List<Node> getLinkedList(int i) {
        return linkedList.get(i);
    }

    public void setLinkedList(Map<Integer, List<Node>> linkedList) {
        this.linkedList = linkedList;
    }

    public Map<Integer, Integer> getItemFrequency() {
        return itemFrequency;
    }

    public void setItemFrequency(Map<Integer, Integer> itemFrequency) {
        this.itemFrequency = itemFrequency;
    }

}
