package software.aoc.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MatrixProcessor implements LinearSolver {
    private static final double EPSILON = 1e-9;

    @Override
    public Optional<long[]> solve(double[][] A, double[] t, long[] bounds) {
        int rows = A.length;
        int cols = A[0].length;

        // Gaussian elimination to RREF
        int pivotRow = 0;
        int[] pivotColForRow = new int[rows];
        Arrays.fill(pivotColForRow, -1);
        boolean[] isPivotCol = new boolean[cols];

        for (int col = 0; col < cols && pivotRow < rows; col++) {
            // Find pivot
            int sel = -1;
            for (int row = pivotRow; row < rows; row++) {
                if (Math.abs(A[row][col]) > EPSILON) {
                    sel = row;
                    break;
                }
            }
            if (sel == -1) continue;

            // Swap rows
            swapRows(A, t, pivotRow, sel);

            // Normalize pivot row
            normalizeRow(A, t, pivotRow, col, cols);

            // Eliminate other rows
            eliminateRows(A, t, pivotRow, col, rows, cols);

            pivotColForRow[pivotRow] = col;
            isPivotCol[col] = true;
            pivotRow++;
        }

        // Check for inconsistencies (0 = non-zero)
        for (int row = pivotRow; row < rows; row++) {
            if (Math.abs(t[row]) > EPSILON) return Optional.empty();
        }

        // Identify free variables
        List<Integer> freeVars = new ArrayList<>();
        for (int c = 0; c < cols; c++) {
            if (!isPivotCol[c]) freeVars.add(c);
        }

        // Iterate free variables to find best solution
        long[] bestSol = search(0, freeVars, new long[cols], A, t, pivotColForRow, bounds);
        return Optional.ofNullable(bestSol);
    }

    private void swapRows(double[][] A, double[] t, int row1, int row2) {
        double[] tempRow = A[row1];
        A[row1] = A[row2];
        A[row2] = tempRow;
        double tempT = t[row1];
        t[row1] = t[row2];
        t[row2] = tempT;
    }

    private void normalizeRow(double[][] A, double[] t, int row, int col, int cols) {
        double pivotVal = A[row][col];
        for (int c = col; c < cols; c++) {
            A[row][c] /= pivotVal;
        }
        t[row] /= pivotVal;
    }

    private void eliminateRows(double[][] A, double[] t, int pivotRow, int pivotCol, int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            if (row != pivotRow) {
                double factor = A[row][pivotCol];
                for (int c = pivotCol; c < cols; c++) {
                    A[row][c] -= factor * A[pivotRow][c];
                }
                t[row] -= factor * t[pivotRow];
            }
        }
    }

    private long[] search(int freeVarIndex, List<Integer> freeVars, long[] solution, 
                          double[][] RREF, double[] rhs, int[] pivotColForRow, long[] bounds) {
        if (freeVarIndex == freeVars.size()) {
            // Calculate pivot variables based on free variables
            long[] currentSol = Arrays.copyOf(solution, solution.length);
            for (int r = 0; r < pivotColForRow.length; r++) {
                int pCol = pivotColForRow[r];
                if (pCol == -1) continue;

                double val = rhs[r];
                for (int fIdx : freeVars) {
                    val -= RREF[r][fIdx] * currentSol[fIdx];
                }

                // Check integer and non-negative
                long rounded = Math.round(val);
                if (Math.abs(rounded - val) > EPSILON && Math.abs(rounded - val) < 1.0 - EPSILON) return null; // Not integer
                if (rounded < 0) return null;

                currentSol[pCol] = rounded;
            }
            return currentSol;
        }

        int fCol = freeVars.get(freeVarIndex);
        long[] bestSolution = null;
        long minCost = Long.MAX_VALUE;

        // Iterate range for this free variable
        for (long val = 0; val <= bounds[fCol]; val++) {
            solution[fCol] = val;
            long[] res = search(freeVarIndex + 1, freeVars, solution, RREF, rhs, pivotColForRow, bounds);
            
            if (res != null) {
                long currentCost = 0;
                for(long v : res) currentCost += v;
                
                if (bestSolution == null || currentCost < minCost) {
                    minCost = currentCost;
                    bestSolution = res;
                }
            }
        }
        return bestSolution;
    }
}
