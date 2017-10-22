package main.imodel;

import java.util.HashMap;
import java.util.Map;

import main.imodel.Node;

import java.util.List;

/**
 * Abstract implementation of the node class
 *
 * @author vivek
 */
public abstract class AFPNode<I> implements Node<I> {

    protected I itemId;
    protected Map<I, Node> children;
    protected Node parent;
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
    public boolean isTopNode() {
        return ((Integer)parent.getItemId() == -1);
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

    @Override
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

    @Override
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public void setChildren(Map<I, Node> children) {
        this.children = children;
    }

}
