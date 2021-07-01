package model;

import java.awt.*;

/**
 * reference: https://github.com/Johnny850807/Java-Game-Programming-with-FSM-and-MVC
 */
public enum Direction {
    UP, DOWN, LEFT, RIGHT;

    public Dimension translate() {
        switch (this) {
            case LEFT:
                return new Dimension(-5, 0);
            case RIGHT:
                return new Dimension(5, 0);
            default:
                throw new IllegalStateException("Impossible");
        }
    }
}
