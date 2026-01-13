package software.aoc.day12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileInstructionReader implements InstructionReader {
    
    private final String filePath;

    public FileInstructionReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<String> readInput() throws IOException {
        List<String> lines = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IOException("File not found in resources: " + filePath);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
