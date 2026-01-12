package software.aoc.day07;

public interface BeanProcessor {
    void onStart(int row, int col, BeanMap map, BeanSearcher searcher);
    void processSplit(int row, int col, BeanMap map, BeanSearcher searcher);
    void processStraight(int row, int col, BeanMap map, BeanSearcher searcher);
    Object getResult();
}
