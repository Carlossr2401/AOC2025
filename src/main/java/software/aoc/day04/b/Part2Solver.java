package software.aoc.day04.b;

import software.aoc.day04.Coordinate;
import software.aoc.day04.PaperRollMap;
import software.aoc.day04.Solver;

import java.util.ArrayList;
import java.util.List;

public record Part2Solver(PaperRollMap initialMap) implements Solver {

    private static final String ROLL_SYMBOL = "@";
    private static final String EMPTY_SYMBOL = ".";
    private static final int MAX_NEIGHBORS = 4;

    @Override
    public int solve() {
        int totalRemoved = 0;
        PaperRollMap currentMap = this.initialMap;

        while (true) {
            MapFinderResult iterationResult = findViableRollsInIteration(currentMap);
            int removedInThisStep = iterationResult.removedCount();

            if (removedInThisStep == 0) {
                break;
            }

            totalRemoved += removedInThisStep;
            currentMap = currentMap.updateMap(iterationResult.removableCoordinates(), EMPTY_SYMBOL);
        }

        return totalRemoved;
    }

    private MapFinderResult findViableRollsInIteration(PaperRollMap currentMap) {
        List<Coordinate> coordinatesToRemove = new ArrayList<>();
        int rowCount = currentMap.getRows();
        int colCount = currentMap.getCols();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (isRoll(currentMap, row, col) && isAccessible(currentMap, row, col)) {
                    coordinatesToRemove.add(new Coordinate(row, col));
                }
            }
        }
        return new MapFinderResult(coordinatesToRemove.size(), coordinatesToRemove);
    }

    private boolean isRoll(PaperRollMap map, int row, int col) {
        return map.getValue(row, col).equals(ROLL_SYMBOL);
    }

    private boolean isAccessible(PaperRollMap map, int row, int col) {
        return countAdjacentRolls(map, row, col) < MAX_NEIGHBORS;
    }

    private int countAdjacentRolls(PaperRollMap map, int row, int col) {
        int rollCount = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) continue;

                int neighborRow = row + rowOffset;
                int neighborCol = col + colOffset;

                if (isValidPosition(map, neighborRow, neighborCol) && isRoll(map, neighborRow, neighborCol)) {
                    rollCount++;
                }
            }
        }
        return rollCount;
    }

    private boolean isValidPosition(PaperRollMap map, int row, int col) {
        return row >= 0 && row < map.getRows() && col >= 0 && col < map.getCols();
    }

    private record MapFinderResult(int removedCount, List<Coordinate> removableCoordinates) {}
}
