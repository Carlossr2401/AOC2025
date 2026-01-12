package software.aoc.day11;

public class TimedPathCounter implements PathCounter {
    private final PathCounter delegate;

    public TimedPathCounter(PathCounter delegate) {
        this.delegate = delegate;
    }

    @Override
    public long countPaths(Graph graph, String start, String end) {
        long startTime = System.nanoTime();
        long result = delegate.countPaths(graph, start, end);
        long endTime = System.nanoTime();
        
        System.out.printf("Path counting (%s -> %s) took %.4f ms%n", start, end, (endTime - startTime) / 1_000_000.0);
        return result;
    }
}
