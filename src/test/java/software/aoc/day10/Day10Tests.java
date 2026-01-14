package software.aoc.day10;

import org.junit.Test;
import software.aoc.day10.a.Day10PartASolver;
import software.aoc.day10.a.LightConfiguration;
import software.aoc.day10.b.Day10PartBSolver;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class Day10Tests {

    private InstructionReader createReader(List<String> lines) {
        return () -> lines;
    }

    @Test
    public void testParserPartA() {
        String line = "Machine [.#] (0) (1)";
        
        Machine<LightConfiguration> machine = MachineParser.parseForPartA(line);
        
        List<Boolean> config = machine.configuration().configuration();
        assertEquals(2, config.size());
        assertFalse(config.get(0));
        assertTrue(config.get(1));
        
        assertEquals(2, machine.buttons().size());
        assertEquals(List.of(0), machine.buttons().get(0).positions());
        assertEquals(List.of(1), machine.buttons().get(1).positions());
    }

    @Test
    public void testParserPartB() {
        String line = "Machine {10, 20} (0, 1) (1)";
        
        Machine<List<Integer>> machine = MachineParser.parseForPartB(line);
        
        List<Integer> targets = machine.configuration();
        assertEquals(2, targets.size());
        assertEquals(Integer.valueOf(10), targets.get(0));
        assertEquals(Integer.valueOf(20), targets.get(1));
        
        assertEquals(2, machine.buttons().size());
        assertEquals(List.of(0, 1), machine.buttons().get(0).positions());
    }

    @Test
    public void testPartASimple() throws IOException {
        List<String> input = List.of(
            "Machine [#] (0)"
        );
        
        Day10PartASolver solver = new Day10PartASolver(createReader(input));
        assertEquals(1, solver.solve());
    }

    @Test
    public void testPartATwoButtons() throws IOException {
        List<String> input = List.of(
            "Machine [##] (0) (1)"
        );
        
        Day10PartASolver solver = new Day10PartASolver(createReader(input));
        assertEquals(2, solver.solve());
    }

    @Test
    public void testPartAInteraction() throws IOException {
        List<String> input = List.of(
            "Machine [#.] (0,1) (1)"
        );
        
        Day10PartASolver solver = new Day10PartASolver(createReader(input));
        assertEquals(2, solver.solve());
    }
    
    @Test
    public void testPartANoSolution() throws IOException {
        List<String> input = List.of(
            "Machine [#] (1)"
        );
        Day10PartASolver solver = new Day10PartASolver(createReader(input));
        int result = (int)solver.solve();
        assertEquals(Integer.MAX_VALUE, result); 
    }

    @Test
    public void testPartBSimpleLinear() throws IOException {
        List<String> input = List.of(
            "Machine {10} (0)"
        );
        
        Day10PartBSolver solver = new Day10PartBSolver(createReader(input));
        assertEquals(10L, solver.solve());
    }

    @Test
    public void testPartBSystem() throws IOException {
        List<String> input = List.of(
            "Machine {10, 20} (0) (1)"
        );
        
        Day10PartBSolver solver = new Day10PartBSolver(createReader(input));
        assertEquals(30L, solver.solve());
    }
    
    @Test
    public void testPartBInteraction() throws IOException {
        List<String> input = List.of(
            "Machine {5, 6} (0, 1) (1)"
        );
        
        Day10PartBSolver solver = new Day10PartBSolver(createReader(input));
        assertEquals(6L, solver.solve());
    }

    @Test
    public void testPartBImpossible() throws IOException {
        List<String> input = List.of(
            "Machine {10} (1)"
        );
        
        Day10PartBSolver solver = new Day10PartBSolver(createReader(input));
        assertEquals(0L, solver.solve());
    }
}
