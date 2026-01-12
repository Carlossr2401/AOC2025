package software.aoc.day12.app;

import software.aoc.day12.service.*;

public class Main {
    public static void main(String[] args) {
        // 1. Composition Root / Dependency Injection
        ShapeFactory shapeFactory = new ShapeFactory();
        InputParser inputParser = new Day12InputParser(shapeFactory);
        FileInstructionReader fileReader = new FileInstructionReader();
        PlacementStrategy strategy = new BacktrackingSolver();

        // 2. Orchestrator
        Day12Solver application = new Day12Solver(fileReader, inputParser, strategy);

        // 3. Execution
        String fileName = "input";

        application.solve(fileName);
    }
}
