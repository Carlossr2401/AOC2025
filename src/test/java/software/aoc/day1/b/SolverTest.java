package software.aoc.day01.b;

import org.junit.Test;
import java.io.IOException;
import java.util.List;
import software.aoc.day01.InstructionReader;
import static org.junit.Assert.assertEquals;

public class SolverTest {

    @Test
    public void testIntermediateZeros() throws IOException {
        // GIVEN: Empezamos en 50.
        // L51: Va de 50 hacia atrás... pasa por el 0 (en el paso 50) ... y termina en 99.
        // Para la Parte B, cuenta CADA vez que toca el 0.
        InstructionReader reader = () -> List.of("L51");
        SolverB solver = new SolverB(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: Debe haber pasado por el 0 una vez.
        assertEquals(1, result);
    }

    @Test
    public void testMultiplePassesThroughZero() throws IOException {
        // GIVEN: Empezamos en 50.
        // R250:
        // - 50 pasos para llegar a 100/0 (1er cero)
        // - 100 pasos más para volver a 0 (2do cero)
        // - 100 pasos más para volver a 0 (3er cero)
        // Total 250 pasos.
        InstructionReader reader = () -> List.of("R250");
        SolverB solver = new SolverB(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: Debe haber pasado por 0 tres veces.
        assertEquals(3, result);
    }

    @Test
    public void testZeroAtStartOfMovementDoesNotCountUnlessVisited() throws IOException {
        // GIVEN: Empezamos en 50.
        // L50 -> Llega a 0 (cuenta 1). Estado actual = 0.
        // R10 -> Se mueve 0 -> 1 -> ... -> 10.
        // El ejercicio dice "número de veces que el dial apunta al 0... durante la rotación".
        // Si arrancamos de 0 y nos movemos 1 a la derecha, ¿cuenta el 0 inicial?
        // Interpretación usual: "durante la rotación" implica los pasos 1..N.
        // Si el estado YA es 0, y nos movemos, el primer paso nos aleja (o acerca si es 360).
        // Vamos a asumir que cuenta si ATERRIZA en 0.
        
        InstructionReader reader = () -> List.of("L50", "R10");
        SolverB solver = new SolverB(reader);

        // WHEN: Resolvemos
        int result = solver.solve();

        // THEN: L50 aterriza en 0 (1 visita). R10 se aleja inmediatamente. Total 1.
        assertEquals(1, result);
    }
    
    @Test
    public void testLandingExactlyOnZero() throws IOException {
       // GIVEN: Empezamos en 50. L50 -> llega a 0.
       InstructionReader reader = () -> List.of("L50");
       SolverB solver = new SolverB(reader);
       
       // WHEN: Resolvemos
       int result = solver.solve();
       
       // THEN: 1
       assertEquals(1, result);
    }
}
