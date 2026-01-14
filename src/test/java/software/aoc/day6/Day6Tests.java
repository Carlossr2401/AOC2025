package software.aoc.day6;

import org.junit.Test;
import software.aoc.day06.InstructionReader;
import software.aoc.day06.a.Day06ASolver;
import software.aoc.day06.b.Day06BSolver;
import software.aoc.day06.b.Grid;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class Day6Tests {

    private InstructionReader createReader(List<String> lines) {
        return () -> lines;
    }

    @Test
    public void testPartASimpleAddition() throws IOException {
        List<String> input = List.of(
            "1",
            "1",
            "1",
            "1",
            "+"
        );
        Day06ASolver solver = new Day06ASolver(createReader(input));
        assertEquals(4, solver.solve());
    }

    @Test
    public void testPartASimpleMultiplication() throws IOException {
        List<String> input = List.of(
            "2",
            "3",
            "4",
            "5",
            "*"
        );
        Day06ASolver solver = new Day06ASolver(createReader(input));
        assertEquals(120, solver.solve());
    }

    @Test
    public void testPartAMultipleColumnsSingleOperator() throws IOException {
        List<String> input = List.of(
            "1 2",
            "1 2",
            "1 2",
            "1 2",
            "+"
        );
        Day06ASolver solver = new Day06ASolver(createReader(input));
        assertEquals(12, solver.solve());
    }

    @Test
    public void testPartAMixedOperators() throws IOException {
        List<String> input = List.of(
            "1 2",
            "1 2",
            "1 2",
            "1 2",
            "+ *"
        );
        Day06ASolver solver = new Day06ASolver(createReader(input));
        assertEquals(20, solver.solve());
    }

    @Test
    public void testPartAOperatorCycling() throws IOException {
        List<String> input = List.of(
            "1 2 3",
            "1 2 3",
            "1 2 3",
            "1 2 3",
            "+ *"
        );
        Day06ASolver solver = new Day06ASolver(createReader(input));
        assertEquals(32, solver.solve());
    }

    @Test(expected = IllegalStateException.class)
    public void testPartAInsufficientLines() throws IOException {
        List<String> input = List.of("1", "2", "3");
        Day06ASolver solver = new Day06ASolver(createReader(input));
        solver.solve();
    }

    @Test
    public void testGridCreation() {
        List<String> lines = List.of(
            "ABC",
            "DEF"
        );
        Grid grid = new Grid(lines);
        assertEquals(2, grid.getHeight());
        assertEquals(3, grid.getWidth());
        assertEquals("A", grid.getChar(0,0));
        assertEquals("E", grid.getChar(1,1));
    }

    @Test
    public void testGridOutOfBounds() {
        Grid grid = new Grid(List.of("A"));
    }
}
