package model;

/**
 * @author vaidy083
 */
import imodel.Node;
import imodelimpl.AFPNode;
import imodelimpl.AFPTree;

public class FPNode extends AFPNode<Integer> {

    //super Constructor
    public FPNode(AFPTree context, Node parent, int iid) {
        super();
        setParent(parent);
        setItemId(iid);
        setSupportCount(0);
        setContext(context);
    }

    @Override
    public Node defaultNode(Integer t) {
        return new FPNode(getContext(), this, t);
    }

    @Override
    public Node defaultRootNode() {
        return new FPNode(getContext(), null, -1);
    }
    
    @Override
    public void createChild(Integer t){
        Node newNode = defaultNode(t);
        children.put(t, newNode);
        getContext().addNodeToLinkedList(t, newNode);
    }
    @Override
    public boolean isTopNode() {
        return ((Integer)parent.getItemId() == -1);
    }

    @Override
    public Node copy() {
        Node cop = new FPNode(this.context, this.parent, this.itemId);
        cop.setSupportCount(supportCount);
        cop.setChildren(getChildren());
        return cop;
    }
}
