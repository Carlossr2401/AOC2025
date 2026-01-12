package software.aoc.day11;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public record Graph(Map<String, List<String>> adjacencyList) {
    public Graph {
        adjacencyList = Collections.unmodifiableMap(adjacencyList);
    }

    public List<String> getNeighbors(String node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }
}
