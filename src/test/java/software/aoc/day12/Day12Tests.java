package software.aoc.day12;

import org.junit.Test;
import software.aoc.day12.model.Grid;
import software.aoc.day12.model.Shape;
import software.aoc.day12.service.BacktrackingSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day12Tests {

    private Shape createSingleCellShape(int id) {
        boolean[][] grid = {{true}};
        return new Shape(id, grid);
    }

    private Shape createLineShape(int id, int length) {
        boolean[][] grid = new boolean[1][length];
        Arrays.fill(grid[0], true);
        return new Shape(id, grid);
    }
    
    @Test
    public void testShapeCreation() {
        boolean[][] grid = {{true, false}, {true, true}};
        Shape shape = new Shape(1, grid);
        
        assertEquals(1, shape.getId());
        assertEquals(2, shape.getHeight());
        assertEquals(2, shape.getWidth());
        assertTrue(shape.get(0, 0));
        assertFalse(shape.get(0, 1));
    }

    @Test
    public void testShapeImmutability() {
        boolean[][] grid = {{true}};
        Shape shape = new Shape(1, grid);
        
        grid[0][0] = false;
        
        assertTrue(shape.get(0, 0));
    }

    @Test
    public void testShapeRotation() {
        boolean[][] grid = {{true, false}, {true, true}};
        Shape shape = new Shape(1, grid);
        
        Shape rotated = shape.rotate();
        
        assertTrue(rotated.get(0, 0));
        assertTrue(rotated.get(0, 1));
        assertFalse(rotated.get(1, 0));
        assertTrue(rotated.get(1, 1));
    }

    @Test
    public void testGridPlacement() {
        Grid grid = new Grid(3, 3);
        Shape shape = createSingleCellShape(1);
        
        assertTrue("Should be able to place in empty grid", grid.canPlace(shape, 0, 0));
        
        grid.place(shape, 0, 0, 1);
        assertFalse("Should not be able to overlap", grid.canPlace(shape, 0, 0));
        
        grid.remove(shape, 0, 0);
        assertTrue("Should be able to place after removal", grid.canPlace(shape, 0, 0));
    }
    
    @Test
    public void testGridBounds() {
        Grid grid = new Grid(2, 2);
        Shape shape3x1 = createLineShape(1, 3);
        
        assertFalse("Should fail if shape exceeds width", grid.canPlace(shape3x1, 0, 0));
    }

    @Test
    public void testBacktrackingSolverSimple() {
        Grid grid = new Grid(2, 1);
        List<Shape> shapes = new ArrayList<>();
        shapes.add(createSingleCellShape(1));
        shapes.add(createSingleCellShape(2));
        
        BacktrackingSolver solver = new BacktrackingSolver();
        assertTrue("Should solve simple case", solver.solve(grid, shapes));
    }

    @Test
    public void testBacktrackingSolverImpossibleArea() {
        Grid grid = new Grid(1, 1);
        List<Shape> shapes = new ArrayList<>();
        shapes.add(createLineShape(1, 2));
        
        BacktrackingSolver solver = new BacktrackingSolver();
        assertFalse("Should not solve impossible fit", solver.solve(grid, shapes));
    }

    @Test
    public void testBacktrackingSolverRotationNeeded() {
        Grid grid = new Grid(1, 2);
        List<Shape> shapes = new ArrayList<>();
        shapes.add(createLineShape(1, 2));
        
        BacktrackingSolver solver = new BacktrackingSolver();
        assertTrue("Should solve by rotating shape", solver.solve(grid, shapes));
    }
    
    @Test
    public void testBacktrackingSolverComplex() {
        Grid grid = new Grid(3, 3);
        List<Shape> shapes = new ArrayList<>();
        
        shapes.add(createLineShape(1, 3));
        
        boolean[][] lGrid = {{true, false}, {true, true}};
        shapes.add(new Shape(2, lGrid));
        
        shapes.add(createLineShape(3, 3));
        
        BacktrackingSolver solver = new BacktrackingSolver();
        assertTrue("Should solve perfect fit puzzle", solver.solve(grid, shapes));
    }
}
