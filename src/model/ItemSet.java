/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author vivek
 */
public class ItemSet {
    private int support;
    private List<Integer> items;
    
    public ItemSet(){
        
    }
    public void addSupport(int supp){
        support += supp;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int curr : items){
            sb.append(curr + ",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }
}
