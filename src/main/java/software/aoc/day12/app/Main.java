package software.aoc.day12.app;

import software.aoc.day12.*;
import software.aoc.day12.service.*;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1. Composition Root / Dependency Injection
        ShapeFactory shapeFactory = new ShapeFactory();
        InputParser inputParser = new Day12InputParser(shapeFactory);
        
        String path = "src/main/java/software/aoc/day12/resources/input";
        InstructionReader reader = ReaderFactory.createFileReader(path);
        
        PlacementStrategy strategy = new BacktrackingSolver();

        // 2. Orchestrator
        Solver solver = SolverFactory.createSolver("A", reader, inputParser, strategy);

        // 3. Execution
        System.out.println("Final Answer: " + solver.solveProblem());
    }
}
