package software.aoc.day11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record FileInstructionReader(String filePath) {

    public Graph readGraph() throws IOException {
        List<String> lines = Files.readAllLines(Path.of(filePath));
        Map<String, List<String>> adjacencyList = new HashMap<>();

        for (String line : lines) {
            parseLine(line, adjacencyList);
        }

        return new Graph(adjacencyList);
    }

    private void parseLine(String line, Map<String, List<String>> adjacencyList) {
        // Example format: "aaa: you hhh"
        // Split by ": "
        String[] parts = line.split(": ");
        if (parts.length != 2) {
             return; // Or throw exception
        }

        String source = parts[0];
        String targetsStr = parts[1];
        
        // Targets are space separated
        List<String> targets = Arrays.asList(targetsStr.split(" "));
        
        adjacencyList.put(source, targets);
    }
}
