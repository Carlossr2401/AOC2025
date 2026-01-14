package software.aoc.day03.b;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxFinderTest {

    private final MaxFinder maxFinder = new MaxFinder();

    @Test
    public void findMaxSubsequence_ShouldReturnCorrect12DigitValue() {
        // Given
        // Need a list longer than 12. Let's make it 13.
        List<Integer> inputs = new ArrayList<>();
        // 987654321098 7 (13 digits)
        // Window 1 (first 12): 987654321098 -> max 9 (index 0)
        // ... greedy logic tests
        
        // Let's use a simple known case from logic logic or simple construction
        // If we have [1, 1, ..., 1] (12 times), result is 111111111111
        for (int i=0; i<12; i++) inputs.add(1);
        
        // When
        long result = maxFinder.findMaxSubsequence(inputs);

        // Then
        assertThat(result).isEqualTo(111111111111L);
    }
    
    @Test
    public void findMaxSubsequence_ShouldPickGreedyMax() {
        // Given [9, 1, 1, ..., 1] (13 elements: 9 + 12 ones)
        // It must pick 9 first if possible.
        List<Integer> inputs = new ArrayList<>();
        inputs.add(9);
        for(int i=0; i<12; i++) inputs.add(1); 
        // Total 13 items.
        // It should pick 9... then 11 ones.
        
        long result = maxFinder.findMaxSubsequence(inputs);
        
        assertThat(String.valueOf(result)).startsWith("9111");
        assertThat(String.valueOf(result).length()).isEqualTo(12);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findMaxSubsequence_ShouldThrow_WhenListTooShort() {
        List<Integer> inputs = List.of(1, 2, 3);
        maxFinder.findMaxSubsequence(inputs);
    }
}
