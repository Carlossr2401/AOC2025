package software.aoc.day12.service;

import software.aoc.day12.model.RegionProblem;
import software.aoc.day12.model.Shape;

import java.util.List;

/**
 * Interface for parsing raw input lines into domain objects.
 * Dependencies are inverted: High-level modules verify against this interface.
 */
public interface InputParser {
    /**
     * Parses the raw lines to extract shapes.
     */
    List<Shape> parseShapes(List<String> lines);

    /**
     * Parses the raw lines to extract region problems.
     */
    List<RegionProblem> parseProblems(List<String> lines);
}
