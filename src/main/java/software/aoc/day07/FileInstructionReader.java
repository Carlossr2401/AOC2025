package software.aoc.day07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record FileInstructionReader(String filePath) implements InstructionReader {

    public BeanMap readAllData() throws IOException {

        List<List<String>> Map = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Map.add(createList(line));
            }
        }

        return new BeanMap(Map);
    }

    private List<String> createList(String line) {
        return line.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());
    }
}
