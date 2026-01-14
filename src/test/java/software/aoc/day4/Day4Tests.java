package software.aoc.day4;

import org.junit.Test;
import software.aoc.day04.Coordinate;
import software.aoc.day04.PaperRollMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Day4Tests {

    private PaperRollMap createMap(String... rows) {
        List<List<String>> grid = new ArrayList<>();
        for (String row : rows) {
            List<String> rowList = new ArrayList<>(Arrays.asList(row.split("")));
            grid.add(rowList);
        }
        return new PaperRollMap(grid);
    }

    @Test
    public void testMapCreationAndDimensions() {
        PaperRollMap map = createMap(
            "ABC",
            "DEF"
        );
        assertEquals("Should have 2 rows", 2, map.getRows());
        assertEquals("Should have 3 columns", 3, map.getCols());
    }

    @Test
    public void testGetValue() {
        PaperRollMap map = createMap(
            "ABC",
            "DEF"
        );
        assertEquals("A", map.getValue(0, 0));
        assertEquals("E", map.getValue(1, 1));
        assertEquals("F", map.getValue(1, 2));
    }

    @Test
    public void testUpdateMapImmutability() {
        PaperRollMap originalMap = createMap(
            "ABC",
            "DEF"
        );
        
        List<Coordinate> toUpdate = List.of(new Coordinate(0, 0));
        PaperRollMap newMap = originalMap.updateMap(toUpdate, "X");

        assertEquals("X", newMap.getValue(0, 0));
        
        assertEquals("A", originalMap.getValue(0, 0));
    }

    @Test
    public void testUpdateMultipleCoordinates() {
        PaperRollMap map = createMap(
            "123",
            "456",
            "789"
        );
        
        List<Coordinate> toUpdate = List.of(
            new Coordinate(0, 0),
            new Coordinate(1, 1),
            new Coordinate(2, 2)
        );
        
        PaperRollMap newMap = map.updateMap(toUpdate, "X");

        assertEquals("X", newMap.getValue(0, 0));
        assertEquals("X", newMap.getValue(1, 1));
        assertEquals("X", newMap.getValue(2, 2));
        
        assertEquals("2", newMap.getValue(0, 1));
    }
}
