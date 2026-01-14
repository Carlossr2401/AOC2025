package software.aoc.day03.b;

import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class VoltageCalculatorTest {

    private VoltageCalculator calculator;
    private MaxFinder maxFinder;

    @Before
    public void setUp() {
        maxFinder = new MaxFinder();
        calculator = new VoltageCalculator(maxFinder);
    }

    @Test
    public void calculate_ShouldDelegateToMaxFinder() {
        // Given
        // Simple 12 ones case again
        List<Integer> inputs = new ArrayList<>();
        for (int i=0; i<12; i++) inputs.add(1);

        // When
        long result = calculator.calculateHighestVoltage(inputs);

        // Then
        assertThat(result).isEqualTo(111111111111L);
    }
}
