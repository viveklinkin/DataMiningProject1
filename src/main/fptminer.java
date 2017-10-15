package main;

import java.util.Scanner;
import model.DataSet;
import model.FPTree;
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

        int minsup = 2;                             //(args[0]);
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

        System.out.print("Performing FPGrowth");
        //List<ItemSet> sample = FPGrowth.getFrequentItemSets(tree, minsup);
        System.out.print("..Done\n" + Parser.max + "\n");

//        for(ItemSet s: sample){
//            System.out.println(s.getItems() + ":" + s.getSupport());
//        }
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
        System.out.println("Runtime: "
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
}
