package software.aoc.day12;

import software.aoc.day12.service.Day12Solver;
import software.aoc.day12.service.InputParser;
import software.aoc.day12.service.PlacementStrategy;

public class SolverFactory {
    public static Solver createSolver(String type, InstructionReader reader, InputParser parser, PlacementStrategy strategy) {
        if ("A".equalsIgnoreCase(type)) {
            // Day12Solver acts as Part A solver based on context
            return new Day12Solver(reader, parser, strategy);
        }
        throw new IllegalArgumentException("Unknown solver type: " + type);
    }
}
