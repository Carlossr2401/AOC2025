package software.aoc.day11.b;

import software.aoc.day11.Graph;
import software.aoc.day11.PathCounter;
import software.aoc.day11.Solver;

public class Day11PartBSolver implements Solver {
    private final Graph graph;
    private final PathCounter pathCounter;

    public Day11PartBSolver(Graph graph, PathCounter pathCounter) {
        this.graph = graph;
        this.pathCounter = pathCounter;
    }

    @Override
    public long solveProblem() {
        String start = "svr";
        String end = "out";
        String waypointA = "dac";
        String waypointB = "fft";

        // Case 1: svr -> dac -> fft -> out
        long path1 = pathCounter.countPaths(graph, start, waypointA) 
                   * pathCounter.countPaths(graph, waypointA, waypointB) 
                   * pathCounter.countPaths(graph, waypointB, end);

        // Case 2: svr -> fft -> dac -> out
        long path2 = pathCounter.countPaths(graph, start, waypointB) 
                   * pathCounter.countPaths(graph, waypointB, waypointA) 
                   * pathCounter.countPaths(graph, waypointA, end);

        return path1 + path2;
    }
}
