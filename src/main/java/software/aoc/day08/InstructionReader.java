package software.aoc.day08;

import java.io.IOException;

public interface InstructionReader<T> {
    T readAllData() throws IOException;
}
