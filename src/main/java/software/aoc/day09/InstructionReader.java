package software.aoc.day09;

import java.io.IOException;

public interface InstructionReader {
    PositionList readAllData() throws IOException;
}
