package software.aoc.day10;

import software.aoc.day10.a.LightConfiguration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MachineParser {

    public static Machine<LightConfiguration> parseForPartA(String line) {
        String targetPart = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
        LightConfiguration target = parseTargetA(targetPart);
        List<Button> buttons = parseButtons(line);
        return new Machine<>(target, buttons);
    }

    public static Machine<List<Integer>> parseForPartB(String line) {
        String targetPart = line.substring(line.indexOf('{') + 1, line.indexOf('}'));
        List<Integer> targets = parseTargetB(targetPart);
        List<Button> buttons = parseButtons(line);
        return new Machine<>(targets, buttons);
    }

    private static List<Button> parseButtons(String line) {
        List<Button> buttons = new ArrayList<>();
        // Matches (1,2,3) or (1, 2) etc.
        Matcher buttonMatcher = Pattern.compile("\\(([\\d,\\s]+)\\)").matcher(line);
        while (buttonMatcher.find()) {
            buttons.add(parseButton(buttonMatcher.group(1)));
        }
        return buttons;
    }

    private static Button parseButton(String content) {
        String cleanStr = content.replaceAll("\\s+", "");
        String[] indices = cleanStr.split(",");
        List<Integer> lightIndices = new ArrayList<>();
        for (String indexStr : indices) {
            if (!indexStr.isEmpty()) {
                lightIndices.add(Integer.parseInt(indexStr));
            }
        }
        return new Button(lightIndices);
    }

    private static LightConfiguration parseTargetA(String targetStr) {
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

    private static List<Integer> parseTargetB(String targetStr) {
        String[] parts = targetStr.split(",");
        List<Integer> targets = new ArrayList<>();
        for (String s : parts) {
            targets.add(Integer.parseInt(s.trim()));
        }
        return targets;
    }
}
