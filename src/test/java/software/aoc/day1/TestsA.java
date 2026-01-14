import org.junit.Test;
import software.aoc.day01.a.Dial;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestsA {

    private ArrayList<String> createMovements(String... moves) {
        return new ArrayList<>(List.of(moves));
    }

    @Test
    public void testZeroCountSingleHit() {
        ArrayList<String> movements = createMovements("R50");
        Dial dial = new Dial(movements);

        assertEquals(1, dial.getZeros());
    }

    @Test
    public void testZeroCountMultipleHits() {
        ArrayList<String> movements = createMovements("L50", "R50", "L50", "L1", "R1");
        Dial dial = new Dial(movements);

        assertEquals(3, dial.getZeros());
    }

    @Test
    public void testZeroCountNoHits() {
        ArrayList<String> movements = createMovements("L49", "R98");
        Dial dial = new Dial(movements);

        assertEquals(0, dial.getZeros());
    }

    @Test
    public void testEfficiencySingleCalculation() {
        ArrayList<String> movements = createMovements("L50");
        Dial dial = new Dial(movements);

        int firstCount = dial.getZeros();
        int secondCount = dial.getZeros();

        assertEquals(1, firstCount);
        assertEquals(firstCount, secondCount);
    }

    @Test
    public void testCountZeros() {
        ArrayList<String> movements = createMovements("L250", "R550", "L223", "R131", "L411");
        Dial dial = new Dial(movements);

        assertEquals(1, dial.getZeros());
    }
}