package main.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import main.model.AssociationRule;

public class RuleGeneration {

	public static List<AssociationRule> getRules(
			Map<Set<Integer>, Integer> input, float minconf) {
		List<AssociationRule> result = new ArrayList<>();
		int n = maxSize(input);
		for (int i = 2; i <= n; i++) {
			List<Set<Integer>> currentSet = getSize(input, i);
			for (Set<Integer> currentCandidate : currentSet) {
				List<Set<Integer>> allcombs = Combinatorics
						.allSplits(currentCandidate);
				for (Set<Integer> currentCombination : allcombs) {
					if (input.containsKey(currentCombination)) {
						float confidence = (float) input.get(currentCandidate)
								/ (float) input.get(currentCombination);
						if (confidence >= minconf) {
							Set<Integer> rhs = new HashSet<>(currentCandidate);
							rhs.removeAll(currentCombination);
							result.add(new AssociationRule(currentCombination,
									rhs, input.get(currentCandidate),
									confidence));
						}
					}
				}
			}
		}
		return result;
	}

	public static List<Set<Integer>> getNextGen(List<Set<Integer>> previous,
			int len) {
		List<Set<Integer>> result = new ArrayList<>();
		for (int i = 0; i < previous.size() - 1; i++) {
			for (int j = i + 1; j < previous.size(); j++) {
				Set<Integer> union = new HashSet<Integer>(previous.get(i));
				union.addAll(previous.get(j));
				if (union.size() == len) {
					result.add(union);
				}
			}
		}
		return result;
	}

	public static int maxSize(Map<Set<Integer>, Integer> x) {
		int maxlen = 0;
		for (Entry<Set<Integer>, Integer> entry : x.entrySet()) {
			if (entry.getKey().size() > maxlen) {
				maxlen = entry.getKey().size();
			}
		}
		return maxlen;
	}

	public static List<Set<Integer>> getSize(Map<Set<Integer>, Integer> x,
			int size) {
		List<Set<Integer>> res = new ArrayList<>();
		for (Entry<Set<Integer>, Integer> entry : x.entrySet()) {
			if (entry.getKey().size() == size) {
				res.add(entry.getKey());
			}
		}
		return res;
	}
}
