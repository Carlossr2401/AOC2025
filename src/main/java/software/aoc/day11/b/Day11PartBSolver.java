package software.aoc.day11.b;

import software.aoc.day11.InstructionReader;
import software.aoc.day11.Graph;
import software.aoc.day11.PathCounter;
import software.aoc.day11.Solver;

import java.io.IOException;

public class Day11PartBSolver implements Solver {
    private final InstructionReader reader;
    private final PathCounter pathCounter;

    public Day11PartBSolver(InstructionReader reader, PathCounter pathCounter) {
        this.reader = reader;
        this.pathCounter = pathCounter;
    }

    @Override
    public Object solveProblem() throws IOException {
        Graph graph = reader.readGraph();
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
