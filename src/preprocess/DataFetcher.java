/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package preprocess;

import java.util.List;
import model.RawData;
import model.TransactionList;
import static util.FileOps.readFile;
import util.Parser;

/**
 *
 * @author vivek
 */
public class DataFetcher {
    String inputFile;
    public DataFetcher(String inputFile){
        this.inputFile = inputFile;
    }
    
    public TransactionList fetch(){
        RawData data = Parser.parseFile(inputFile);
        TransactionList tl = data.toSortedTransactionList();
        return tl;
    }
}
