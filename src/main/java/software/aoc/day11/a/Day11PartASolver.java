package software.aoc.day11.a;

import software.aoc.day11.InstructionReader;
import software.aoc.day11.Graph;
import software.aoc.day11.PathCounter;
import software.aoc.day11.Solver;

import java.io.IOException;

public class Day11PartASolver implements Solver {
    private final InstructionReader reader;
    private final PathCounter pathCounter;

    public Day11PartASolver(InstructionReader reader, PathCounter pathCounter) {
        this.reader = reader;
        this.pathCounter = pathCounter;
    }

    @Override
    public Object solveProblem() throws IOException {
        Graph graph = reader.readGraph();
        return pathCounter.countPaths(graph, "you", "out");
    }
}
