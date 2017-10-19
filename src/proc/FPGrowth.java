/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc;

import imodel.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import model.FPTree;
import model.ItemSet;

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
        Map<Integer, List<Node>> meta = tree.getMeta();
                
        for (Integer item : tree.getLexOrder().keySet()) {
            if (tree.getFrequency(item) >= minsup) {
                System.out.println(item);
                tree.project(item);
                //callstack.add(item);
                List<ItemSet> frequentSets = performFPGrowth(item);
//                for(ItemSet is : frequentSets){
//                    is.add(item);
//                }
                res.put(item, frequentSets);
                tree.applyMeta(meta);
                //callstack.clear();
            }
        }
        return res;
    }

    private List<ItemSet> performFPGrowth(Integer prefix) {
        List<ItemSet> res = new ArrayList<>();
        //System.out.println("CALLSTACK:" + callstack);
        if (tree.isEmpty()) {
            System.out.println(prefix);
            return res;
        }
        Map<Integer, List<Node>> meta = tree.getMeta();
        for (Integer item : tree.getLexOrder().keySet()) {
            if (tree.getLexIndexOf(item) >= tree.getLexIndexOf(prefix)) {
                break;
            }
            if (tree.getFrequency(item) >= minsup) {
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
