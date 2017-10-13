package util;

import java.util.Comparator;
import java.util.HashMap;


public class RawDataSorter implements Comparator<Integer>{
    
    HashMap<Integer, Integer> frequencyTable = new HashMap<>();
    @Override
    public int compare(Integer t1, Integer t2) {
        if(frequencyTable.get(t1) < frequencyTable.get(t2)){
            return 1;
        }
        return -1;
    }
    
}
