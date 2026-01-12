package software.aoc.day09;

import java.util.Iterator;
import java.util.List;

public record PositionList(List<Position> positions) implements Iterable<Position> {

    @Override
    public Iterator<Position> iterator() {
        return positions.iterator();
    }
}
