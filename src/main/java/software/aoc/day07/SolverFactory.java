package software.aoc.day07;

import software.aoc.day07.a.Day07ASolver;
import software.aoc.day07.b.Day07BSolver;

public class SolverFactory {
    public static Solver createSolver(String type, InstructionReader reader) {
        if ("A".equalsIgnoreCase(type)) {
            return new Day07ASolver(reader);
        } else if ("B".equalsIgnoreCase(type)) {
            return new Day07BSolver(reader);
        } else {
            throw new IllegalArgumentException("Unknown solver type: " + type);
        }
    }
}
