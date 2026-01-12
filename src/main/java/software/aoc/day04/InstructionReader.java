package software.aoc.day04;

import java.io.IOException;

public interface InstructionReader<T> {
    T readAllLines() throws IOException;
}
