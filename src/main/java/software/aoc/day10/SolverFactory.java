package software.aoc.day10;

import software.aoc.day10.a.Day10PartASolver;
import software.aoc.day10.b.Day10PartBSolver;

public class SolverFactory {
    public Solver createSolver(String type, InstructionReader reader) {
        
        if ("A".equalsIgnoreCase(type)) {
            return new Day10PartASolver(reader);
        } else if ("B".equalsIgnoreCase(type)) {
            return new Day10PartBSolver(reader);
        }
        
        throw new IllegalArgumentException("Unknown solver type: " + type);
    }
}
