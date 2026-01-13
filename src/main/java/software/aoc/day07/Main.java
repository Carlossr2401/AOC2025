package software.aoc.day07;

public class Main {
    public static void main(String[] args) throws java.io.IOException {
        String path = "src/main/java/software/aoc/day07/resources/input";
        InstructionReader reader = ReaderFactory.createFileReader(path);

        System.out.println("--- Part A ---");
        Solver solverA = SolverFactory.createSolver("A", reader);
        System.out.println(solverA.solve());

        System.out.println("--- Part B ---");
        Solver solverB = SolverFactory.createSolver("B", reader);
        System.out.println(solverB.solve());
    }
}
