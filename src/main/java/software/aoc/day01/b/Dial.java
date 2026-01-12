package software.aoc.day01.b;

import java.util.List;

public class Dial {

    private final List<String> movements;
    private int state = 50;
    private int zeroCount = 0;

    public Dial(List<String> movements) {
        this.movements = movements;
    }

    public int getZeros() {
        applyAllMovements();
        return zeroCount;
    }

    private void applyAllMovements() {
        movements.forEach(this::applyMovement);
    }

    private void applyMovement(String movement) {
        int value = parseValue(movement);
        int direction = Integer.signum(value);
        int steps = Math.abs(value);

        for (int i = 0; i < steps; i++) {
            state = (state + direction + 100) % 100;
            if (state == 0) zeroCount++;
        }
    }

    private int parseValue(String movement) {
        int number = Integer.parseInt(movement.substring(1));
        return movement.charAt(0) == 'L' ? -number : number;
    }
}
