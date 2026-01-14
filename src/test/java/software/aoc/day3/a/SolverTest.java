package software.aoc.day03.a;

import org.junit.Test;
import software.aoc.day03.InputReader;
import software.aoc.day03.VoltageProcessor;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverTest {

    @Test
    public void solve_ShouldSumAllVoltages() throws IOException {
        // Given
        InputReader mockReader = new InputReader() {
            @Override
            public List<List<Integer>> readAllVoltages() {
                return List.of(
                    List.of(1, 2), // Mock voltage 1
                    List.of(3, 4)  // Mock voltage 2
                );
            }
        };

        VoltageProcessor mockProcessor = new VoltageProcessor() {
            @Override
            public long calculateHighestVoltage(List<Integer> voltages) {
                // Simple sum for testing orchestration
                return voltages.stream().mapToInt(Integer::intValue).sum();
            }
        };

        SolverA solver = new SolverA(mockReader, mockProcessor);

        // When
        long result = solver.solve();

        // Then
        // Set 1: 1+2 = 3
        // Set 2: 3+4 = 7
        // Total: 10
        assertThat(result).isEqualTo(10);
    }
}
