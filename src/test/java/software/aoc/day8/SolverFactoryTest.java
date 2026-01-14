package software.aoc.day8;

import org.junit.Before;
import org.junit.Test;
import software.aoc.day8.a.Day08PartASolver;
import software.aoc.day8.b.Day08PartBSolver;

import static org.assertj.core.api.Assertions.assertThat;

public class SolverFactoryTest {

    private SolverFactory factory;

    @Before
    public void setUp() {
        factory = new SolverFactory();
    }

    @Test
    public void testCreatePartASolver() {
        Solver solver = factory.createPartASolver("dummy/path");
        assertThat(solver).isInstanceOf(Day08PartASolver.class);
    }

    @Test
    public void testCreatePartBSolver() {
        Solver solver = factory.createPartBSolver("dummy/path");
        assertThat(solver).isInstanceOf(Day08PartBSolver.class);
    }
}
