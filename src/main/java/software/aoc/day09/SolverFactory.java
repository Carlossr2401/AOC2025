package software.aoc.day09;

import software.aoc.day09.a.Day09PartASolver;
import software.aoc.day09.b.Day09PartBSolver;



public class SolverFactory {
    public static Solver createSolver(String type, InstructionReader reader) {
        return switch (type) {
            case "A" -> new Day09PartASolver(reader);
            case "B" -> new Day09PartBSolver(reader);
            default -> throw new IllegalArgumentException("Unknown solver type: " + type);
        };
    }
}
