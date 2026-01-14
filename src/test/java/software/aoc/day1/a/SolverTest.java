package software.aoc.day01.a;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import software.aoc.day01.InstructionReader;
import static org.junit.Assert.assertEquals;

public class SolverTest {

    @Test
    public void testNoMovements() throws IOException {
        // GIVEN: Un lector que no devuelve instrucciones
        InstructionReader emptyReader = () -> List.of();
        SolverA solver = new SolverA(emptyReader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: El contador de ceros debe ser 0 (empieza en 50 y no se mueve)
        assertEquals(0, result);
    }

    @Test
    public void testSingleMovementToZero() throws IOException {
        // GIVEN: Empezamos en 50. L50 nos lleva a 0.
        InstructionReader reader = () -> List.of("L50");
        SolverA solver = new SolverA(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: Debe contar 1 vez el cero (al final del movimiento)
        assertEquals(1, result);
    }

    @Test
    public void testWrapAroundRight() throws IOException {
        // GIVEN: Empezamos en 50. 
        // R50 -> llega a 0 (100 % 100 = 0). 
        // R100 -> da una vuelta completa y vuelve a 0.
        InstructionReader reader = () -> List.of("R50", "R100");
        SolverA solver = new SolverA(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: Los dos movimientos terminan en 0. Total = 2.
        assertEquals(2, result);
    }

    @Test
    public void testWrapAroundLeft() throws IOException {
        // GIVEN: Empezamos en 50.
        // L50 -> llega a 0.
        // L100 -> da euna vuelta completa y vuelve a 0.
        InstructionReader reader = () -> List.of("L50", "L100");
        SolverA solver = new SolverA(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: Los dos movimientos terminan en 0. Total = 2.
        assertEquals(2, result);
    }

    @Test
    public void testMixedMovementsWithoutHittingZero() throws IOException {
        // GIVEN: Empezamos en 50.
        // R10 -> 60.
        // L20 -> 40.
        InstructionReader reader = () -> List.of("R10", "L20");
        SolverA solver = new SolverA(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: Ningún movimiento termina en 0.
        assertEquals(0, result);
    }
}
