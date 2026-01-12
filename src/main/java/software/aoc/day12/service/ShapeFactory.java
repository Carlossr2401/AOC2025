package software.aoc.day12.service;

import software.aoc.day12.model.Shape;

import java.util.List;

/**
 * Factory responsible for creating Shape objects.
 * Encapsulates the complexity of converting character grids to Shape instances.
 */
public class ShapeFactory {

    public Shape createShape(int id, List<String> shapeLines) {
        if (shapeLines.isEmpty()) {
            throw new IllegalArgumentException("Shape lines cannot be empty");
        }

        int height = shapeLines.size();
        int width = shapeLines.stream().mapToInt(String::length).max().orElse(0);
        boolean[][] grid = new boolean[height][width];

        for (int r = 0; r < height; r++) {
            String line = shapeLines.get(r);
            for (int c = 0; c < line.length(); c++) {
                if (line.charAt(c) == '#') {
                    grid[r][c] = true;
                }
            }
        }

        return new Shape(id, grid);
    }
}
