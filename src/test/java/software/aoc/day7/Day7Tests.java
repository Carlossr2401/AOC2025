package software.aoc.day7;

import org.junit.Test;
import software.aoc.day07.BeanMap;
import software.aoc.day07.InstructionReader;
import software.aoc.day07.a.Day07ASolver;
import software.aoc.day07.b.Day07BSolver;
import software.aoc.day07.b.CountMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class Day7Tests {

    private BeanMap createMap(String... rows) {
        List<List<String>> map = new ArrayList<>();
        for (String row : rows) {
            List<String> rowList = Arrays.stream(row.split(""))
                .collect(Collectors.toList());
            map.add(new ArrayList<>(rowList));
        }
        return new BeanMap(map);
    }

    private InstructionReader createReader(BeanMap map) {
        return () -> map;
    }

    @Test
    public void testPartANoSplits() throws IOException {
        BeanMap map = createMap(
            " S ",
            " . ",
            " . ",
            " . "
        );
        
        Day07ASolver solver = new Day07ASolver(createReader(map));
        assertEquals(0, solver.solve());
    }

    @Test
    public void testPartASingleSplit() throws IOException {
        BeanMap map = createMap(
            " S ",
            " . ",
            " ^ ",
            " . "
        );
        
        Day07ASolver solver = new Day07ASolver(createReader(map));
        assertEquals(1, solver.solve());
    }

    @Test
    public void testPartAMultipleSplits() throws IOException {
        BeanMap map = createMap(
            "  S  ",
            "  .  ",
            "  ^  ",
            " . . ",
            " ^ ^ "
        );
        
        Day07ASolver solver = new Day07ASolver(createReader(map));
        assertEquals(3, solver.solve());
    }

    @Test
    public void testPartBSinglePath() throws IOException {
        BeanMap map = createMap(
            " S ",
            " . ",
            " . ",
            " . "
        );
        
        Day07BSolver solver = new Day07BSolver(createReader(map));
        assertEquals(1L, solver.solve());
    }

    @Test
    public void testPartBSplitandMerge() throws IOException {
        BeanMap map = createMap(
            " S ",
            " . ",
            " ^ ",
            " . "
        );
        
        Day07BSolver solver = new Day07BSolver(createReader(map));
        assertEquals(2L, solver.solve());
    }
}
