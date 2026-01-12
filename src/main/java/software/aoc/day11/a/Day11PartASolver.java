package software.aoc.day11.a;

import software.aoc.day11.Graph;
import software.aoc.day11.PathCounter;
import software.aoc.day11.Solver;

public class Day11PartASolver implements Solver {
    private final Graph graph;
    private final PathCounter pathCounter;

    public Day11PartASolver(Graph graph, PathCounter pathCounter) {
        this.graph = graph;
        this.pathCounter = pathCounter;
    }

    @Override
    public long solveProblem() {
        return pathCounter.countPaths(graph, "you", "out");
    }
}
