/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import main.imodel.Node;
import main.model.FPTree;
import main.model.ItemSet;

/**
 * 
 * @author vivek
 */
public class FPGrowth {

	private FPTree tree;
	private int minsup;
	private int size;

	public FPGrowth(FPTree tree, int minsup, int size) {
		this.tree = tree;
		this.minsup = minsup;
		this.size = size;
	}

	public Map<Set<Integer>, Integer> performFPGrowth() {
		Map<Set<Integer>, Integer> res = new HashMap<>();
		int per = 0, pre = -1;
		for (final Integer item : tree.getLexOrder().keySet()) {
			if (tree.getFrequency(item) >= minsup) {
				Map<Integer, List<Node>> meta = tree.getMeta();
				per = (item*100) / tree.getLexOrder().size();
				if (per > pre) {
					//System.out.print("\n" + per + "%");
					pre = per;
				}
				tree.project(item);
				List<ItemSet> frequentSets = performFPGrowth(item);
				for (ItemSet is : frequentSets) {
					is.add(item);
				}
				tree.applyMeta(meta);
				frequentSets.add(new ItemSet(new HashSet<Integer>(){{
					add(item);
				}}, tree.getFrequency(item)));
                for(ItemSet is : frequentSets){
                	res.put(is.getItems(), is.getSupport());
                }
			}
		}
		return res;
	}

	private static long progress = 0;

	private List<ItemSet> performFPGrowth(final Integer prefix) {
		List<ItemSet> res = new ArrayList<>();
		if (tree.isEmpty()) {
			return res;
		}
		for (final Integer item : tree.getLexOrder().keySet()) {
			if (tree.getLexIndexOf(item) >= tree.getLexIndexOf(prefix)) {
				continue;
			}
			//if (progress++ % 1000 == 0)System.out.print(".");
			if (tree.getFrequency(item) >= minsup) {
				Map<Integer, Integer> prevFreq = new HashMap<>();
				for (Entry<Integer, Integer> ee : tree.getItemFrequency()
						.entrySet()) {
					prevFreq.put((int) ee.getKey(), (int) ee.getValue());
				}
				Map<Integer, List<Node>> meta = tree.getMeta();
				ItemSet is = new ItemSet(new HashSet<Integer>() {
					{
						add(item);
					}
				}, tree.getFrequency(item));
				tree.project(item);
				List<ItemSet> frequentSets = performFPGrowth(item);
				for (ItemSet currentItemSet : frequentSets) {
					currentItemSet.add(item);
				}
				frequentSets.add(is);
				res.addAll(frequentSets);
				tree.applyMeta(meta);
			}
		}
		return res;
	}
}
