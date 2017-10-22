package main.model;

/**
 * @author vaidy083
 */
import main.imodel.AFPNode;
import main.imodel.AFPTree;
import main.imodel.Node;

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
    public Node copy() {
        Node cop = new FPNode(this.context, this.parent, this.itemId);
        cop.setSupportCount(supportCount);
        cop.setChildren(getChildren());
        return cop;
    }
}
