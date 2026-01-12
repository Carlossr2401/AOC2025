package software.aoc.day12.service;

import software.aoc.day12.model.Grid;
import software.aoc.day12.model.Shape;

import java.util.List;

/**
 * Strategy interface for solving the placement problem.
 * Allows switching between different algorithms (e.g., Backtracking, DLX) without changing client code.
 */
public interface PlacementStrategy {
    /**
     * Attempts to place all shapes onto the grid.
     * @param grid The grid to place shapes on.
     * @param shapes The list of shapes to place.
     * @return true if all shapes fit, false otherwise.
     */
    boolean solve(Grid grid, List<Shape> shapes);
}
