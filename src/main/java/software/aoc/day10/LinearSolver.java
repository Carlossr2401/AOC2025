package software.aoc.day10;

import java.util.Optional;

public interface LinearSolver {
    /**
     * Solves a linear system Ax = b.
     * @param coefficients The matrix A.
     * @param results The vector b.
     * @return An Optional containing the solution vector if one exists, or empty otherwise.
     */
    Optional<long[]> solve(double[][] coefficients, double[] results, long[] bounds);
}
