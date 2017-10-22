package main.model;

import java.util.List;

import main.imodel.*;

public class AssociationTree extends AFPTree<AssociationNode, Integer> {

	@Override
	public void addNodeToLinkedList(Integer t, AssociationNode n) {
		//Do nothing
	}

	@Override
	public void project(Integer i) {
		//Do nothing
	}
	
	public int query(List<Integer> list){
		if(list.isEmpty()){
			return 0;
		}
		int c = list.get(0);
		list.remove(0);
		return ((AssociationNode)root.getChild(c)).query(list);
	}
	
}
