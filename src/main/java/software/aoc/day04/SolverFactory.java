package software.aoc.day04;

import software.aoc.day04.a.Part1Solver;
import software.aoc.day04.b.Part2Solver;



public class SolverFactory {

    public static Solver createSolver(String part, InstructionReader<PaperRollMap> reader) {
        switch (part.toUpperCase()) {
            case "A":
                return new Part1Solver(reader);
            case "B":
                return new Part2Solver(reader);
            default:
                throw new IllegalArgumentException("Unknown part: " + part);
        }
    }
}
