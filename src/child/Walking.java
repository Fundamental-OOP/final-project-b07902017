package child;

import fsm.CyclicSequence;
import fsm.ImageState;
import model.Direction;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Walking extends CyclicSequence {
    public static final String AUDIO_STEP1 = "step1";
    public static final String AUDIO_STEP2 = "step2";
    private final Child child;

    public Walking(Child child, List<ImageState> states) {
        super(states);
        this.child = child;
    }

    @Override
    public void update() {
        if (child.isAlive()) {
            super.update();
            for (Direction direction : child.getDirections()) {
                child.getWorld().move(child, direction.translate());
            }
        }
    }

    @Override
    public String toString() {
        return "Walking";
    }
}
