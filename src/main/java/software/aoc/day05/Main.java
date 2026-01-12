package software.aoc.day05;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String inputPath = "src/main/java/software/aoc/day05/resources/input";

        Solver solver = SolverFactory.createSolver("A", inputPath);
        System.out.println("Part A Result: " + solver.solve());

        Solver solver2 = SolverFactory.createSolver("B", inputPath);
        System.out.println("Part B Result: " + solver2.solve());
    }
}
