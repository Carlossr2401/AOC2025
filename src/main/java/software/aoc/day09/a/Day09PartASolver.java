package software.aoc.day09.a;

import software.aoc.day09.InstructionReader;
import software.aoc.day09.Position;
import software.aoc.day09.PositionList;
import software.aoc.day09.Rectangle;
import software.aoc.day09.Solver;

public class Day09PartASolver implements Solver {

    private final InstructionReader reader;

    public Day09PartASolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public Long solve() throws java.io.IOException {
        PositionList positions = reader.readAllData();
        long maxArea = 0;

        for (Position a : positions) {
            for (Position b : positions) {

                if (a.equals(b)) continue;

                Rectangle r = new Rectangle(a, b);

                if (r.area() == 0) continue;
                maxArea = Math.max(maxArea, r.area());
            }
        }

        return maxArea;
    }
}
