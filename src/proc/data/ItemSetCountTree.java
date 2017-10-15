/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc.data;

import imodel.Node;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import imodelimpl.AFPTree;

/**
 *
 * @author vivek
 */
public class ItemSetCountTree extends AFPTree<Node, Integer>{

    private Map<Integer,ItemNode> root;
    private int iid;

    ItemSetCountTree(int val) {
        iid = val;
        root = new HashMap<>();
    }
    
    public void add(List<Integer> x, int support){
        
    }

    @Override
    public void addNodeToLinkedList(Integer t, Node n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
