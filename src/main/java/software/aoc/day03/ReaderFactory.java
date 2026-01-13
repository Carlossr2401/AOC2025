package software.aoc.day03;

public class ReaderFactory {
    public static InputReader createReader(String filePath) {
        return new FileInstructionReader(filePath);
    }
}
