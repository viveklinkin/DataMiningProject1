package main.model;

import java.util.HashSet;
import java.util.Set;

public class AssociationRule {
	private Set<Integer> lhs, rhs;
	private int support;
	private float confidence;
	
	public Set<Integer> getLhs(){
		return lhs;
	}
	
	public int getSupport(){
		return support;
	}
	
	public float getConfidence(){
		return confidence;
	}
	
	public Set<Integer> getRhs(){
		return rhs;
	}
	
	public AssociationRule(){
		lhs = new HashSet<>();
		rhs = new HashSet<>();
		support = 0;
		confidence = 0;
	}
	
	public AssociationRule(Set a, Set b, int x, float y){
		lhs = a;
		rhs = b;
		if(rhs == null) rhs = new HashSet<>();
		support = x;
		confidence = y;
	}
	
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(lhs);
		sb.append("|");
		sb.append(rhs);
		sb.append("|");
		sb.append(support);
		sb.append("|");
		sb.append(confidence);
		return sb.toString();
	}
}
