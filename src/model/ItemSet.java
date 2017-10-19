/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vivek
 */
public class ItemSet {

    private int support;
    private Set<Integer> items;

    public ItemSet(Set<Integer> items, int support) {
        this.items = items;
        this.support = support;
    }
    
    public void add(Integer i){
        items.add(i);
    }

    public void addSupport(int supp) {
        support += supp;
    }

    public int getSupport() {
        return support;
    }

    public Set<Integer> getItems() {
        return items;
    }

    public void setItems(Set<Integer> items) {
        this.items = items;
    }
    
    public boolean contains(int i){
        return items.contains(i);
    }
    public boolean contains(Collection<Integer> i){
        for(int x : i){
            if(!items.contains(x)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int curr : items) {
            sb.append(curr + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ItemSet) {
            ItemSet is = (ItemSet) o;
            if(is.getItems().size() != this.getItems().size())return false;
            List<Integer> 
                    l1 = new ArrayList<>(this.getItems()),
                    l2 = new ArrayList<>(is.getItems());
            
            l2.removeAll(this.getItems());
            l1.removeAll(is.getItems());
            return (l1.isEmpty() && l2.isEmpty());
        }
        return false;
    }
}
