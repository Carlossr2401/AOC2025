package software.aoc.day12;

import java.io.IOException;
import java.util.List;

public interface InstructionReader {
    List<String> readInput() throws IOException;
}
