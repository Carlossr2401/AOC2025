package software.aoc.day07.a;

import software.aoc.day07.BeanMap;
import software.aoc.day07.BeanSearcher;
import software.aoc.day07.InstructionReader;
import software.aoc.day07.Solver;

import java.io.IOException;

public class Day07ASolver implements Solver {
    private final InstructionReader reader;

    public Day07ASolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public Object solve() {
        try {
            BeanMap map = reader.readAllData();
            BeanSearcher searcher = new BeanSearcher(map, new SplitCountStrategy());
            return searcher.search();
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input data", e);
        }
    }
}
