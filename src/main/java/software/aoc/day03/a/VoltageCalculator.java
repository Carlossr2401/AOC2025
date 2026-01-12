package software.aoc.day03.a;

import software.aoc.day03.VoltageProcessor;

import java.util.List;
import java.util.NoSuchElementException;

public class VoltageCalculator implements VoltageProcessor {

    private final MaxFinder maxFinder;

    public VoltageCalculator(MaxFinder maxFinder) {
        this.maxFinder = maxFinder;
    }

    @Override
    public long calculateHighestVoltage(List<Integer> voltages) {

        MaxFinder.Result highestTenth = maxFinder.findHighestTenth(voltages);

        if (highestTenth.index() == -1) {
            throw new NoSuchElementException("Voltage list is empty or invalid.");
        }

        int subListStart = highestTenth.index() + 1;

        if (subListStart >= voltages.size()) {
            // Si no quedan unidades, la unidad es 0
            return (long) highestTenth.value() * 10;
        }

        List<Integer> remainingUnits = voltages.subList(subListStart, voltages.size());

        int highestUnit = maxFinder.findHighestUnit(remainingUnits);

        return (long) highestTenth.value() * 10 + highestUnit;
    }
}