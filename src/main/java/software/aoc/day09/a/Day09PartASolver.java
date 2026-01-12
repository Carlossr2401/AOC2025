package software.aoc.day09.a;

import software.aoc.day09.Position;
import software.aoc.day09.PositionList;
import software.aoc.day09.Rectangle;
import software.aoc.day09.Solver;

public record Day09PartASolver(PositionList positions) implements Solver {

    @Override
    public Long solve() {

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
