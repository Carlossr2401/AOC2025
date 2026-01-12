package software.aoc.day07.a;

import software.aoc.day07.BeanMap;
import software.aoc.day07.BeanProcessor;
import software.aoc.day07.BeanSearcher;

public class SplitCountStrategy implements BeanProcessor {
    private int splits = 0;

    @Override
    public void onStart(int row, int col, BeanMap map, BeanSearcher searcher) {
         searcher.setMap(map.modifyValue(row, col, "|"));
    }

    @Override
    public void processSplit(int rowIndex, int colIndex, BeanMap map, BeanSearcher searcher) {
        BeanMap currentMap = searcher.getMap();
        if (colIndex > 0) {
            currentMap = currentMap.modifyValue(rowIndex, colIndex - 1, "|");
        }
        if (colIndex < currentMap.getRow(rowIndex).size() - 1) {
            currentMap = currentMap.modifyValue(rowIndex, colIndex + 1, "|");
        }
        searcher.setMap(currentMap);
        this.splits++;
    }

    @Override
    public void processStraight(int rowIndex, int colIndex, BeanMap map, BeanSearcher searcher) {
        searcher.setMap(searcher.getMap().modifyValue(rowIndex, colIndex, "|"));
    }

    @Override
    public Object getResult() {
        return splits;
    }
}
