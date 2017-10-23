/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author vivek
 */
public class Combinatorics {

    public static List<List<Integer>> getCombinatoricsListWithSuffix(List<Integer> items, int suffix) {
        int n = items.size();
        List<List<Integer>> output = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    curr.add(items.get(j));
                }
            }
            output.add(curr);
        }
        return output;
    }
    
    public static List<Set<Integer>> allSplits(final Set<Integer> integerSet){
    	List<Set<Integer>> res = new ArrayList<>();
    	List<Integer> integerList = new ArrayList<>(integerSet);
    	int n = integerList.size();
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> curr = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    curr.add(integerList.get(j));
                }
            }
            if(curr.size() != 0 && curr.size() != n){
            	res.add(new HashSet<>(curr));
            }
        }
        return res;
    }
}
