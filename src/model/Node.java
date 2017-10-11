package model;

/**
 * @author vaidy083
 */

import java.util.Map;

/*
 * 
 * This depicts a node item on the FP tree
 */
public class Node {
	
	//This would describe the name or the item
	private String name;
	
	//TODO: decide on how to present an item in the itemset
	//private String item_name
	
	//The set of child nodes in the tree
	private Map<String, Node> children;
	
	//support for each item in the set
	private Map<Integer, Integer> support;
	
	//The parent node
	private Node parent;
	
	//The number of times it occurs
	private long count;
	
	//Constructor
	public Node(Node parent){
		this.parent = parent;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
}
