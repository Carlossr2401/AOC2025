package software.aoc.day09.b;

import software.aoc.day09.Position;
import software.aoc.day09.Rectangle;
import software.aoc.day09.Segment;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private final List<Segment> edges;

    public Polygon(List<Position> vertices) {
        edges = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            Position start = vertices.get(i);
            Position end = vertices.get((i + 1) % vertices.size());
            edges.add(new Segment(start, end));
        }
    }

    public List<Segment> edges() {
        return edges;
    }

    // Algoritmo de ray casting horizontal + Boundary check
    public boolean contains(Position p) {
        // Check 1: Boundary
        for (Segment edge : edges) {
            if (edge.contains(p)) return true;
        }

        // Check 2: Ray casting (Inside)
        int intersections = 0;
        long px = p.x();
        long py = p.y();

        for (Segment edge : edges) {
            long x1 = edge.start().x();
            long y1 = edge.start().y();
            long x2 = edge.end().x();
            long y2 = edge.end().y();

            // Ignora segmentos horizontales que no cruzan la línea horizontal de p
            if (y1 == y2) continue;

            // Verifica si p.y está entre y1 y y2 (ray passes through y-range of edge)
            // Use inclusive low, exclusive high to avoid double counting vertices
            if (py >= Math.min(y1, y2) && py < Math.max(y1, y2)) {
                // Calcular intersección con línea vertical del punto
                double xIntersection = x1 + (double)(py - y1) * (x2 - x1) / (y2 - y1);
                // Ray is "to the right" of the point? No, standard is cast ray to right.
                // If intersection is > px, then edge is to the right of point.
                if (xIntersection > px) intersections++;
            }
        }
        return intersections % 2 == 1;
    }

    public boolean contains(Rectangle r) {
        // 1. All corners must be inside or on boundary
        for (Position corner : r.allCorners()) {
            if (!this.contains(corner)) return false;
        }

        // 2. No polygon edge can be strictly inside the rectangle
        // (This prevents the polygon from having a "dent" that is inside the rectangle)
        for (Segment edge : edges) {
            if (r.strictlyContains(edge.start()) || r.strictlyContains(edge.end())) {
                return false;
            }
        }
        
        // 3. No polygon edge can cross the rectangle boundary strictly
        // (This prevents the polygon from passing through the rectangle)
        for (Segment rectSide : r.sides()) {
            for (Segment polyEdge : edges) {
                if (rectSide.crosses(polyEdge)) {
                    return false;
                }
            }
        }

        return true;
    }
}
