package software.aoc.day11;

public class ReaderFactory {
    public static InstructionReader createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
