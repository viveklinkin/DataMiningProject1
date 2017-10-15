/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc;

import model.DataSet;
import model.FPTree;
import model.Transaction;

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
    }

    public void addTransaction(Transaction t) {
        tree.addTransaction(t.getItems());
    }

    public FPTree getTree() {
        return this.tree;
    }

}
