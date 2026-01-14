package software.aoc.day03.a;

import org.junit.Before;
import org.junit.Test;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class VoltageCalculatorTest {

    private VoltageCalculator calculator;

    @Before
    public void setUp() {
        calculator = new VoltageCalculator(new MaxFinder());
    }

    @Test
    public void calculate_ShouldCombineTenthAndUnit() {
        // Given
        // 8 is max tenth at index 0. Remaining: [5, 2]. Max unit: 5. Result: 85
        List<Integer> inputs = List.of(8, 5, 2);

        // When
        long result = calculator.calculateHighestVoltage(inputs);

        // Then
        assertThat(result).isEqualTo(85);
    }
    
    @Test
    public void calculate_ShouldHandleFirstIndexPrecedence() {
        // Given: [8, 9, 7, 9, 4]. First 9 at index 1. Remaining [7, 9, 4]. Max unit 9. Result 99.
        List<Integer> inputs = List.of(8, 9, 7, 9, 4);
        assertThat(calculator.calculateHighestVoltage(inputs)).isEqualTo(99);
    }

    @Test
    public void calculate_ShouldHandleUnitFromRemainingList() {
        // Given: [2, 5, 8, 1, 9, 4]. 
        // Logic: Max tenth is 9 (index 4). Remaining [4]. Result 94.
        // (Note: Previous legacy test expected 89, which is incorrect as 94 > 89)
        List<Integer> inputs = List.of(2, 5, 8, 1, 9, 4);
        assertThat(calculator.calculateHighestVoltage(inputs)).isEqualTo(94);
    }

    @Test
    public void calculate_ShouldHandleLargeValues() {
        // [90, 95, 80, 99] -> Max tenth 95 (idx 1) -> remaining [80, 99] -> Max unit 99 -> 9599?
        // Wait, if input is Integers, 90, 95...
        // Logic: 95 is tenth. 95 * 10 = 950. Unit 99. Result 950 + 99 = 1049.
        // Legacy test said: 90, 95, 80, 99. Result 990 ???
        // Legacy code: calculateHighestVoltage(List<Integer>).
        // If inputs are "digits", they are usually 0-9?
        // But the code uses `Integer`. If inputs are 90, 95...
        // MaxFinder finds "max value". Max of [90, 95, 80, 99] is 99 (idx 3).
        // Remaining: [] -> unit 0. Result: 99 * 10 = 990.
        // Legacy test expected 990. Correct.
        List<Integer> inputs = List.of(90, 95, 80, 99);
        assertThat(calculator.calculateHighestVoltage(inputs)).isEqualTo(990);
    }

    @Test
    public void calculate_ShouldHandleSingleElement() {
        // Given
        // 5 is max tenth. Remaining: []. Unit: 0. Result: 50
        List<Integer> inputs = List.of(5);

        // When
        long result = calculator.calculateHighestVoltage(inputs);

        // Then
        assertThat(result).isEqualTo(50);
    }
    
    @Test
    public void calculate_ShouldHandleLastElementMax() {
        // Given
        // 9 is max tenth at last index. Remaining: []. Unit: 0. Result: 90
        List<Integer> inputs = List.of(1, 2, 9);
        
        // When
        long result = calculator.calculateHighestVoltage(inputs);
        
        // Then
        assertThat(result).isEqualTo(90);
    }
}
