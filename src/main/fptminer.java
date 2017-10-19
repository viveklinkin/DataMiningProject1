package main;

import imodel.Node;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import model.DataSet;
import model.FPTree;
import model.ItemSet;
import proc.FPGrowth;
import proc.FPTreeBuilder;
import proc.Preprocess;
import util.Parser;

public class fptminer {

    private static long startTime = 0;
    private static boolean isRunning = false;
    private static int complete = 0;
    

    public static void main(String args[]) {
        /**
         ******************************************
         ************Temporary Code Strip**********
         * *****************************************
         */
        //TODO: Accept args instead of System.in
        //validate(args);
        Scanner sc = new Scanner(System.in);

        int minsup = 10;                             //(args[0]);
        int minconf = 10;                            //(args[1]);
        String inputfile = "/home/vivek/large";      //args[2];
        String outputfile = "nothingfornow";         //args[3];
        /**
         * **********Modify till here**************
         */

        startTimer();
        System.out.print("Fetching Data");
        Preprocess preprocessor = new Preprocess(inputfile);
        DataSet data = preprocessor.fetch();
        System.out.print("...Done\n");

//System.out.println("Uncompressed Data size: " + Instrumentation.getObjectSize());
        System.out.print("Constructing FPTree");
        FPTreeBuilder fptBuilder = new FPTreeBuilder();
        fptBuilder.buildFPTreeFromDataSet(data);
        FPTree tree = fptBuilder.getTree();
        System.out.print("..Done\n");

        //int x = tree.getRoot().getChild(1).getChild(2).getSupportCount();
        stopProcess();
        
//        Map<Integer, List<Node>>meta = tree.getMeta();
//        tree.project(2);
//        tree.project(1);
//        print(tree.getMeta());
//        tree.applyMeta(meta);
//        tree.project(1);
//        System.out.print(tree.getItemFrequency());
//        tree.applyMeta(meta);
//        print(tree.getMeta());

        System.out.print("Performing FPGrowth and generating frequent itemsets");
        FPGrowth fpg = new FPGrowth(tree, minsup);
        Map<Integer, List<ItemSet>> frequentItemSets = fpg.performFPGrowth();
        System.out.println("..Done" + frequentItemSets.size());
        
        for(Entry<Integer, List<ItemSet>> s: frequentItemSets.entrySet()){
            System.out.print(s.getKey() + ":{");
            for(ItemSet curr: s.getValue()){
                System.out.print(curr.getItems()+ ",");
            }
            System.out.print("}");
        }
        stopTimer();
    }

    private static void validate(String args[]) {
        if (args.length != 4) {
            throw new RuntimeException("Check input format " + args.length);
        }
        System.exit(1);
    }

    private static void startTimer() {
        startTime = System.currentTimeMillis();
    }

    private static void stopTimer() {
        if (startTime == 0) {
            throw new RuntimeException("Timer stopped without start");
        }
        System.out.println("\nRuntime: "
                + (double) (System.currentTimeMillis() - startTime) / 1000.0
                + "s");
    }
    public static boolean isRunning(){
        return isRunning;
    }
    public static int getStatus(){
        return complete;
    }
    private static void stopProcess(){
        isRunning = false;
    }
    
    private static void print(Map<Integer, List<Node>> meta){
        for(Entry<Integer, List<Node>> entry : meta.entrySet()){
            System.out.print(entry.getKey() + ":[");
            for(Node no: entry.getValue()){
                System.out.print(no.getSupportCount() + ",");
            }
            System.out.print("]");
        }
        System.out.println();
    }
}
