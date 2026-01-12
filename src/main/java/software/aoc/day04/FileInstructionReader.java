package software.aoc.day04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record FileInstructionReader(String filePath) implements InstructionReader<PaperRollMap> {

    @Override
    public PaperRollMap readAllLines() throws IOException {

        List<List<String>> wholeMatrix = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                wholeMatrix.add(parseLine(line));
            }
        }

        return new PaperRollMap(wholeMatrix);
    }

    private List<String> parseLine(String line) {
        return line.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
    }
}
