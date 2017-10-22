import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import main.helper.FPGrowth;
import main.helper.FPTreeBuilder;
import main.helper.FileOps;
import main.helper.Preprocess;
import main.imodel.Node;
import main.model.DataSet;
import main.model.FPTree;
import main.model.ItemSet;

public class fptminer {

	private static long startTime = 0;

	public static void main(String args[]) {
		System.out.println("Starting");
		startTimer();
		// validate(args);

		int minsup = 50; // Integer.parseInt(args[0]);
		int minconf = 10; // Integer.parseInt(args[1]);
		String inputfile = "/home/vaidy083/small"; // args[2];
		String outputfile = "/home/vaidy083/outputs50"; // args[3];

		System.out.print("Fetching Data");
		Preprocess preprocessor = new Preprocess(inputfile);
		DataSet data = preprocessor.fetch();
		System.out.print("...Done\n");

		System.out.print("Constructing FPTree");
		FPTreeBuilder fptBuilder = new FPTreeBuilder();
		fptBuilder.buildFPTreeFromDataSet(data);
		FPTree tree = fptBuilder.getTree();
		System.out.print("..Done\n");

		System.out.print("Performing FPGrowth..");
		FPGrowth fpg = new FPGrowth(tree, minsup, 1);
		Map<Integer, List<ItemSet>> frequentItemSets = fpg.performFPGrowth();
		System.out.println("..Done");

		int i = 0, j = 0;
		List<ItemSet> mine = new ArrayList<>();
		for (Entry<Integer, List<ItemSet>> s : frequentItemSets.entrySet()) {
			// System.out.print(s.getKey() + ":{");
			for (ItemSet curr : s.getValue()) {
				i++;
				mine.add(curr);
				if(curr.size()>j)j = curr.size();
				// System.out.print(curr.getItems() + ",");
			}
			// System.out.print("}");
		}
		stopTimer();
		System.out.println("My Length: " + i );
		List<String> readLines = FileOps.readFile(outputfile);
		List<ItemSet> george = new ArrayList<>();
		for (String s : readLines) {
			if (s.contains("=>")) {
				String[] splits = s.split("=>");
				int supp = Integer
						.parseInt((splits[0].trim().split("\\s+")[0]));
				Set<Integer> currentList = new HashSet<>();
				for (String item : splits[1].trim().split(" ")) {
					currentList.add(Integer.parseInt(item) + 1);
				}
				george.add(new ItemSet(currentList, supp));
			}
		}
		System.out.println("George's length: " + george.size());
		int mistakes = 0, percent = 0, prev = 0, iterate = 0;
		for (ItemSet is : george) {
			percent = (iterate++ *100)/george.size();
			if(percent>prev){
				prev = percent;
				System.out.println(percent);
			}
			Iterator<ItemSet> iter = mine.iterator();
			boolean check = true;
			while (iter.hasNext()) {
				if (is.equals(iter.next())) {
					check = false;
					iter.remove();
				}
			}
			if (check) {
				System.out.println(is.getItems());
				mistakes++;
			}
		}
		for (ItemSet is : mine) {
		//	System.out.println(is.getItems());
		}
		System.out.println("mistakes " + mistakes + " diff: "
				+ (george.size() - i));
		//stopTimer();
	}

	private static void validate(String args[]) {
		if (args.length != 4) {
			throw new RuntimeException("Check input format " + args.length);
		}
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

	private static void print(Map<Integer, List<Node>> meta) {
		for (Entry<Integer, List<Node>> entry : meta.entrySet()) {
			System.out.print(entry.getKey() + ":[");
			for (Node no : entry.getValue()) {
				System.out.print(no.getSupportCount() + ",");
			}
			System.out.print("]");
		}
		System.out.println();
	}
}
