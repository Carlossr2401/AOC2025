package software.aoc.day08.b;

import software.aoc.day08.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day08PartBSolver implements Solver {

    private final InstructionReader<JunctionBoxList> reader;

    public Day08PartBSolver(InstructionReader<JunctionBoxList> reader) {
        this.reader = reader;
    }

    @Override
    public Object solve() throws IOException {
        JunctionBoxList allBoxes = reader.readAllData();
        List<Connection> allConnections = generateConnections(allBoxes);
        
        return solveProblem(allBoxes, allConnections);
    }

    private List<Connection> generateConnections(JunctionBoxList allBoxes) {
        List<Connection> allConnections = new ArrayList<>();
        for (int i = 0; i < allBoxes.size(); i++) {
            for (int j = i + 1; j < allBoxes.size(); j++) {
                JunctionBox boxA = allBoxes.get(i);
                JunctionBox boxB = allBoxes.get(j);
                allConnections.add(Connection.create(boxA, boxB));
            }
        }
        allConnections.sort(Comparator.comparingDouble(Connection::distanceSquared));
        return allConnections;
    }

    private long solveProblem(JunctionBoxList allBoxes, List<Connection> allConnections) {
        JunctionBoxList currentBoxes = allBoxes;
        final int TOTAL_BOXES = currentBoxes.size();
        int effectiveUnions = 0;

        long lastX1 = 0;
        long lastX2 = 0;

        for (Connection c : allConnections) {
            if (effectiveUnions >= TOTAL_BOXES - 1) {
                break;
            }

            FindResult resultA = currentBoxes.getRoot(c.idA());
            JunctionBox rootA = resultA.root();

            FindResult resultB = resultA.compressedList().getRoot(c.idB());
            JunctionBox rootB = resultB.root();

            if (rootA.id() != rootB.id()) {

                currentBoxes = currentBoxes.union(c.idA(), c.idB());

                // Note: The original code got boxA/boxB from currentBoxes. 
                // Since JunctionBox is a record and only parentId/circuitSize change in modified boxes,
                // and x/y/z remain same, looking up is correct to get updated state if needed,
                // but x() is immutable. However, let's keep original logic.
                JunctionBox boxA = currentBoxes.get(c.idA());
                JunctionBox boxB = currentBoxes.get(c.idB());

                lastX1 = boxA.x();
                lastX2 = boxB.x();

                effectiveUnions++;
            }
        }

        System.out.println("Uniones efectivas realizadas: " + effectiveUnions);
        System.out.println("Última conexión: X1=" + lastX1 + ", X2=" + lastX2);

        return lastX1 * lastX2;
    }
}
