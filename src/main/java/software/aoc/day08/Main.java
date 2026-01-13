package software.aoc.day08;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = "src/main/java/software/aoc/day08/resources/input";
        InstructionReader<JunctionBoxList> reader = ReaderFactory.createFileReader(path);
        
        System.out.println("Part A Solution:");
        Solver solverA = SolverFactory.createSolver("A", reader);
        System.out.println(solverA.solve());

        System.out.println("Part B Solution:");
        Solver solverB = SolverFactory.createSolver("B", reader);
        System.out.println(solverB.solve());
    }
}
