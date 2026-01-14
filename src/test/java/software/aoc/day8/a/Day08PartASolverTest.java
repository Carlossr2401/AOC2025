package software.aoc.day8.a;

import org.junit.Test;
import software.aoc.day8.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class Day08PartASolverTest {

    @Test
    public void testSolveWithSmallCluster() {
        // Arrange
        // Create 3 clusters:
        // Cluster A: ID 1, 2 (Size 2)
        // Cluster B: ID 3, 4, 5 (Size 3)
        // Cluster C: ID 6 (Size 1)
        
        // Coordinates:
        // 1: (0,0,0), 2: (1,0,0) -> dist sq 1
        // 3: (100,0,0), 4: (101,0,0), 5: (100,1,0) -> dists within are small (~1-2)
        // 6: (1000,0,0) -> far from everyone
        
        Map<Integer, JunctionBox> map = new HashMap<>();
        map.put(1, new JunctionBox(1, 0, 0, 0, 1, 1));
        map.put(2, new JunctionBox(2, 1, 0, 0, 2, 1));
        
        map.put(3, new JunctionBox(3, 100, 0, 0, 3, 1));
        map.put(4, new JunctionBox(4, 101, 0, 0, 4, 1));
        map.put(5, new JunctionBox(5, 100, 1, 0, 5, 1));
        
        map.put(6, new JunctionBox(6, 1000, 0, 0, 6, 1));

        JunctionBoxList data = new JunctionBoxList(map);

        InstructionReader<JunctionBoxList> fakeReader = new InstructionReader<>() {
            @Override
            public JunctionBoxList readAllData() throws IOException {
                return data;
            }
        };

        Day08PartASolver solver = new Day08PartASolver(fakeReader);

        // Act
        // Logic: Will process connections small to large.
        // 1-2 will connect.
        // 3-4, 3-5, 4-5 will connect.
        // Distances between clusters are large (approx 100^2 or 900^2).
        // It will form 3 circuits: {1,2}, {3,4,5}, {6}.
        // Sizes: 2, 3, 1.
        // Sorted: 3, 2, 1.
        // Result: 3 * 2 * 1 = 6.
        Object result = solver.solve();

        // Assert
        assertThat(result).isEqualTo(6L);
    }
    
    @Test
    public void testSolveWithInsufficientCircuits() {
         // Arrange
        // Only 2 boxes far apart -> 2 circuits of size 1.
        Map<Integer, JunctionBox> map = new HashMap<>();
        map.put(1, new JunctionBox(1, 0, 0, 0, 1, 1));
        map.put(2, new JunctionBox(2, 100, 0, 0, 2, 1));

        JunctionBoxList data = new JunctionBoxList(map);

        InstructionReader<JunctionBoxList> fakeReader = new InstructionReader<>() {
            @Override
            public JunctionBoxList readAllData() throws IOException {
                return data;
            }
        };

        Day08PartASolver solver = new Day08PartASolver(fakeReader);
        
        // Act
        Object result = solver.solve();
        
        // Assert
        // Should return 0 because < 3 circuits found
        assertThat(result).isEqualTo(0L);
    }
}
