package software.aoc.day10.b;

import software.aoc.day10.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Day10PartBSolver implements Solver {
    private final InstructionReader reader;
    private final LinearSolver linearSolver;

    public Day10PartBSolver(InstructionReader reader) {
        this.reader = reader;
        this.linearSolver = new MatrixProcessor();
    }

    @Override
    public Object solve() throws java.io.IOException {
        Machines<List<Integer>> machines = parseMachines(reader.readInput());
        long totalPresses = 0;
        int i = 0;
        for (Machine<List<Integer>> machine : machines) {
            // System.out.println("Solving machine " + (++i));
            Long minPresses = solveMachine(machine);
            if (minPresses != null) {
                totalPresses += minPresses;
            } else {
                System.err.println("No solution for machine " + (++i));
            }
        }
        return totalPresses;
    }

    private Machines<List<Integer>> parseMachines(List<String> lines) {
        List<Machine<List<Integer>>> list = new ArrayList<>();
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                list.add(MachineParser.parseForPartB(line));
            }
        }
        return new Machines<>(list);
    }

    private Long solveMachine(Machine<List<Integer>> machine) {
        int numCounters = machine.configuration().size();
        int numButtons = machine.buttons().size();

        // Build Matrix A (Rows: counters, Cols: buttons)
        double[][] A = new double[numCounters][numButtons];
        // Upper bounds for each button based on simple constraints
        long[] bounds = new long[numButtons];
        Arrays.fill(bounds, Long.MAX_VALUE);

        for (int j = 0; j < numButtons; j++) {
            Button b = machine.buttons().get(j);
            boolean affectsAny = false;
            for (int counterIdx : b.positions()) {
                if (counterIdx < numCounters) {
                    A[counterIdx][j] = 1.0;
                    long limit = machine.configuration().get(counterIdx);
                    bounds[j] = Math.min(bounds[j], limit);
                    affectsAny = true;
                }
            }
            if (!affectsAny) bounds[j] = 0; // Useless button
        }

        double[] t = new double[numCounters];
        for (int k = 0; k < numCounters; k++) {
            t[k] = machine.configuration().get(k);
        }

        return linearSolver.solve(A, t, bounds)
                .map(solution -> {
                    long cost = 0;
                    for (long s : solution) {
                        cost += s;
                    }
                    return cost;
                })
                .orElse(null);
    }
}
