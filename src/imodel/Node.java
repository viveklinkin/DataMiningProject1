/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imodel;

import java.util.List;
import java.util.Map;

/**
 *
 * @author vivek
 * @param <I>
 */
public interface Node<I> {

    public void addSupport(int a);

    public int getSupportCount();

    public Node getParent();

    public I getItemId();

    public void propagate(List<I> l, int a);

    public void createChild(I t);
    
    public Map<I, Node> getChildren();

    public boolean isTopNode();

    public boolean isLeafNode();

    public Node getChild(I i);
    
    public abstract Node defaultNode(I t);

    public abstract Node defaultRootNode();
}
