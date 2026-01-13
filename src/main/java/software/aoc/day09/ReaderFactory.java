package software.aoc.day09;

public class ReaderFactory {
    public static InstructionReader createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
