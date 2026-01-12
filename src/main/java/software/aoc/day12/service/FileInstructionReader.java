package software.aoc.day12.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for reading instructions/input from a file.
 * Single Responsibility: File I/O.
 */
public class FileInstructionReader {

    /**
     * Reads all lines from a file located in the resources folder.
     *
     * @param fileName The name of the file (e.g., "input.txt").
     * @return A list of strings, each representing a line in the file.
     * @throws IOException If the file cannot be read or found.
     */
    public List<String> readFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("File not found in resources: " + fileName);
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
