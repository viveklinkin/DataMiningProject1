/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.helper;

import java.util.Map.Entry;

import main.model.DataSet;
import main.model.FPTree;
import main.model.Transaction;

/**
 *
 * @author vivek
 */
public class FPTreeBuilder {

    private FPTree tree;

    public FPTreeBuilder() {
        tree = new FPTree();
    }

    public void buildFPTreeFromDataSet(DataSet data) {
        for (Transaction currentTransaction : data.getTransactions()) {
            addTransaction(currentTransaction);
        }
        tree.setItemFrequency(data.getItemFrequency());
        tree.setLexOrder(data.getLexOrder());
        
    }

    public void addTransaction(Transaction t) {
        tree.addTransaction(t.getItems());
    }

    public FPTree getTree() {
        return this.tree;
    }

}
