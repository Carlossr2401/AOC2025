package software.aoc.day11;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInstructionReader reader = new FileInstructionReader("src/main/java/software/aoc/day11/resources/input");
        Graph graph = reader.readGraph();
        PathCounter pathCounter = new RecursivePathCounter();
        
        System.out.println("Part A");
        Solver solverA = SolverFactory.createSolver("a", graph, pathCounter);
        System.out.println("Result A: " + solverA.solveProblem());
             
        System.out.println("Part B");
        Solver solverB = SolverFactory.createSolver("b", graph, pathCounter);
        System.out.println("Result B: " + solverB.solveProblem());
    }
}

