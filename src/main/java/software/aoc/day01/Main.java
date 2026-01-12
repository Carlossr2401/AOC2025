package software.aoc.day01;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InstructionReader reader = ReaderFactory.createReader("src/main/java/software/aoc/day01/resources/dialInput");
        Solver solver = SolverFactory.createSolver("A", reader);
        System.out.println(solver.solve());
    }
}
