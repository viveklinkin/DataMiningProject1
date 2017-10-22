/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.helper;

import java.util.List;

import main.model.RawData;

/**
 *
 * @author vivek
 */
public class Parser {
    public static int max = 0;
    public static RawData parseFile(String fileName){
        List<String> fileContents = FileOps.readFile(fileName);
        
        RawData raw = new RawData();
        
        int curr = 0, prevind = 0;
        for(String currentLine: fileContents){
            String[] parsedVal = currentLine.split(" ");
            int tid = Integer.parseInt(parsedVal[0]);
            
            if(tid == prevind){
                curr++;
            }
            else{
                prevind = tid;
                max = (max > curr)?max:curr;
                curr = 1;
            }
            int iid = Integer.parseInt(parsedVal[1]);
            raw.addEntry(tid, iid);
        }
        return raw;
    }
}
