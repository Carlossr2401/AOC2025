package software.aoc.day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record FileInstructionReader(String filePath) implements InstructionReader {

    @Override
    public List<String> readAllInstructions() throws IOException {
        List<String> instructions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    instructions.add(trimmedLine);
                }
            }
        }
        return instructions;
    }
}
