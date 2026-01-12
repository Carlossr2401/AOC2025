package software.aoc.day10.a;

import software.aoc.day10.*;

import java.util.ArrayList;
import java.util.List;

public class Day10PartASolver implements Solver {

    private final InstructionReader reader;

    public Day10PartASolver(InstructionReader reader) {
        this.reader = reader;
    }

    @Override
    public Object solve() {
        return solveProblem();
    }

    public int solveProblem() {
        Machines<LightConfiguration> allMachines = parseMachines(reader.readInput());
        int totalPresses = 0;
        for (Machine<LightConfiguration> machine : allMachines) {
            int minPresses = solveMachine(machine);
            totalPresses += minPresses;
        }
        return totalPresses;
    }

    private Machines<LightConfiguration> parseMachines(List<String> lines) {
        List<Machine<LightConfiguration>> list = new ArrayList<>();
        for (String line : lines) {
            list.add(createMachine(line));
        }
        return new Machines<>(list);
    }

    private Machine<LightConfiguration> createMachine(String line) {
        String targetPart = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
        List<Button> buttons = new ArrayList<>();

        java.util.regex.Matcher buttonMatcher = java.util.regex.Pattern.compile("\\((.*?)\\)").matcher(line);

        while (buttonMatcher.find()) {
            String buttonIndicesStr = buttonMatcher.group(1);
            buttons.add(parseButton(buttonIndicesStr));
        }

        LightConfiguration target = parseTarget(targetPart);

        return new Machine<>(target, buttons);
    }

    private LightConfiguration parseTarget(String targetStr) {
        List<Boolean> targetLights = new ArrayList<>();
        for (char c : targetStr.toCharArray()) {
            if (c == '#') {
                targetLights.add(true);
            } else if (c == '.') {
                targetLights.add(false);
            }
        }
        return new LightConfiguration(targetLights);
    }

    private Button parseButton(String buttonIndicesStr) {
        String cleanStr = buttonIndicesStr.replaceAll("\\s+", "");
        String[] indices = cleanStr.split(",");

        List<Integer> lightIndices = new ArrayList<>();

        for (String indexStr : indices) {
            if (!indexStr.isEmpty()) {
                try {
                    lightIndices.add(Integer.parseInt(indexStr));
                } catch (NumberFormatException e) {
                    System.err.println("Error de formato al parsear índice de botón: " + indexStr);
                }
            }
        }

        return new Button(lightIndices);
    }

    private int solveMachine(Machine<LightConfiguration> machine) {
        int numButtons = machine.buttons().size();
        int numLights = machine.configuration().configuration().size();
        List<Boolean> target = machine.configuration().configuration();

        int minPresses = Integer.MAX_VALUE;

        // Iterate all subsets of buttons ( 1 << numButtons )
        // Using int for mask since numButtons is small (<= ~20)
        int combinations = 1 << numButtons;

        for (int i = 0; i < combinations; i++) {
            int currentPresses = Integer.bitCount(i);
            
            // Optimization: if we already found a better or equal solution, 
            // no need to simulate this one if strict inequality check, 
            // but we want ANY solution with min presses.
            // If currentPresses >= minPresses, we might skip checking if we only care about min.
            // But we must check if it IS a solution.
            if (currentPresses >= minPresses) {
                continue;
            }

            if (check(i, machine, numLights, target)) {
                minPresses = currentPresses;
            }
        }
        return minPresses;
    }

    private boolean check(int mask, Machine<LightConfiguration> machine, int numLights, List<Boolean> target) {
        boolean[] current = new boolean[numLights]; // Defaults to false (off)

        List<Button> buttons = machine.buttons();
        
        for (int b = 0; b < buttons.size(); b++) {
            if ((mask & (1 << b)) != 0) {
                // Button b is pressed
                Button btn = buttons.get(b);
                for (Integer lightIndex : btn.positions()) {
                    if (lightIndex >= 0 && lightIndex < numLights) {
                        current[lightIndex] = !current[lightIndex];
                    }
                }
            }
        }

        // Compare with target
        for (int k = 0; k < numLights; k++) {
            if (current[k] != target.get(k)) {
                return false;
            }
        }
        return true;
    }
}
