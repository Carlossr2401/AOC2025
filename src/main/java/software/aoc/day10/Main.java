package software.aoc.day10;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String path = "src/main/java/software/aoc/day10/resources/input";
        InstructionReader reader = ReaderFactory.createFileReader(path);
        
        System.out.println("--- Part A ---");
        Solver solverA = SolverFactory.createSolver("A", reader);
        System.out.println("Result Part A: " + solverA.solve());
        
        System.out.println("--- Part B ---");
        Solver solverB = SolverFactory.createSolver("B", reader);
        System.out.println("Result Part B: " + solverB.solve());
    }
}