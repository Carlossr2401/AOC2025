package software.aoc.day02.a;

import software.aoc.day02.IdValidator;
import software.aoc.day02.InstructionReader;
import software.aoc.day02.Solver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import software.aoc.day02.Range;

public class SolverA implements Solver {

    private final InstructionReader reader;
    private final IdValidator validator;

    public SolverA(InstructionReader reader, IdValidator validator) {
        this.reader = reader;
        this.validator = validator;
    }

    @Override
    public long solve() throws IOException {
        List<String> lista = reader.readAllInstructions();
        AtomicLong addition = new AtomicLong(0);

        lista.forEach(line -> {
            Range range = Range.fromString(line);
            long invalidCount = java.util.stream.LongStream.rangeClosed(range.start(), range.end())
                    .filter(validator::isValid)
                    .sum();
            addition.addAndGet(invalidCount);
        });

        return addition.get();
    }
}
