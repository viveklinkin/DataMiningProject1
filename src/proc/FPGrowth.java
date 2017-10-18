/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc;

import java.util.ArrayList;
import java.util.List;
import imodel.Node;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import model.FPTree;
import model.ItemSet;

/**
 *
 * @author vivek
 */
public class FPGrowth implements Comparator<Integer> {

    private FPTree tree;
    private int minsup;
    
    public FPGrowth(FPTree tree, int minsup){
        this.tree = tree;
        this.minsup = minsup;
    }
    public List<ItemSet> getFrequentItemSets() {
        List<ItemSet> result = new ArrayList<>();
        for(List<Node> currentNode: tree.getLinkedLists().values()){
            List<ItemSet> uproot = uprootAll(currentNode);
            uproot = filter((Integer)currentNode.get(0).getItemId(), uproot, minsup);
            
        }
        return result;
    }

    private List<ItemSet> uprootAll(List<Node> entry) {
        List<ItemSet> itemSet = new ArrayList<>();

        List<Node> list = entry;
        for (Node nodeTraverse : list) {
            Node currentNode = nodeTraverse;
            int supportCount = nodeTraverse.getSupportCount();
            Set<Integer> currentItemSet = new LinkedHashSet<>();
            while (!currentNode.isTopNode()) {
                currentNode = currentNode.getParent();
                currentItemSet.add((Integer) currentNode.getItemId());
                ItemSet tempItemSet = new ItemSet(currentItemSet, supportCount);
                itemSet.add(tempItemSet);
            }

        }

        return itemSet;
    }

//    private ItemSet contains(List<ItemSet> set, ItemSet s) {
//        for (ItemSet x : set) {
//            if (x.equals(s)) {
//                return x;
//            }
//        }
//        return null;
//    }
    
    private List<ItemSet> filter(int suffix, List<ItemSet> isl, int minsup){
        List<ItemSet> finalList;
        List<Integer> getAllUniqueItems = getAllUniqueItems(isl);
        
        
        return isl;
    }
    private List<Integer> getAllUniqueItems(List<ItemSet> list){
        Set<Integer> y = new HashSet<>();
        for(ItemSet is: list){
            for(int m : is.getItems()){
                y.add(m);
            }
        }
        List<Integer> x = new ArrayList<>(y);
        Collections.sort(x, this);
        return x;
    }

    @Override
    public int compare(Integer t, Integer t1) {
        return (this.tree.getItemFrequency().get(t) < this.tree.getItemFrequency().get(t1)) ? -1 : 1;
    }
    

}
