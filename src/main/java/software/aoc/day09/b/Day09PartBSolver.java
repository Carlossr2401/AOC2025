package software.aoc.day09.b;

import software.aoc.day09.InstructionReader;
import software.aoc.day09.Position;
import software.aoc.day09.PositionList;
import software.aoc.day09.Rectangle;
import software.aoc.day09.Solver;

import java.util.List;

public class Day09PartBSolver implements Solver {

    private final InstructionReader reader;

    public Day09PartBSolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public Long solve() throws java.io.IOException {
        PositionList positions = reader.readAllData();
        long maxArea = 0;
        List<Position> reds = positions.positions();
        Polygon polygon = new Polygon(reds);

        int n = reds.size();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                Position a = reds.get(i);
                Position b = reds.get(j);
                Rectangle r = new Rectangle(a, b);

                if (r.area() == 0) continue;

                if (polygon.contains(r)) {
                    maxArea = Math.max(maxArea, r.area());
                }
            }
        }
        return maxArea;
    }
}
