package software.aoc.day04;

public class ReaderFactory {
    public static InstructionReader<PaperRollMap> createReader(String filePath) {
        return new FileInstructionReader(filePath);
    }
}
