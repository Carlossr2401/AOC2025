package software.aoc.day03.a;

import software.aoc.day03.InputReader;
import software.aoc.day03.Solver;
import software.aoc.day03.VoltageProcessor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class SolverA implements Solver {

    private final InputReader reader;
    private final VoltageProcessor calculator;

    public SolverA(InputReader reader) {
        this.reader = reader;
        this.calculator = new VoltageCalculator(new MaxFinder());
    }

    @Override
    public long solve() throws IOException {
        List<List<Integer>> allVoltageSets = reader.readAllVoltages();
        AtomicLong grandTotal = new AtomicLong(0);

        for (List<Integer> voltageSet : allVoltageSets) {
            long calculatedVoltage = calculator.calculateHighestVoltage(voltageSet);
            grandTotal.addAndGet(calculatedVoltage);
        }

        return grandTotal.get();
    }
}
