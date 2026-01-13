package software.aoc.day08;

public class ReaderFactory {
    public static InstructionReader<JunctionBoxList> createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
