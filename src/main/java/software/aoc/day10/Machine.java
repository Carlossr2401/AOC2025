package software.aoc.day10;

import java.util.List;



public record Machine<T>(T configuration, List<Button> buttons) {
}
