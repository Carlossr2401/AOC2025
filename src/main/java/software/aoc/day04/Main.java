package software.aoc.day04;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Strategy for Part A
        System.out.println("Solving Part A...");
        Solver solverA = SolverFactory.createSolver("A", "src/main/java/software/aoc/day4/resources/map.txt");
        System.out.println("Result Part A: " + solverA.solve());

        // Strategy for Part B
        System.out.println("Solving Part B...");
        Solver solverB = SolverFactory.createSolver("B", "src/main/java/software/aoc/day4/resources/map.txt");
        System.out.println("Result Part B: " + solverB.solve());
    }
}
