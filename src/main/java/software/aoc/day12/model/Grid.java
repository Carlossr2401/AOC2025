package software.aoc.day12.model;

import java.util.Arrays;

/**
 * Represents the placement area (region).
 * manages the state of the grid (filled/empty).
 */
public class Grid {
    private final int width;
    private final int height;
    private final int[][] cells; // Stores the shape ID occupying the cell, or -1 if empty

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new int[height][width];
        for (int[] row : cells) {
            Arrays.fill(row, -1);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Checks if a shape can be placed at the specified position.
     * @param shape The shape to place.
     * @param startX Top-left X coordinate.
     * @param startY Top-left Y coordinate.
     * @return true if placement is valid.
     */
    public boolean canPlace(Shape shape, int startX, int startY) {
        if (startX + shape.getWidth() > width || startY + shape.getHeight() > height) {
            return false;
        }

        for (int r = 0; r < shape.getHeight(); r++) {
            for (int c = 0; c < shape.getWidth(); c++) {
                if (shape.get(r, c)) {
                    if (cells[startY + r][startX + c] != -1) {
                        return false; // Overlap
                    }
                }
            }
        }
        return true;
    }

    /**
     * Places a shape on the grid. Assumes canPlace checked beforehand.
     */
    public void place(Shape shape, int startX, int startY, int uniquePlacementId) {
        for (int r = 0; r < shape.getHeight(); r++) {
            for (int c = 0; c < shape.getWidth(); c++) {
                if (shape.get(r, c)) {
                    cells[startY + r][startX + c] = uniquePlacementId;
                }
            }
        }
    }

    /**
     * Removes a shape from the grid.
     */
    public void remove(Shape shape, int startX, int startY) {
        for (int r = 0; r < shape.getHeight(); r++) {
            for (int c = 0; c < shape.getWidth(); c++) {
                if (shape.get(r, c)) {
                    cells[startY + r][startX + c] = -1;
                }
            }
        }
    }
}
