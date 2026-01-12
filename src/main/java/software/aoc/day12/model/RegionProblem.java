package software.aoc.day12.model;

import java.util.Map;

/**
 * Holds the definition of a specific region problem: dimensions and required present quantities.
 */
public record RegionProblem(int width, int length, Map<Integer, Integer> requiredShapeCounts) {
}
