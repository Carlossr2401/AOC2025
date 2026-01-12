package software.aoc.day07.b;

import software.aoc.day07.BeanMap;
import software.aoc.day07.BeanProcessor;
import software.aoc.day07.BeanSearcher;

import java.util.ArrayList;
import java.util.List;

public class TimelineCountStrategy implements BeanProcessor {
    private CountMap pathCountMap;

    @Override
    public void onStart(int row, int col, BeanMap map, BeanSearcher searcher) {
        this.pathCountMap = initializeCountMap(map);
        searcher.setMap(map.modifyValue(row, col, "|"));
        this.pathCountMap = pathCountMap.modifyValue(row, col, 1L);
    }

    @Override
    public void processSplit(int rowIndex, int colIndex, BeanMap map, BeanSearcher searcher) {
        long pathsFromGuide = pathCountMap.getCount(rowIndex - 1, colIndex); // from row above
        
        BeanMap currentMap = searcher.getMap();
        
        if (colIndex > 0) {
            currentMap = currentMap.modifyValue(rowIndex, colIndex - 1, "|");
            long currentCount = pathCountMap.getCount(rowIndex, colIndex - 1);
            this.pathCountMap = pathCountMap.modifyValue(rowIndex, colIndex - 1, currentCount + pathsFromGuide);
        }

        if (colIndex < currentMap.getRow(rowIndex).size() - 1) {
            currentMap = currentMap.modifyValue(rowIndex, colIndex + 1, "|");
            long currentCount = pathCountMap.getCount(rowIndex, colIndex + 1);
            this.pathCountMap = pathCountMap.modifyValue(rowIndex, colIndex + 1, currentCount + pathsFromGuide);
        }
        
        searcher.setMap(currentMap);
    }

    @Override
    public void processStraight(int rowIndex, int colIndex, BeanMap map, BeanSearcher searcher) {
        long pathsFromGuide = pathCountMap.getCount(rowIndex - 1, colIndex);
        
        searcher.setMap(searcher.getMap().modifyValue(rowIndex, colIndex, "|"));

        long currentCount = pathCountMap.getCount(rowIndex, colIndex);
        this.pathCountMap = pathCountMap.modifyValue(rowIndex, colIndex, currentCount + pathsFromGuide);
    }

    @Override
    public Object getResult() {
        return pathCountMap.sumLastRowPaths();
    }

    private CountMap initializeCountMap(BeanMap markerMap) {
        List<List<String>> markerMatrix = markerMap.map();
        List<List<Long>> zeroMatrix = new ArrayList<>();

        for (List<String> row : markerMatrix) {
            List<Long> zeroRow = new ArrayList<>();
            for (int i = 0; i < row.size(); i++) {
                zeroRow.add(0L);
            }
            zeroMatrix.add(zeroRow);
        }
        return new CountMap(zeroMatrix);
    }
}
