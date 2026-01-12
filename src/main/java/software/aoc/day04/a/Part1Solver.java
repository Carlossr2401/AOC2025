package software.aoc.day04.a;

import software.aoc.day04.PaperRollMap;
import software.aoc.day04.Solver;

public record Part1Solver(PaperRollMap rollMap) implements Solver {

    private static final String ROLL_SYMBOL = "@";
    private static final int MAX_NEIGHBORS = 4;

    @Override
    public int solve() {
        int accessibleRolls = 0;
        int rowCount = rollMap.getRows();
        int colCount = rollMap.getCols();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (isRoll(row, col) && isAccessible(row, col)) {
                    accessibleRolls++;
                }
            }
        }
        return accessibleRolls;
    }

    private boolean isRoll(int row, int col) {
        return rollMap.getValue(row, col).equals(ROLL_SYMBOL);
    }

    private boolean isAccessible(int row, int col) {
        return countAdjacentRolls(row, col) < MAX_NEIGHBORS;
    }

    private int countAdjacentRolls(int row, int col) {
        int rollCount = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) continue;

                int neighborRow = row + rowOffset;
                int neighborCol = col + colOffset;

                if (isValidPosition(neighborRow, neighborCol) && isRoll(neighborRow, neighborCol)) {
                    rollCount++;
                }
            }
        }
        return rollCount;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rollMap.getRows() && col >= 0 && col < rollMap.getCols();
    }
}
