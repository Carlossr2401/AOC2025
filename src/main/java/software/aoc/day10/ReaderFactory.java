package software.aoc.day10;


public class ReaderFactory {
    public static InstructionReader createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
