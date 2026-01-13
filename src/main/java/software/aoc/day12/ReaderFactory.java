package software.aoc.day12;

public class ReaderFactory {
    public static InstructionReader createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
