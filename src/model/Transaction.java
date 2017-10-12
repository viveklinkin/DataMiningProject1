package model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Transaction {
	private int id;
	private Set<Integer> items;
	
	public boolean contains(int i){
		return items.contains(i);
	}
	
	public Transaction(int id){
		this.id = id;
		this.items = new LinkedHashSet<>();
	}
	
	public void addItem(int it){
		items.add(it);
	}
	
	//returns CSV
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(id + ",");
		for(int x : items){
			sb.append(x + ",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
}
