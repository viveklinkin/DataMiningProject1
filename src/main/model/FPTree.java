/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import main.imodel.AFPTree;
import main.imodel.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @author vivek
 */
public class FPTree extends AFPTree<Node, Integer> {

	protected Map<Integer, List<Node>> linkedList;

	private Map<Integer, Integer> itemFrequency;

	private Map<Integer, Integer> lexOrder;

	public FPTree() {
		root = new FPNode(this, null, -1);
		linkedList = new HashMap<>();
		lexOrder = new HashMap<>();
	}

	@Override
	public void addNodeToLinkedList(Integer iid, final Node newNode) {
		if (linkedList.containsKey(iid)) {
			linkedList.get(iid).add(newNode);
			return;
		}
		linkedList.put(iid, new ArrayList<Node>() {
			{
				add(newNode);
			}
		});
	}

	public Map<Integer, List<Node>> getLinkedLists() {
		return linkedList;
	}

	public List<Node> getLinkedList(int i) {
		return linkedList.get(i);
	}

	public void setLinkedList(Map<Integer, List<Node>> linkedList) {
		this.linkedList = linkedList;
	}

	public Map<Integer, Integer> getItemFrequency() {
		return itemFrequency;
	}

	public int getFrequency(Integer i) {
		return itemFrequency.get(i);
	}

	public void setItemFrequency(Map<Integer, Integer> itemFrequency) {
		this.itemFrequency = itemFrequency;
	}

	@Override
	public void project(Integer item) {
		for (Entry<Integer, List<Node>> currentEntry : linkedList.entrySet()) {
			if ((int) currentEntry.getKey() != (int) item) {
				currentEntry.getValue().clear();
			}
		}
		for (Node currentNode : getLinkedList(item)) {
			while (!currentNode.isTopNode()) {
				currentNode.setParent(currentNode.getParent().copy());
				currentNode = currentNode.getParent();
				currentNode.setSupportCount(0);
				this.addNodeToLinkedList((Integer) currentNode.getItemId(),
						currentNode);
			}
		}
		for (Node currentNode : getLinkedList(item)) {
			int supp = currentNode.getSupportCount();
			while (!currentNode.isTopNode()) {
				currentNode = currentNode.getParent();
				currentNode.addSupport(supp);
			}
		}
		updateItemFrequency();
	}

	public void applyMeta(Map<Integer, List<Node>> metaData) {
		for (Entry<Integer, List<Node>> entry : metaData.entrySet()) {
			getLinkedList(entry.getKey()).clear();
			for (Node currentNode : entry.getValue()) {
				this.addNodeToLinkedList(entry.getKey(), currentNode);
			}
		}
		updateItemFrequency();
	}

	public Map<Integer, List<Node>> getMeta() {
		Map<Integer, List<Node>> metaData = new HashMap<>();
		for (Entry<Integer, List<Node>> currentEntry : linkedList.entrySet()) {
			List<Node> curr = new ArrayList<>();
			for (Node currentNode : currentEntry.getValue()) {
				curr.add(currentNode);
			}
			metaData.put(currentEntry.getKey(), curr);
		}
		return metaData;
	}

	private void updateItemFrequency() {
		empty = true;
		for (Entry<Integer, List<Node>> entry : linkedList.entrySet()) {
			int sum = 0;
			for (Node currentNode : entry.getValue()) {
				sum += currentNode.getSupportCount();
			}
			itemFrequency.put(entry.getKey(), sum);
			if (sum != 0) {
				empty = false;
			}
		}
	}

	public int getLexIndexOf(Integer item) {
		return lexOrder.get(item);
	}

	public Map<Integer, Integer> getLexOrder() {
		return lexOrder;
	}

	public void setLexOrder(List<Integer> lexOrder) {
		for (int i = 0; i < lexOrder.size(); i++) {
			this.lexOrder.put(lexOrder.get(i), i);
		}
	}

}
