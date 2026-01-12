package software.aoc.day08;

public class ReaderFactory {
    public InstructionReader<JunctionBoxList> createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
