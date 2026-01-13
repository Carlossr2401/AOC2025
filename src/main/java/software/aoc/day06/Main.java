package software.aoc.day06;

public class Main {
    
    // Standard entry point
    public static void main(String[] args) throws java.io.IOException {
        // Defaulting to Part B as it's the advanced one, or running both?
        // Let's run both for demonstration.
        System.out.println("Running Day 6 Solutions...");
        
        String inputPath = "src/main/software/aoc/day06/resources/input";
        InstructionReader reader = ReaderFactory.createFileReader(inputPath);

        System.out.println("--- Part A ---");
        Solver solverA = SolverFactory.createSolver(SolverFactory.Part.A, reader);
        System.out.println("Result A: " + solverA.solve());

        System.out.println("\n--- Part B ---");
        Solver solverB = SolverFactory.createSolver(SolverFactory.Part.B, reader);
        System.out.println("Result B: " + solverB.solve());
    }
}
