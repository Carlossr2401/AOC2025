package software.aoc.day03;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src/main/java/software/aoc/day03/resources/input.txt";
        InputReader reader = ReaderFactory.createReader(filePath);

        Solver solverA = SolverFactory.createSolver(SolverFactory.SolverType.PART_A, reader);
        System.out.println("Suma total de IDs inválidos (Parte 1): " + solverA.solve());

        Solver solverB = SolverFactory.createSolver(SolverFactory.SolverType.PART_B, reader);
        System.out.println("Suma total de IDs inválidos (Parte 2): " + solverB.solve());    
    }
}