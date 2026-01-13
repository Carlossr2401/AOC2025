package software.aoc.day04.a;

import software.aoc.day04.InstructionReader;
import software.aoc.day04.PaperRollMap;
import software.aoc.day04.Solver;

public class Part1Solver implements Solver {

    private final InstructionReader<PaperRollMap> reader;
    private static final String ROLL_SYMBOL = "@";
    private static final int MAX_NEIGHBORS = 4;

    public Part1Solver(InstructionReader<PaperRollMap> reader) {
        this.reader = reader;
    }

    @Override
    public int solve() throws java.io.IOException {
        PaperRollMap rollMap = reader.readAllLines();
        int accessibleRolls = 0;
        int rowCount = rollMap.getRows();
        int colCount = rollMap.getCols();

        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (isRoll(rollMap, row, col) && isAccessible(rollMap, row, col)) {
                    accessibleRolls++;
                }
            }
        }
        return accessibleRolls;
    }

    private boolean isRoll(PaperRollMap rollMap, int row, int col) {
        return rollMap.getValue(row, col).equals(ROLL_SYMBOL);
    }

    private boolean isAccessible(PaperRollMap rollMap, int row, int col) {
        return countAdjacentRolls(rollMap, row, col) < MAX_NEIGHBORS;
    }

    private int countAdjacentRolls(PaperRollMap rollMap, int row, int col) {
        int rollCount = 0;

        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                if (rowOffset == 0 && colOffset == 0) continue;

                int neighborRow = row + rowOffset;
                int neighborCol = col + colOffset;

                if (isValidPosition(rollMap, neighborRow, neighborCol) && isRoll(rollMap, neighborRow, neighborCol)) {
                    rollCount++;
                }
            }
        }
        return rollCount;
    }

    private boolean isValidPosition(PaperRollMap rollMap, int row, int col) {
        return row >= 0 && row < rollMap.getRows() && col >= 0 && col < rollMap.getCols();
    }
}
