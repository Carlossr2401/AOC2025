package software.aoc.day11;

import software.aoc.day11.a.Day11PartASolver;
import software.aoc.day11.b.Day11PartBSolver;

public class SolverFactory {
    public static Solver createSolver(String type, Graph graph, PathCounter pathCounter) {
        return switch (type.toLowerCase()) {
            case "a" -> new Day11PartASolver(graph, pathCounter);
            case "b" -> new Day11PartBSolver(graph, pathCounter);
            default -> throw new IllegalArgumentException("Unknown solver type: " + type);
        };
    }
}
