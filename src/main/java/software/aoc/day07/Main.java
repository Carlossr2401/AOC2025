package software.aoc.day07;

public class Main {
    public static void main(String[] args) {
        SolverFactory factory = new SolverFactory();

        System.out.println("--- Part A ---");
        Solver solverA = factory.createSolver("A", "src/main/java/software/aoc/day07/resources/input");
        System.out.println(solverA.solve());

        System.out.println("--- Part B ---");
        Solver solverB = factory.createSolver("B", "src/main/java/software/aoc/day07/resources/input");
        System.out.println(solverB.solve());
    }
}
