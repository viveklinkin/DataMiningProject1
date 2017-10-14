package model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Transaction {

    // Transaction ID
    private int tid;
    // Set of items in the transaction
    private List<Integer> items;

    public Transaction(int id) {
        this.tid = id;
        this.items = new ArrayList<>();
    }
    
    public Transaction(int id, List<Integer> items){
        this.tid = tid;
        this.items = items;
    }
    
    public List<Integer> getItems(){
        return items;
    }

    public boolean contains(int i) {
        return items.contains(i);
    }

    public void addItem(int it) {
        items.add(it);
    }

    //returns CSV
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tid + ",");
        for (int x : items) {
            sb.append(x + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
