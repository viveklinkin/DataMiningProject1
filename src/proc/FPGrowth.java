/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import imodel.Node;
import model.FPTree;
import model.FPNode;
import model.ItemSet;


/**
 *
 * @author vivek
 */
public class FPGrowth {

    public static List<ItemSet> getFrequentItemSets(FPTree tree, int minsup) {
        List<ItemSet> itemSetList = getAllItemSets(tree);
        itemSetList = eliminateInfrequentItemSets(itemSetList, minsup);
        return itemSetList;
    }

    public static List<ItemSet> getAllItemSets(FPTree tree) {
        List<ItemSet> result = new ArrayList<>();
        for (Entry<Integer, List<Node>> currentEntry : tree.getLinkedLists().entrySet()) {
            result.addAll(generateItemSetForANodeSet(currentEntry.getValue()));
        }
        return result;
    }

    public static List<ItemSet> eliminateInfrequentItemSets(List<ItemSet> itemSets, int minsup) {
        Iterator iter = itemSets.iterator();
        while (iter.hasNext()) {
            if (((ItemSet) iter.next()).getSupport() < minsup) {
                iter.remove();
            }
        }
        return itemSets;
    }

    private static List<ItemSet> generateItemSetForANodeSet(List<Node> entry) {
        List<ItemSet> itemSet = new ArrayList<>();

        List<Node> list = entry;
        for (Node nodeTraverse : list) {
            Node currentNode = nodeTraverse;
            int supportCount = nodeTraverse.getSupportCount();
            List<Integer> currentItemSet = new ArrayList<>();
            currentItemSet.add((Integer)currentNode.getItemId());
            while (!currentNode.isTopNode()) {
                currentNode = currentNode.getParent();
                currentItemSet.add(0, (Integer)currentNode.getItemId());

                ItemSet tempItemSet = new ItemSet(currentItemSet, supportCount);

                ItemSet tempItemSet2 = contains(itemSet, tempItemSet);
                if (tempItemSet2 != null) {
                    tempItemSet2.addSupport(supportCount);
                } else {
                    itemSet.add(tempItemSet);
                }
            }

        }

        return itemSet;
    }

    private static ItemSet contains(List<ItemSet> set, ItemSet s) {
        for (ItemSet x : set) {
            if (x.equals(s)) {
                return x;
            }
        }
        return null;
    }

}
