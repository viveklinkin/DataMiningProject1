/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import model.RawData;

/**
 *
 * @author vivek
 */
public class Parser {
    public static RawData parseFile(String fileName){
        List<String> fileContents = FileOps.readFile(fileName);
        
        RawData raw = new RawData();
        
        for(String currentLine: fileContents){
            String[] parsedVal = currentLine.split(" ");
            int tid = Integer.parseInt(parsedVal[0]);
            int iid = Integer.parseInt(parsedVal[1]);
            raw.addEntry(tid, iid);
        }
        return raw;
    }
}
