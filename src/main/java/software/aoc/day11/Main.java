package software.aoc.day11;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/java/software/aoc/day11/resources/input";
        InstructionReader reader = ReaderFactory.createFileReader(path);
        PathCounter pathCounter = new RecursivePathCounter();
        
        System.out.println("Part A");
        Solver solverA = SolverFactory.createSolver("a", reader, pathCounter);
        System.out.println("Result A: " + solverA.solveProblem());
             
        System.out.println("Part B");
        Solver solverB = SolverFactory.createSolver("b", reader, pathCounter);
        System.out.println("Result B: " + solverB.solveProblem());
    }
}

