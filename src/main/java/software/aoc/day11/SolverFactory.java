package software.aoc.day11;

import software.aoc.day11.a.Day11PartASolver;
import software.aoc.day11.b.Day11PartBSolver;

public class SolverFactory {
    public static Solver createSolver(String type, InstructionReader reader, PathCounter pathCounter) {
        return switch (type.toLowerCase()) {
            case "a" -> new Day11PartASolver(reader, pathCounter);
            case "b" -> new Day11PartBSolver(reader, pathCounter);
            default -> throw new IllegalArgumentException("Unknown solver type: " + type);
        };
    }
}
