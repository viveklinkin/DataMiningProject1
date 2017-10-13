package main;


import java.util.Scanner;
import model.TransactionList;
import preprocess.DataFetcher;


public class fptminer {

    public static void main(String args[]) {
        //TODO: Accept args instead of System.in
        //validate(args);
        Scanner sc = new Scanner(System.in);
        int minsup = Integer.parseInt(sc.nextLine());        //(args[0]);
        int minconf = Integer.parseInt(sc.nextLine());       //(args[1]);
        String inputfile = sc.nextLine();                    //args[2];
        String outputfile = sc.nextLine();                   //args[3];
        
        long startTime = System.currentTimeMillis();
        
        DataFetcher fetcher = new DataFetcher(inputfile);
        TransactionList tl = fetcher.fetch();
        
        System.out.println(tl.getFrequencyOf(1));
        
        long endTime = System.currentTimeMillis();
        double totalTime = endTime - startTime;
        totalTime /= 1000;
        System.out.println("Runtime: " + totalTime + "s");
    }

    private static void validate(String args[]) {
        if (args.length != 4) {
            throw new RuntimeException("Check input format " + args.length);
        }
        System.exit(1);
    }
}
