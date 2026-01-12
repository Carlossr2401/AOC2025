package software.aoc.day03;

import java.io.IOException;
import java.util.List;

public interface InputReader {
    List<List<Integer>> readAllVoltages() throws IOException;
}
