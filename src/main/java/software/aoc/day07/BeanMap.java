package software.aoc.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record BeanMap(List<List<String>> map) {

    public int size() {
        return map.size();
    }

    public List<String> getRow(int row) {
        if (row >= 0 && row < map.size()) {
            return map.get(row);
        }
        return List.of();
    }

    public List<Integer> findAllIndicesInRow(int rowIndex, String targetValue) {

        List<String> row = getRow(rowIndex);
        if (row.isEmpty()) {
            return List.of();
        }

        return IntStream.range(0, row.size())
                .filter(colIndex -> row.get(colIndex).equals(targetValue))
                .boxed()
                .collect(Collectors.toList());
    }


    public BeanMap modifyValue(int rowIndex, int colIndex, String newValue) {
        List<List<String>> newMap = new ArrayList<>(this.map.size());
        for (int i = 0; i < this.map.size(); i++) {
            List<String> currentRow = this.map.get(i);
            if (i == rowIndex) {
                List<String> newRow = new ArrayList<>(currentRow);
                if (colIndex >= 0 && colIndex < newRow.size()) {
                    newRow.set(colIndex, newValue);
                }
                newMap.add(newRow);
            } else {
                newMap.add(currentRow);
            }
        }
        return new BeanMap(newMap);
    }

    public String getChar(int row, int col) {
        List<String> rowList = getRow(row);
        if (col >= 0 && col < rowList.size()) {
            return rowList.get(col);
        }
        return " ";
    }
}
