package software.aoc.day12.service;

import software.aoc.day12.model.Grid;
import software.aoc.day12.model.Shape;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Solves the placement problem using recursive backtracking.
 */
public class BacktrackingSolver implements PlacementStrategy {

    @Override
    public boolean solve(Grid grid, List<Shape> shapes) {
        // Optimization: Fail fast if total area of shapes > grid area
        int totalShapeArea = shapes.stream()
                .mapToInt(s -> {
                    int count = 0;
                    for (int r = 0; r < s.getHeight(); r++) {
                        for (int c = 0; c < s.getWidth(); c++) {
                            if (s.get(r, c)) count++;
                        }
                    }
                    return count;
                }).sum();
        
        if (totalShapeArea > grid.getWidth() * grid.getHeight()) {
            return false;
        }

        return backtrack(grid, shapes, 0);
    }

    private boolean backtrack(Grid grid, List<Shape> shapes, int index) {
        if (index >= shapes.size()) {
            return true; // All shapes placed successfully
        }

        Shape currentShape = shapes.get(index);
        Set<Shape> variants = generateVariants(currentShape);

        for (Shape variant : variants) {
            // Try every possible position on the grid
            // Optimization: Iterate only where the shape could possibly fit within bounds
            for (int r = 0; r <= grid.getHeight() - variant.getHeight(); r++) {
                for (int c = 0; c <= grid.getWidth() - variant.getWidth(); c++) {
                    
                    if (grid.canPlace(variant, c, r)) {
                        grid.place(variant, c, r, index + 1); // Use index+1 as ID for debugging visibility
                        
                        if (backtrack(grid, shapes, index + 1)) {
                            return true;
                        }
                        
                        grid.remove(variant, c, r); // Backtrack
                    }
                }
            }
        }

        return false;
    }

    /**
     * Generates all 8 possible orientations (rotations * flips).
     */
    private Set<Shape> generateVariants(Shape shape) {
        Set<Shape> variants = new HashSet<>();
        Shape current = shape;

        // 4 rotations for original
        for (int i = 0; i < 4; i++) {
            variants.add(current);
            current = current.rotate();
        }

        // Flip and do 4 rotations
        current = shape.flip();
        for (int i = 0; i < 4; i++) {
            variants.add(current);
            current = current.rotate();
        }
        
        return variants;
    }
}
