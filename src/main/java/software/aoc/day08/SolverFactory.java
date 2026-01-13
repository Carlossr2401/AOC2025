package software.aoc.day08;

import software.aoc.day08.a.Day08PartASolver;
import software.aoc.day08.b.Day08PartBSolver;

public class SolverFactory {
    public static Solver createSolver(String part, InstructionReader<JunctionBoxList> reader) {
        if ("A".equalsIgnoreCase(part)) {
            return new Day08PartASolver(reader);
        } else if ("B".equalsIgnoreCase(part)) {
            return new Day08PartBSolver(reader);
        } else {
            throw new IllegalArgumentException("Unknown solver part: " + part);
        }
    }
}
