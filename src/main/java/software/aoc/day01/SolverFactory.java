package software.aoc.day01;

import software.aoc.day01.a.SolverA;
import software.aoc.day01.b.SolverB;

public class SolverFactory {
    public static Solver createSolver(String part, InstructionReader reader) {
        return switch (part.toUpperCase()) {
            case "A" -> new SolverA(reader);
            case "B" -> new SolverB(reader);
            default -> throw new IllegalArgumentException("Unknown part: " + part);
        };
    }
}
