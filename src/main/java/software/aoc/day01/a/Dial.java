package software.aoc.day01.a;

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
        state = (state + parseValue(movement) + 100) % 100;
        if (state == 0) zeroCount++;
    }

    private int parseValue(String movement) {
        int value = Integer.parseInt(movement.substring(1));
        return movement.charAt(0) == 'L' ? -value : value;
    }
}
