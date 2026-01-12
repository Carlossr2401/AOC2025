package software.aoc.day09;

public record Segment(Position start, Position end) {

    public boolean isVertical() {
        return start.x() == end.x() && start.y() != end.y();
    }

    public boolean isHorizontal() {
        return start.x() != end.x() && start.y() == end.y();
    }

    public boolean intersects(Segment other) {
        // Check 1: Bounding box non-overlap (Rapid rejection)
        long minX1 = Math.min(start.x(), end.x());
        long maxX1 = Math.max(start.x(), end.x());
        long minY1 = Math.min(start.y(), end.y());
        long maxY1 = Math.max(start.y(), end.y());

        long minX2 = Math.min(other.start.x(), other.end.x());
        long maxX2 = Math.max(other.start.x(), other.end.x());
        long minY2 = Math.min(other.start.y(), other.end.y());
        long maxY2 = Math.max(other.start.y(), other.end.y());

        if (maxX1 < minX2 || maxX2 < minX1 || maxY1 < minY2 || maxY2 < minY1) {
            return false;
        }

        // Check 2: Specialized orthogonal intersection (Since all segments are H or V)
        if (this.isHorizontal() && other.isVertical()) {
            return other.start.x() >= minX1 && other.start.x() <= maxX1 &&
                   start.y() >= minY2 && start.y() <= maxY2;
        }
        if (this.isVertical() && other.isHorizontal()) {
            return start.x() >= minX2 && start.x() <= maxX2 &&
                   other.start.y() >= minY1 && other.start.y() <= maxY1;
        }

        // Check 3: Parallel segments check overlap
        if (this.isHorizontal() && other.isHorizontal()) {
            return start.y() == other.start.y() && // Same Y
                   Math.max(minX1, minX2) <= Math.min(maxX1, maxX2); // Overlap X
        }
        if (this.isVertical() && other.isVertical()) {
            return start.x() == other.start.x() && // Same X
                   Math.max(minY1, minY2) <= Math.min(maxY1, maxY2); // Overlap Y
        }

        return false;
    }

    // Strict intersection (excludes endpoints and overlaps)
    public boolean crosses(Segment other) {
         if (!intersects(other)) return false;

         // If parallel overlap, it doesn't "cross" it overlaps.
         if (this.isHorizontal() && other.isHorizontal()) return false;
         if (this.isVertical() && other.isVertical()) return false;

         long minX1 = Math.min(start.x(), end.x());
         long maxX1 = Math.max(start.x(), end.x());
         long minY1 = Math.min(start.y(), end.y());
         long maxY1 = Math.max(start.y(), end.y());

         long minX2 = Math.min(other.start.x(), other.end.x());
         long maxX2 = Math.max(other.start.x(), other.end.x());
         long minY2 = Math.min(other.start.y(), other.end.y());
         long maxY2 = Math.max(other.start.y(), other.end.y());

         // Orthogonal crossing: The intersection point must be STRICTLY inside both segments
         if (this.isHorizontal() && other.isVertical()) {
             // this Y is between other Ys strictly
             // other X is between this Xs strictly
             return other.start.x() > minX1 && other.start.x() < maxX1 &&
                    start.y() > minY2 && start.y() < maxY2;
         }
         if (this.isVertical() && other.isHorizontal()) {
             return start.x() > minX2 && start.x() < maxX2 &&
                    other.start.y() > minY1 && other.start.y() < maxY1;
         }
         return false;
    }

    public boolean contains(Position p) {
        long minX = Math.min(start.x(), end.x());
        long maxX = Math.max(start.x(), end.x());
        long minY = Math.min(start.y(), end.y());
        long maxY = Math.max(start.y(), end.y());
        
        return p.x() >= minX && p.x() <= maxX && p.y() >= minY && p.y() <= maxY;
    }
}
