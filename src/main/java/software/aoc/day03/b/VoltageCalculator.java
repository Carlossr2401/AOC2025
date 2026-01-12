package software.aoc.day03.b;

import software.aoc.day03.VoltageProcessor;

import java.util.List;

public class VoltageCalculator implements VoltageProcessor {
    
    private final MaxFinder maxFinder;

    public VoltageCalculator(MaxFinder maxFinder) {
        this.maxFinder = maxFinder;
    }

    @Override
    public long calculateHighestVoltage(List<Integer> voltages) {
        return maxFinder.findMaxSubsequence(voltages);
    }
}