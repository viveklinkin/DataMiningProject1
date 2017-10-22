package main.model;

import java.util.List;

import main.imodel.AFPNode;
import main.imodel.Node;


public class AssociationNode extends AFPNode<Integer> {

	public AssociationNode(Integer itemId, AssociationNode parent) {
		this.itemId = itemId;
		supportCount = 0;
		setParent(parent);
	}
	@Override
	public boolean isTopNode() {
		// TODO: Implement later, when it is not a 24-h countdown to submission
		return false;
	}
	

	@Override
	public Node copy() {
		throw new RuntimeException("Copy not defined in AssiciationNode");
	}

	@Override
	public Node defaultNode(Integer t) {
		return new AssociationNode(t, this);
	}

	@Override
	public Node defaultRootNode() {
		return null;
	}
	
	@Override
    public void propagate(List<Integer> x, int a) {
        supportCount = (supportCount>a)? supportCount:a;
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
	
	public int query(List<Integer> x){
		if (x.isEmpty()) {
            return supportCount;
        }
        int c = x.get(0);
		x.remove(0);
        return ((AssociationNode)children.get(x)).query(x);
	}
	
}
