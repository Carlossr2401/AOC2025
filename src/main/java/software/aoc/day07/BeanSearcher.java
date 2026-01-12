package software.aoc.day07;

import java.util.List;

public class BeanSearcher {

    private BeanMap map;
    private final BeanProcessor processor;

    public BeanSearcher(BeanMap map, BeanProcessor processor) {
        this.map = map;
        this.processor = processor;
    }

    public Object search() {
        searchStartPoint();

        for (int rowIndex = 2; rowIndex < this.map.size(); rowIndex++) {
            processRow(rowIndex);
        }
        return processor.getResult();
    }

    public BeanMap getMap() {
        return map;
    }

    public void setMap(BeanMap map) {
        this.map = map;
    }

    private void processRow(int rowIndex) {
        List<Integer> indices = map.findAllIndicesInRow(rowIndex - 1, "|");

        for (int colIndex : indices) {

            String actualChar = map.getChar(rowIndex, colIndex);

            if (actualChar.equals("^")) {
                processor.processSplit(rowIndex, colIndex, map, this);

            } else {
                processor.processStraight(rowIndex, colIndex, map, this);
            }
        }
    }

    private void searchStartPoint() {
        if (map.size() > 0) {
            int startCol = map.getRow(0).indexOf("S");
            if (map.size() > 1 && startCol != -1) {
                processor.onStart(1, startCol, map, this);
            }
        }
    }
}
