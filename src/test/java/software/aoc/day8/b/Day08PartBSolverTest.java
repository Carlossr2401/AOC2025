package software.aoc.day8.b;

import org.junit.Test;
import software.aoc.day8.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Day08PartBSolverTest {

    @Test
    public void testSolvePartB() {
        // Arrange
        // Two boxes that will be united.
        Map<Integer, JunctionBox> map = new HashMap<>();
        map.put(1, new JunctionBox(1, 10, 0, 0, 1, 1));
        map.put(2, new JunctionBox(2, 20, 0, 0, 2, 1));

        JunctionBoxList data = new JunctionBoxList(map);

        InstructionReader<JunctionBoxList> fakeReader = new InstructionReader<>() {
            @Override
            public JunctionBoxList readAllData() throws IOException {
                return data;
            }
        };

        Day08PartBSolver solver = new Day08PartBSolver(fakeReader);

        // Act
        // Logic: Connects 1 and 2.
        // lastX1 = 10, lastX2 = 20.
        // Result = 200.
        Object result = solver.solve();

        // Assert
        assertThat(result).isEqualTo(200L);
    }
}
