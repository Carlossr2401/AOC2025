package software.aoc.day12.service;

import software.aoc.day12.model.RegionProblem;
import software.aoc.day12.model.Shape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day12InputParser implements InputParser {

    private final ShapeFactory shapeFactory;

    public Day12InputParser(ShapeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    @Override
    public List<Shape> parseShapes(List<String> lines) {
        List<Shape> shapes = new ArrayList<>();
        List<String> currentShapeLines = new ArrayList<>();
        int currentId = -1;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            if (line.matches("\\d+:")) {
                if (currentId != -1) {
                    shapes.add(shapeFactory.createShape(currentId, currentShapeLines));
                    currentShapeLines.clear();
                }
                currentId = Integer.parseInt(line.replace(":", ""));
            } else if (line.matches("^[\\.#]+$")) {
                currentShapeLines.add(line);
            } else if (line.contains("x")) {
                // We reached the problem definitions, stop parsing shapes.
                break;
            }
        }
        // Add the last shape if exists
        if (currentId != -1 && !currentShapeLines.isEmpty()) {
            shapes.add(shapeFactory.createShape(currentId, currentShapeLines));
        }

        return shapes;
    }

    @Override
    public List<RegionProblem> parseProblems(List<String> lines) {
        List<RegionProblem> problems = new ArrayList<>();
        Pattern problemPattern = Pattern.compile("(\\d+)x(\\d+):(.*)");

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            Matcher matcher = problemPattern.matcher(line);
            if (matcher.matches()) {
                int width = Integer.parseInt(matcher.group(1));
                int length = Integer.parseInt(matcher.group(2));
                String[] counts = matcher.group(3).trim().split("\\s+");
                
                Map<Integer, Integer> requiredCounts = new HashMap<>();
                for (int i = 0; i < counts.length; i++) {
                     int count = Integer.parseInt(counts[i]);
                     if (count > 0) {
                         requiredCounts.put(i, count);
                     }
                }
                problems.add(new RegionProblem(width, length, requiredCounts));
            }
        }
        return problems;
    }
}
