package main.imodel;

import java.util.List;
import java.util.Map;

/**
 * Node interface.
 *
 * @author vivek
 * @param <I>
 */
public interface Node<I> {

    public void addSupport(int a);

    public int getSupportCount();

    public void setSupportCount(int i);

    public Node getParent();

    public void setParent(Node n);

    public I getItemId();

    public void propagate(List<I> l, int a);

    public void createChild(I t);

    public Map<I, Node> getChildren();

    public boolean isTopNode();

    public boolean isLeafNode();

    public Node getChild(I i);

    public Node copy();

    public void setChildren(Map<I, Node> m);

    public Node defaultNode(I t);

    public Node defaultRootNode();
}
