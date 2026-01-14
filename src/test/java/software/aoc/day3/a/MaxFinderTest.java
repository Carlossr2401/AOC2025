package software.aoc.day03.a;

import org.junit.Test;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxFinderTest {

    private final MaxFinder maxFinder = new MaxFinder();

    @Test
    public void findHighestTenth_ShouldReturnMaxDigit_WhenListHasValues() {
        // Given
        List<Integer> inputs = List.of(3, 8, 1, 9, 2);

        // When
        MaxFinder.Result result = maxFinder.findHighestTenth(inputs);

        // Then
        assertThat(result.value()).isEqualTo(9);
        assertThat(result.index()).isEqualTo(3);
    }

    @Test
    public void findHighestTenth_ShouldReturnFirstMax_WhenDuplicatesExist() {
        // Given
        List<Integer> inputs = List.of(3, 8, 9, 1, 9, 2);

        // When
        MaxFinder.Result result = maxFinder.findHighestTenth(inputs);

        // Then
        assertThat(result.value()).isEqualTo(9);
        assertThat(result.index()).isEqualTo(2); // First 9
    }

    @Test
    public void findHighestTenth_ShouldReturnInvalid_WhenListIsEmpty() {
        // Given
        List<Integer> inputs = Collections.emptyList();

        // When
        MaxFinder.Result result = maxFinder.findHighestTenth(inputs);

        // Then
        assertThat(result.index()).isEqualTo(-1);
    }

    @Test
    public void findHighestUnit_ShouldReturnMax_WhenListHasValues() {
        // Given
        List<Integer> inputs = List.of(1, 5, 2);

        // When
        int result = maxFinder.findHighestUnit(inputs);

        // Then
        assertThat(result).isEqualTo(5);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void findHighestUnit_ShouldThrow_WhenListIsEmpty() {
        maxFinder.findHighestUnit(Collections.emptyList());
    }
}
