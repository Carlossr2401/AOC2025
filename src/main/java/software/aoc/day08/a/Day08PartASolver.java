package software.aoc.day08.a;

import software.aoc.day08.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day08PartASolver implements Solver {

    private final InstructionReader<JunctionBoxList> reader;

    public Day08PartASolver(InstructionReader<JunctionBoxList> reader) {
        this.reader = reader;
    }

    @Override
    public Object solve() throws IOException {
        JunctionBoxList allBoxes = reader.readAllData();
        List<Connection> allConnections = generateConnections(allBoxes);
        
        // Logic originally in Solver.java
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
        List<Integer> circuitSizes = getCircuitSizes(allBoxes, allConnections);

        circuitSizes.sort(Comparator.reverseOrder());

        if (circuitSizes.size() < 3) {
            System.out.println(circuitSizes);
            System.err.println("Error de Lógica: Solo se encontraron " + circuitSizes.size() + " circuitos. Se requieren 3 para la multiplicación final.");
            return 0;
        }

        long size1 = circuitSizes.get(0);
        long size2 = circuitSizes.get(1);
        long size3 = circuitSizes.get(2);

        return size1 * size2 * size3;
    }

    private List<Integer> getCircuitSizes(JunctionBoxList initialBoxes, List<Connection> allConnections) {
        JunctionBoxList currentBoxes = initialBoxes;
        int effectiveConnections = 0;
        final int TARGET_CONNECTIONS = 1000;

        for (Connection c : allConnections) {

            if (effectiveConnections >= TARGET_CONNECTIONS) {
                break;
            }

            FindResult resultA = currentBoxes.getRoot(c.idA());
            JunctionBox rootA = resultA.root();

            FindResult resultB = resultA.compressedList().getRoot(c.idB());
            JunctionBox rootB = resultB.root();

            if (rootA.id() != rootB.id()) {
                currentBoxes = currentBoxes.union(c.idA(), c.idB());
            }
            effectiveConnections++;
        }

        return currentBoxes.getFinalCircuitSizes();
    }
}
