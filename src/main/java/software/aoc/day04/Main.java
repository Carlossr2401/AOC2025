package software.aoc.day04;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Strategy for Part A
        System.out.println("Solving Part A...");
        String filePath = "src/main/java/software/aoc/day04/resources/input.txt";
        InstructionReader<PaperRollMap> reader = ReaderFactory.createReader(filePath);

        Solver solverA = SolverFactory.createSolver("A", reader);
        System.out.println("Result Part 1: " + solverA.solve());

        Solver solverB = SolverFactory.createSolver("B", reader);
        System.out.println("Result Part 2: " + solverB.solve());
    }
}
