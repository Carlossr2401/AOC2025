package software.aoc.day01;

public class ReaderFactory {
    public static InstructionReader createReader(String inputFile) {
        return new FileInstructionReader(inputFile);
    }
}
