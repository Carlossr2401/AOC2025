package software.aoc.day07.b;

import java.util.ArrayList;
import java.util.List;

public record CountMap(List<List<Long>> map) {

    public int size() {
        return map.size();
    }

    public long getCount(int row, int col) {
        if (row >= 0 && row < map.size()) {
            List<Long> rowList = map.get(row);
            if (col >= 0 && col < rowList.size()) {
                return rowList.get(col);
            }
        }
        return 0L;
    }

    public CountMap modifyValue(int rowIndex, int colIndex, long newValue) {
        List<List<Long>> newMap = new ArrayList<>(this.map.size());

        for (int i = 0; i < this.map.size(); i++) {
            List<Long> currentRow = this.map.get(i);

            if (i == rowIndex) {
                List<Long> newRow = new ArrayList<>(currentRow);
                if (colIndex >= 0 && colIndex < newRow.size()) {
                    newRow.set(colIndex, newValue);
                }
                newMap.add(newRow);
            } else {
                newMap.add(currentRow);
            }
        }
        return new CountMap(newMap);
    }

    public long sumLastRowPaths() {
        if (map.isEmpty()) return 0L;

        List<Long> lastRow = map.get(map.size() - 1);

        return lastRow.stream().mapToLong(Long::longValue).sum();
    }
}
