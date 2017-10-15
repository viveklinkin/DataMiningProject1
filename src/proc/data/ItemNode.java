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
import imodelimpl.AFPNode;


/**
 *
 * @author vivek
 */
public class ItemNode extends AFPNode<Integer> {

    private int value;
    private int support;
    private ItemNode parent;
    private Map<Integer, ItemNode> children;

    public ItemNode(int value, int support) {
        this.value = value;
        this.support = support;
        children = new HashMap<>();
    }
    public void addSupport(int s){
        support+=s;
    }
    
//    public void addNode(int iid, int support){
//        if(children.containsKey(iid)){
//            children.get(iid).addSupport(support);
//        }
//        else{
//            children.put(iid, new ItemNode(iid));
//        }
//    }
    
    public void propagate(List<Integer> list, int support){
        
    }

    @Override
    public boolean isTopNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node defaultNode(Integer t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Node defaultRootNode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
