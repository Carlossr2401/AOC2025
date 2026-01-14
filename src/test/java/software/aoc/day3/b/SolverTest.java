package software.aoc.day03.b;

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
                    List.of(1), // Mock voltage set 1
                    List.of(2)  // Mock voltage set 2
                );
            }
        };

        VoltageProcessor mockProcessor = new VoltageProcessor() {
            @Override
            public long calculateHighestVoltage(List<Integer> voltages) {
                // Mock calculation: return input val * 10
                return voltages.get(0) * 10L;
            }
        };

        SolverB solver = new SolverB(mockReader, mockProcessor);

        // When
        long result = solver.solve();

        // Then
        // Set 1: 1 * 10 = 10
        // Set 2: 2 * 10 = 20
        // Total: 30
        assertThat(result).isEqualTo(30L);
    }
}
