import org.junit.Test;
import software.aoc.day01.b.Dial;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestsB {

    private ArrayList<String> createMovements(String... moves) {
        return new ArrayList<>(List.of(moves));
    }

    @Test
    public void testPassThroughZero() {
        ArrayList<String> movements = createMovements("L51");
        Dial dial = new Dial(movements);

        assertEquals(1, dial.getZeros());
    }

    @Test
    public void testMultiplePassesThroughZero() {
        ArrayList<String> movements = createMovements("R150");
        Dial dial = new Dial(movements);

        assertEquals(2, dial.getZeros());
    }

    @Test
    public void testNoZeroInteraction() {
        ArrayList<String> movements = createMovements("R10", "L20");
        Dial dial = new Dial(movements);

        assertEquals(0, dial.getZeros());
    }

    @Test
    public void testExactZeroLanding() {
        ArrayList<String> movements = createMovements("L50");
        Dial dial = new Dial(movements);

        assertEquals(1, dial.getZeros());
    }
}
