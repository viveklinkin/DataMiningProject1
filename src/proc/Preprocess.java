/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proc;

import model.RawData;
import model.DataSet;
import util.Parser;

/**
 *
 * @author vivek
 */
public class Preprocess {

    private String inputFile;

    public Preprocess(String inputFile) {
        this.inputFile = inputFile;
    }

    public DataSet fetch() {
        RawData data = Parser.parseFile(inputFile);
        DataSet tl = data.toSortedTransactionList();
        return tl;
    }

    public static DataSet fetch(String file) {
        RawData data = Parser.parseFile(file);
        DataSet tl = data.toSortedTransactionList();
        return tl;
    }
}
