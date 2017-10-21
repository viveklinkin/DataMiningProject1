/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import main.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import main.FPTree;
import main.ItemSet;

/**
 *
 * @author vivek
 */
public class FPGrowth {

    private FPTree tree;
    private int minsup;

    //private static final List<Integer> callstack = new ArrayList<>();

    public FPGrowth(FPTree tree, int minsup) {
        this.tree = tree;
        this.minsup = minsup;
    }

    public Map<Integer, List<ItemSet>> performFPGrowth() {
        Map<Integer, List<ItemSet>> res = new HashMap<>();
        for (Integer item : tree.getLexOrder().keySet()) {
            if(item.intValue() == 0 ) {
                System.out.println(tree.getFrequency(item) + "I am here");
            }
            if (tree.getFrequency(item) >= minsup) {
                Map<Integer, List<Node>> meta = tree.getMeta();
                System.out.println(item);
                tree.project(item);
                //callstack.add(item);
                List<ItemSet> frequentSets = performFPGrowth(item);
                for(ItemSet is : frequentSets){
                    is.add(item);
                }
                res.put(item, frequentSets);
                tree.applyMeta(meta);
                //callstack.clear();
            }
        }
        return res;
    }

    private static boolean flag;
    private List<ItemSet> performFPGrowth(Integer prefix) {
        List<ItemSet> res = new ArrayList<>();
        //System.out.println("CALLSTACK:" + callstack);
        if (tree.isEmpty()) {
            System.out.println(prefix);
            return res;
        }
        for (Integer item : tree.getLexOrder().keySet()) {
            if (tree.getLexIndexOf(item) >= tree.getLexIndexOf(prefix)) {
                continue;
            }
            if (tree.getFrequency(item) >= minsup) {
//                if(item == 14 && prefix == 1271){
//                    System.out.println("CHECK!s");
//                    System.exit(0);
//                }
                Map<Integer, Integer> prevFreq = new HashMap<>();
                for(Entry<Integer, Integer> ee : tree.getItemFrequency().entrySet()){
                    prevFreq.put((int) ee.getKey(), (int)ee.getValue());
                }
                Map<Integer, List<Node>> meta = tree.getMeta();
                ItemSet is = new ItemSet(new HashSet<Integer>() {
                    {
                        add(item);
                    }
                }, tree.getFrequency(item));
                tree.project(item);
                //callstack.add(item);
                List<ItemSet> frequentSets = performFPGrowth(item);
                for (ItemSet currentItemSet : frequentSets) {
                    currentItemSet.add(item);
                }
                //callstack.remove(callstack.size() - 1);
                frequentSets.add(is);
                res.addAll(frequentSets);
                tree.applyMeta(meta);
            }
        }
        return res;
    }
}
