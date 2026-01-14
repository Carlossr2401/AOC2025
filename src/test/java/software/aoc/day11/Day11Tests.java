package software.aoc.day11;

import org.junit.Test;
import software.aoc.day11.a.Day11PartASolver;
import software.aoc.day11.b.Day11PartBSolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Day11Tests {

    private InstructionReader createReader(Graph graph) {
        return () -> graph;
    }

    private Graph createGraph(Map<String, List<String>> adj) {
        return new Graph(adj);
    }

    @Test
    public void testGraphNeighbors() {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("A", List.of("B", "C"));
        adj.put("B", List.of("C"));
        Graph graph = new Graph(adj);

        assertEquals(List.of("B", "C"), graph.getNeighbors("A"));
        assertEquals(List.of("C"), graph.getNeighbors("B"));
        assertTrue(graph.getNeighbors("C").isEmpty());
    }

    @Test
    public void testRecursivePathCounterSimple() {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("A", List.of("B"));
        adj.put("B", List.of("C"));
        Graph graph = new Graph(adj);

        PathCounter counter = new RecursivePathCounter();
        long paths = counter.countPaths(graph, "A", "C");
        assertEquals(1, paths);
    }

    @Test
    public void testRecursivePathCounterMultiplePaths() {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("A", List.of("B", "C"));
        adj.put("B", List.of("D"));
        adj.put("C", List.of("D"));
        Graph graph = new Graph(adj);

        PathCounter counter = new RecursivePathCounter();
        long paths = counter.countPaths(graph, "A", "D");
        assertEquals(2, paths);
    }

    @Test
    public void testRecursivePathCounterNoPath() {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("A", List.of("B"));
        Graph graph = new Graph(adj);

        PathCounter counter = new RecursivePathCounter();
        long paths = counter.countPaths(graph, "A", "C");
        assertEquals(0, paths);
    }

    @Test
    public void testRecursivePathCounterCycleHandling() {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("A", List.of("B", "C"));
        adj.put("B", List.of("D"));
        adj.put("C", List.of("D"));
        Graph graph = new Graph(adj);

        PathCounter counter = new RecursivePathCounter();
        assertEquals(2, counter.countPaths(graph, "A", "D"));
    }

    @Test
    public void testDay11PartASolver() throws IOException {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("you", List.of("a", "b"));
        adj.put("a", List.of("out"));
        adj.put("b", List.of("out"));
        
        Graph graph = createGraph(adj);
        InstructionReader reader = createReader(graph);
        PathCounter counter = new RecursivePathCounter();
        
        Day11PartASolver solver = new Day11PartASolver(reader, counter);
        assertEquals(2L, solver.solveProblem());
    }

    @Test
    public void testDay11PartBSolverSimple() throws IOException {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("svr", List.of("dac"));
        adj.put("dac", List.of("fft"));
        adj.put("fft", List.of("out"));
        
        Graph graph = createGraph(adj);
        InstructionReader reader = createReader(graph);
        PathCounter counter = new RecursivePathCounter();
        
        Day11PartBSolver solver = new Day11PartBSolver(reader, counter);
        
        assertEquals(1L, solver.solveProblem());
    }

    @Test
    public void testDay11PartBSolverBothWays() throws IOException {
        Map<String, List<String>> adj = new HashMap<>();
        adj.put("svr", List.of("dac", "fft"));
        
        adj.put("dac", List.of("fft"));
        adj.put("fft", List.of("out")); 
        
        Graph graph = createGraph(adj); 
        InstructionReader reader = createReader(graph);
        PathCounter counter = new RecursivePathCounter();
        Day11PartBSolver solver = new Day11PartBSolver(reader, counter);
        
        assertEquals(1L, solver.solveProblem());
    }
}
