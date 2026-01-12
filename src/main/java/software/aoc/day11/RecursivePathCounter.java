package software.aoc.day11;

import java.util.HashMap;
import java.util.Map;

public class RecursivePathCounter implements PathCounter {

    @Override
    public long countPaths(Graph graph, String start, String end) {
        return countPathsRecursive(graph, start, end, new HashMap<>());
    }

    private long countPathsRecursive(Graph graph, String current, String end, Map<String, Long> memo) {
        if (current.equals(end)) {
            return 1;
        }
        if (memo.containsKey(current)) {
            return memo.get(current);
        }

        long count = 0;
        for (String neighbor : graph.getNeighbors(current)) {
            count += countPathsRecursive(graph, neighbor, end, memo);
        }

        memo.put(current, count);
        return count;
    }
}
