/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author vivek
 */
public class RawData implements Comparator<Integer> {

    private Map<Integer, List<Integer>> data;
    private Map<Integer, Integer> itemFrequency;
    private List<Integer> lexOrder;

    public RawData() {
        data = new HashMap<>();
        itemFrequency = new HashMap<>();
    }

    public void addEntry(int i1, final int i2) {
        if (data.containsKey(i1)) {
            data.get(i1).add(i2);
        } else {
            data.put(i1, new ArrayList<Integer>() {
                {
                    add(i2);
                }
            });
        }

        if (itemFrequency.containsKey(i2)) {
            itemFrequency.put(i2, itemFrequency.get(i2) + 1);

        } else {
            itemFrequency.put(i2, 1);
        }
    }

    public Map<Integer, Integer> getFrequencyMap() {
        return this.itemFrequency;
    }

    //Convert raw data into transactions. This needs to be done to preserve order.
    public DataSet toSortedTransactionList() {
        hsortByFreqDecreasing();

        DataSet res = new DataSet();
        res.setItemFrequency(itemFrequency);

        for (Entry<Integer, List<Integer>> currentEntry : data.entrySet()) {
            res.addTransaction(currentEntry.getKey(), currentEntry.getValue());
        }
        lexOrder = new ArrayList<>(res.getItemFrequency().keySet());
        Collections.sort(lexOrder, this);
        res.setLexOrder(lexOrder);
        return res;
    }

    //Sorts items in the set by decreasing order of frequency
    private void hsortByFreqDecreasing() {
        for (Entry<Integer, List<Integer>> currentEntry : data.entrySet()) {
            Collections.sort(currentEntry.getValue(), this);
        }
    }

    @Override
    public int compare(Integer t, Integer t1) {
        if((int)this.itemFrequency.get(t) == (int)this.itemFrequency.get(t1)) return 0;
        return (this.itemFrequency.get(t) < this.itemFrequency.get(t1)) ? 1 : -1;
    }
}
