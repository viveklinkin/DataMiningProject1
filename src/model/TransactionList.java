package model;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class TransactionList {

    private Map<Integer, Transaction> transactions;
    private Map<Integer, Integer> itemFrequency;

    public TransactionList() {
        itemFrequency = new HashMap<>();
        transactions = new HashMap<>();
    }
    
    public void addTransaction(int tid, List<Integer> iidList){
        if(transactions.containsKey(tid)){
            throw new RuntimeException("There is a duplicate transaction..");
        }
        LinkedHashSet<Integer> iidSet = new LinkedHashSet<>(iidList);
        Transaction t = new Transaction(tid, iidSet);
        transactions.put(tid, t);
    }
    
    public void addTransaction(int tid, LinkedHashSet<Integer> iidSet){
        if(transactions.containsKey(tid)){
            throw new RuntimeException("There is a duplicate transaction..");
        }
        Transaction t = new Transaction(tid, iidSet);
        transactions.put(tid, t);
    }
    
    public void setItemFrequency(Map<Integer, Integer> itemFrequency) {
        this.itemFrequency = itemFrequency;
    }
    
    public int getFrequencyOf(Integer i1){
        return itemFrequency.get(i1);
    }
    
    public Transaction getTransactionById(Integer i1){
        if(!transactions.containsKey(i1)){
            throw new RuntimeException("The given tid "+ i1 +" is not found in the table");
        }
        return transactions.get(i1);
    }
}
