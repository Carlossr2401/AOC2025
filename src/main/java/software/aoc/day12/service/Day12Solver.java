package software.aoc.day12.service;

import software.aoc.day12.InstructionReader;
import software.aoc.day12.Solver;
import software.aoc.day12.model.Grid;
import software.aoc.day12.model.RegionProblem;
import software.aoc.day12.model.Shape;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * High-level orchestrator for Day 12.
 * Connects the I/O, Parsing, and Logic layers.
 */
public class Day12Solver implements Solver {

    private final InstructionReader reader;
    private final InputParser inputParser;
    private final PlacementStrategy placementStrategy;

    public Day12Solver(InstructionReader reader, InputParser inputParser, PlacementStrategy placementStrategy) {
        this.reader = reader;
        this.inputParser = inputParser;
        this.placementStrategy = placementStrategy;
    }

    @Override
    public Object solveProblem() throws IOException {
        System.out.println("Solving problem...");
        List<String> lines = reader.readInput();

        // 1. Parse Domain Objects
        List<Shape> allShapes = inputParser.parseShapes(lines);
        System.out.println("Parsed " + allShapes.size() + " unique shapes.");

        List<RegionProblem> problems = inputParser.parseProblems(lines);
        System.out.println("Parsed " + problems.size() + " region problems.");

        // 2. Execute Logic
        int solvedCount = processProblems(problems, allShapes);

        return solvedCount;
    }

    private int processProblems(List<RegionProblem> problems, List<Shape> allShapes) {
        int solvedCount = 0;
        int problemIndex = 1;

        for (RegionProblem problem : problems) {
            System.out.print("Problem " + problemIndex + " (" + problem.width() + "x" + problem.length() + ")... ");

            List<Shape> requiredShapes = prepareShapesForProblem(problem, allShapes);
            
            if (requiredShapes == null) {
                // Error in shape definition, skip
                problemIndex++;
                continue;
            }

            Grid grid = new Grid(problem.width(), problem.length());
            boolean fits = placementStrategy.solve(grid, requiredShapes);

            if (fits) {
                System.out.println("FITS!");
                solvedCount++;
            } else {
                System.out.println("Does not fit.");
            }
            problemIndex++;
        }
        return solvedCount;
    }

    private List<Shape> prepareShapesForProblem(RegionProblem problem, List<Shape> allShapes) {
        List<Shape> requiredShapes = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : problem.requiredShapeCounts().entrySet()) {
            int shapeId = entry.getKey();
            int count = entry.getValue();

            Optional<Shape> prototypeOpt = allShapes.stream()
                    .filter(s -> s.getId() == shapeId)
                    .findFirst();

            if (prototypeOpt.isEmpty()) {
                System.out.println("Error: Shape ID " + shapeId + " not found in definitions.");
                return null;
            }

            Shape prototype = prototypeOpt.get();
            for (int i = 0; i < count; i++) {
                requiredShapes.add(prototype);
            }
        }
        return requiredShapes;
    }
}
