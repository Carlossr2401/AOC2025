package software.aoc.day10;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public record Machines<T>(List<Machine<T>> list) implements Iterable<Machine<T>> {

    @Override
    public Iterator<Machine<T>> iterator() {
        return list.iterator();
    }

    @Override
    public void forEach(Consumer<? super Machine<T>> action) {
        list.forEach(action);
    }
}