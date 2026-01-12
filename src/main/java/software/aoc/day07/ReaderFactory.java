package software.aoc.day07;

public class ReaderFactory {
    public InstructionReader createFileReader(String path) {
        return new FileInstructionReader(path);
    }
}
