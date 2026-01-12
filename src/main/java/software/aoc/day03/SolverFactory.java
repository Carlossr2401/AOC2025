package software.aoc.day03;

import software.aoc.day03.a.SolverA;
import software.aoc.day03.b.SolverB;

public class SolverFactory {
    
    public enum SolverType {
        PART_A,
        PART_B
    }

    public static Solver createSolver(SolverType type, String filePath) {
        InputReader reader = new FileInstructionReader(filePath);
        switch (type) {
            case PART_A:
                return new SolverA(reader);
            case PART_B:
                return new SolverB(reader);
            default:
                throw new IllegalArgumentException("Unknown solver type: " + type);
        }
    }
}