package software.aoc.day09;

public class Rectangle {

    private final Position p1;
    private final Position p2;
    private final Position p3;
    private final Position p4;
    private final long area;

    public Rectangle(Position a, Position b) {
        long minX = Math.min(a.x(), b.x());
        long maxX = Math.max(a.x(), b.x());
        long minY = Math.min(a.y(), b.y());
        long maxY = Math.max(a.y(), b.y());

        this.p1 = new Position(minX, minY);
        this.p2 = new Position(maxX, maxY);
        this.p3 = new Position(minX, maxY);
        this.p4 = new Position(maxX, minY);

        this.area = (maxX - minX + 1) * (maxY - minY + 1);
    }

    public Position[] allCorners() {
        return new Position[]{p1, p2, p3, p4};
    }

    public Segment[] sides() {
        return new Segment[]{
                new Segment(p1, p3), // left
                new Segment(p3, p2), // top
                new Segment(p2, p4), // right
                new Segment(p4, p1)  // bottom
        };
    }

    public long area() {
        return area;
    }

    public boolean contains(Position p) {
        return (p.x() >= p1.x() && p.x() <= p2.x() &&
                p.y() >= p1.y() && p.y() <= p2.y());
    }

    public boolean strictlyContains(Position p) {
        return (p.x() > p1.x() && p.x() < p2.x() &&
                p.y() > p1.y() && p.y() < p2.y());
    }
}
