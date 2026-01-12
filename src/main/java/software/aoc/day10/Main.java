package software.aoc.day10;

import java.io.IOException;

public class Main {

    static void main() throws IOException {
        Solver solver;
        String path = "src/main/java/software/aoc/day10/resources/input";
        ReaderFactory readerFactory = new ReaderFactory();
        InstructionReader reader = readerFactory.createFileReader(path);
        SolverFactory solverFactory = new SolverFactory();
        
        System.out.println("--- Part A ---");
        solver = solverFactory.createSolver("A", reader);
        System.out.println("Result Part A: " + solver.solve());
        
        System.out.println("--- Part B ---");
        solver = solverFactory.createSolver("B", reader);
        System.out.println("Result Part B: " + solver.solve());
    }
}