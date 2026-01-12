package software.aoc.day09;

import software.aoc.day09.a.Day09PartASolver;
import software.aoc.day09.b.Day09PartBSolver;

import java.io.IOException;

public class SolverFactory {
    public Solver createSolver(String type, InstructionReader reader) throws IOException {
        PositionList data = reader.readAllData();
        return switch (type) {
            case "A" -> new Day09PartASolver(data);
            case "B" -> new Day09PartBSolver(data);
            default -> throw new IllegalArgumentException("Unknown solver type: " + type);
        };
    }
}
