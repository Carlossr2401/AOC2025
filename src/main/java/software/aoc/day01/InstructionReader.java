package software.aoc.day01;

import java.io.IOException;
import java.util.List;

public interface InstructionReader {
    List<String> readAllInstructions() throws IOException;
}
