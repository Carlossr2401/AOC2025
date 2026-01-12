package software.aoc.day08;

import software.aoc.day08.a.Day08PartASolver;
import software.aoc.day08.b.Day08PartBSolver;

public class SolverFactory {

    private final ReaderFactory readerFactory;

    public SolverFactory() {
        this.readerFactory = new ReaderFactory();
    }

    public Solver createPartASolver(String path) {
        return new Day08PartASolver(readerFactory.createFileReader(path));
    }

    public Solver createPartBSolver(String path) {
        return new Day08PartBSolver(readerFactory.createFileReader(path));
    }
}
