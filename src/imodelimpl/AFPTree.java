/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imodelimpl;

import imodel.Node;
import imodel.Tree;
import java.util.List;

/**
 *
 * @author vivek
 * @param <N>
 * @param <I>
 */
public abstract class AFPTree<N extends Node, I> implements Tree<N, I> {

    protected N root;
    protected boolean empty = true;
    

    @Override
    public void addTransaction(List<I> items) {
        empty = false;
        if (items.isEmpty()) {
            return;
        }
        N tempNode = null;
        I temp = items.get(0);
        if (this.root.getChild(temp) == null) {
            root.createChild(temp);
        }
        tempNode = (N) root.getChild(temp);

        items.remove(0);
        tempNode.propagate(items, 0);
    }

    @Override
    public N getRoot() {
        return root;
    }

    public void setRoot(N root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
        return empty;
    }
    
    public abstract void addNodeToLinkedList(I t, N n);
    public abstract void project(I i);
}
