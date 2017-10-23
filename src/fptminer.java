import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import main.helper.FPGrowth;
import main.helper.FPTreeBuilder;
import main.helper.FileOps;
import main.helper.Preprocess;
import main.helper.RuleGeneration;
import main.model.AssociationRule;
import main.model.DataSet;
import main.model.FPTree;
import main.model.ItemSet;

public class fptminer {

	private static long start = 0;

	public static void main(String args[]) {
		System.out.println("******************FPTreeMiner******************");
		System.out.println("Starting");
		validate(args);

		int minsup = Integer.parseInt(args[0]);
		float minconf = Float.parseFloat(args[1]);
		String inputfile = args[2];
		String outputfile = args[3];

		System.out.print("computing for the following args:\t");
		for (String x : args)
			System.out.print(x + "\t");

		System.out.print("\n\n\nFetching Data");
		Preprocess preprocessor = new Preprocess(inputfile);
		DataSet data = preprocessor.fetch();
		System.out.print("...Done\n");

		System.out.print("Constructing FPTree...");
		FPTreeBuilder fptBuilder = new FPTreeBuilder();
		fptBuilder.buildFPTreeFromDataSet(data);
		FPTree tree = fptBuilder.getTree();
		System.out.print("..Done\n");

		System.out.print("Performing FPGrowth..Timer Start");
		startTimer();
		FPGrowth fpg = new FPGrowth(tree, minsup, 1);
		Map<Set<Integer>, Integer> frequentItemSets = fpg.performFPGrowth();
		System.out.println("..Done");
		stopTimer();
		System.out.println("Number of frequent itemsets: " + frequentItemSets.size());

		List<AssociationRule> associationRules = null;
		if (minsup >= 30) {
			System.out.print("Forming Association Rules..Timer Start");
			startTimer();
			associationRules = RuleGeneration.getRules(frequentItemSets,
					minconf);
			System.out.println("...Done");
			stopTimer();
			System.out.println("Association rule size: " + associationRules.size());
		} else {
			System.out.println("minsup < 30. skipping association rule generation");
			associationRules = new ArrayList<>();
			for (Entry<Set<Integer>, Integer> entry : frequentItemSets
					.entrySet()) {
				associationRules.add(new AssociationRule(entry.getKey(), null,
						entry.getValue(), -1));
			}
		}
		List<String> outputFileLines = new ArrayList<String>();
		for (AssociationRule ar : associationRules) {
			outputFileLines.add(ar.toString());
		}

		System.out.print("Writing contents to file:" + outputfile);
		FileOps.writeFile(outputfile, outputFileLines);
		System.out.println("...Done");
		System.out.println("******************EXIT***************");
	}

	private static void validate(String args[]) {
		if (args.length != 4) {
			throw new RuntimeException("Check input format " + args.length);
		}
	}

	private static void startTimer() {
		start = System.currentTimeMillis();
	}

	private static void stopTimer() {
		System.out.println("\nRuntime: "
				+ (double) (System.currentTimeMillis() - start) / 1000.0 + "s");
	}
}
