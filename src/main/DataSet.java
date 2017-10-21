package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DataSet {

    private Map<Integer, Transaction> transactions;
    private Map<Integer, Integer> itemFrequency;
    private List<Integer> lexOrder;

    public DataSet() {
        itemFrequency = new HashMap<>();
        transactions = new HashMap<>();
    }

    public void addTransaction(int tid, List<Integer> iidList) {
        if (transactions.containsKey(tid)) {
            throw new RuntimeException("There is a duplicate transaction..");
        }
        //LinkedHashSet<Integer> iidSet = new LinkedHashSet<>(iidList);
        Transaction t = new Transaction(tid, iidList);
        transactions.put(tid, t);
    }

    /* Overridden for some other time
    public void addTransaction(int tid, LinkedHashSet<Integer> iidSet){
        if(transactions.containsKey(tid)){
            throw new RuntimeException("There is a duplicate transaction..");
        }
        Transaction t = new Transaction(tid, iidSet);
        transactions.put(tid, t);
    }
     */
    public void setItemFrequency(Map<Integer, Integer> itemFrequency) {
        this.itemFrequency = itemFrequency;
    }

    public Map<Integer, Integer> getItemFrequency() {
        return itemFrequency;
    }

    public int getFrequencyOf(Integer i1) {
        return itemFrequency.get(i1);
    }

    public Transaction getTransactionById(Integer i1) {
        if (!transactions.containsKey(i1)) {
            throw new RuntimeException("The given tid " + i1 + " is not found in the table");
        }
        return transactions.get(i1);
    }
    
    public List<Transaction> getTransactions(){
        List<Transaction> tl = new ArrayList<>();
        for(Entry<Integer, Transaction> currentEntry: transactions.entrySet()){
            tl.add(currentEntry.getValue());
        }
        return tl;
    }

    public void setLexOrder(List<Integer> lexOrder) {
        this.lexOrder = lexOrder;
    }

    public List<Integer> getLexOrder() {
        return lexOrder;
    }
}
