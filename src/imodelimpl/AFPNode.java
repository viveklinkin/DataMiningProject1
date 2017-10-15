/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imodelimpl;

import java.util.HashMap;
import java.util.Map;
import imodel.Node;
import java.util.List;

/**
 *
 * @author vivek
 */
public abstract class AFPNode<I> implements Node<I> {

    protected I itemId;
    protected Map<I, Node> children;
    protected AFPNode parent;
    protected int supportCount;
    protected AFPTree context;

    public AFPNode() {
        children = new HashMap<>();
    }

    @Override
    public void propagate(List<I> x, int a) {
        this.supportCount++;
        if (x.isEmpty()) {
            return;
        }
        Node currentNode = null;
        if (!children.containsKey(x.get(0))) {
            createChild(x.get(0));
        }
        currentNode = children.get(x.get(0));
        x.remove(0);
        currentNode.propagate(x, 0);
    }

    @Override
    public void createChild(I t) {
        Node newNode = defaultNode(t);
        children.put(t, newNode);
    }

    @Override
    public void addSupport(int a) {
        supportCount += a;
    }

    @Override
    public boolean isLeafNode() {
        return (this.children.isEmpty());
    }

    @Override
    public I getItemId() {
        return itemId;
    }

    public void setItemId(I itemId) {
        this.itemId = itemId;
    }

    @Override
    public int getSupportCount() {
        return supportCount;
    }
    public void setSupportCount(int supportCount) {
        this.supportCount = supportCount;
    }
    
    @Override
    public Node getChild(I i1) {
        return children.get(i1);
    }

    @Override
    public Map<I, Node> getChildren() {
        return children;
    }

    public void setContext(AFPTree tree) {
        this.context = tree;
    }

    public AFPTree getContext() {
        return this.context;
    }

    @Override
    public Node getParent() {
        return parent;
    }

    public void setParent(AFPNode parent) {
        this.parent = parent;
    }
}
