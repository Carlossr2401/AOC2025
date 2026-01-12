package software.aoc.day04;

import java.util.ArrayList;
import java.util.List;

public record PaperRollMap(List<List<String>> grid) {

    public int getRows() {
        return grid.size();
    }

    public int getCols() {
        if (grid.isEmpty()) return 0;
        return grid.get(0).size();
    }

    public String getValue(int r, int c) {
        return grid.get(r).get(c);
    }

    public PaperRollMap updateMap(List<Coordinate> coordinatesToModify, String newValue) {
        List<List<String>> newGrid = new ArrayList<>();
        for (List<String> row : this.grid) {
            newGrid.add(new ArrayList<>(row));
        }

        for (Coordinate coord : coordinatesToModify) {
            newGrid.get(coord.row()).set(coord.col(), newValue);
        }

        return new PaperRollMap(newGrid);
    }
}
