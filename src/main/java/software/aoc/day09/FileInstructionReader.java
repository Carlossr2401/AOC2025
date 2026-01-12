package software.aoc.day09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record FileInstructionReader(String filePath) implements InstructionReader {

    @Override
    public PositionList readAllData() throws IOException {

        List<Position> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentId = 0;

            while ((line = reader.readLine()) != null) {
                list.add(currentId, createPosition(line));
                currentId++;
            }
        }

        return new PositionList(list);
    }

    private Position createPosition(String line) {
        String[] position = line.split(",");

        long x = Long.parseLong(position[0].trim());
        long y = Long.parseLong(position[1].trim());

        return new Position(x, y);
    }
}
