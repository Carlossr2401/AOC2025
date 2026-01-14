package software.aoc.day09.b;

import org.junit.Test;
import software.aoc.day09.Position;
import software.aoc.day09.PositionList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Day09PartBSolverTest {
    @Test
    public void testExample() {
        List<Position> points = new ArrayList<>();
        points.add(new Position(7,1));
        points.add(new Position(11,1));
        points.add(new Position(11,7));
        points.add(new Position(9,7));
        points.add(new Position(9,5));
        points.add(new Position(2,5));
        points.add(new Position(2,3));
        points.add(new Position(7,3));

        PositionList posList = new PositionList(points);
        Day09PartBSolver solver = new Day09PartBSolver(posList);
        
        long result = solver.solve();
        assertEquals(24, result);
    }
}
