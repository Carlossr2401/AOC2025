package software.aoc.day07;

import software.aoc.day07.a.Day07ASolver;
import software.aoc.day07.b.Day07BSolver;

public class SolverFactory {
    public Solver createSolver(String type, String path) {
        ReaderFactory readerFactory = new ReaderFactory();
        InstructionReader reader = readerFactory.createFileReader(path);

        if ("A".equalsIgnoreCase(type)) {
            return new Day07ASolver(reader);
        } else if ("B".equalsIgnoreCase(type)) {
            return new Day07BSolver(reader);
        } else {
            throw new IllegalArgumentException("Unknown solver type: " + type);
        }
    }
}
