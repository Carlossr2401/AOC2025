package software.aoc.day08;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        SolverFactory factory = new SolverFactory();
        
        System.out.println("Part A Solution:");
        Solver solverA = factory.createPartASolver("src/main/java/software/aoc/day08/resources/input");
        System.out.println(solverA.solve());

        System.out.println("Part B Solution:");
        Solver solverB = factory.createPartBSolver("src/main/java/software/aoc/day08/resources/input");
        System.out.println(solverB.solve());
    }
}
