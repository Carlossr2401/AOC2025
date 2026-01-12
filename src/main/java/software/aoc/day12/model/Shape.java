package software.aoc.day12.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * Immutable class representing a present's shape.
 * Adheres to SRP: Only responsible for holding shape data and providing transformations.
 */
public final class Shape {
    private final int id;
    private final boolean[][] grid;
    private final int width;
    private final int height;

    public Shape(int id, boolean[][] grid) {
        this.id = id;
        this.height = grid.length;
        this.width = grid[0].length;
        // Deep copy to ensure immutability
        this.grid = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            System.arraycopy(grid[i], 0, this.grid[i], 0, width);
        }
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean get(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            return false;
        }
        return grid[row][col];
    }

    /**
     * Rotates the shape 90 degrees clockwise.
     * @return New rotated Shape instance.
     */
    public Shape rotate() {
        boolean[][] newGrid = new boolean[width][height];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                newGrid[c][height - 1 - r] = grid[r][c];
            }
        }
        return new Shape(id, newGrid);
    }

    /**
     * Flips the shape horizontally.
     * @return New flipped Shape instance.
     */
    public Shape flip() {
        boolean[][] newGrid = new boolean[height][width];
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                newGrid[r][width - 1 - c] = grid[r][c];
            }
        }
        return new Shape(id, newGrid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return id == shape.id && Arrays.deepEquals(grid, shape.grid);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id);
        result = 31 * result + Arrays.deepHashCode(grid);
        return result;
    }
}
