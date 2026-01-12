package software.aoc.day04;

import software.aoc.day04.a.Part1Solver;
import software.aoc.day04.b.Part2Solver;

import java.io.IOException;

public class SolverFactory {

    public static Solver createSolver(String part, String filePath) throws IOException {
        InstructionReader<PaperRollMap> reader = new FileInstructionReader(filePath);
        PaperRollMap map = reader.readAllLines();

        switch (part.toUpperCase()) {
            case "A":
                return new Part1Solver(map);
            case "B":
                return new Part2Solver(map);
            default:
                throw new IllegalArgumentException("Unknown part: " + part);
        }
    }
}
