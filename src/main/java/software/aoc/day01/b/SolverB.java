package software.aoc.day01.b;

import java.io.IOException;
import java.util.List;

import software.aoc.day01.InstructionReader;

public class SolverB implements software.aoc.day01.Solver {

    private final InstructionReader reader;

    public SolverB(InstructionReader reader) {
        this.reader = reader;
    }

    public int solve() throws IOException {
        List<String> instructions = reader.readAllInstructions();
        Dial dial = new Dial(instructions);
        return dial.getZeros();
    }
}
