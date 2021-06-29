package child;

import fsm.FiniteStateMachine;
import fsm.ImageRenderer;
import fsm.State;
import fsm.WaitingPerFrame;
import model.Direction;
import model.Sprite;
import model.HealthPointSprite;
import model.SpriteShape;

import java.awt.*;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import static fsm.FiniteStateMachine.Transition.from;
import static child.Child.Event.*;
import static model.Direction.LEFT;
import static utils.ImageStateUtils.imageStatesFromFolder;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Child extends HealthPointSprite {

    public static final int CHILD_HP = 12;
    private final SpriteShape shape;
    private final FiniteStateMachine fsm;
    private final Set<Direction> directions = new CopyOnWriteArraySet<>();

    public enum Event {
        WALK, STOP, DAMAGED
    }

    public Child(Point location) {
        super(CHILD_HP);
        this.location = location;
        // public SpriteShape(Dimension size, Dimension bodyOffset, Dimension bodySize) {
        // shape = new SpriteShape(new Dimension(146, 176),
        //         new Dimension(33, 38), new Dimension(66, 105));
        shape = new SpriteShape(new Dimension(40, 50),
                new Dimension(0, 0), new Dimension(40, 50));
        fsm = new FiniteStateMachine();

        ImageRenderer imageRenderer = new ChildImageRenderer(this);
        State idle = new WaitingPerFrame(4,
                new Idle(imageStatesFromFolder("assets/child/idle", imageRenderer)));
        State walking = new WaitingPerFrame(3,
                new Walking(this, imageStatesFromFolder("assets/child/walking", imageRenderer)));

        fsm.setInitialState(idle);
        fsm.addTransition(from(idle).when(WALK).to(walking));
        fsm.addTransition(from(walking).when(STOP).to(idle));
    }

    public void move(Direction direction) {
        if (direction == LEFT || direction == Direction.RIGHT) {
            face = direction;
        }
        if (!directions.contains(direction)) {
            this.directions.add(direction);
            fsm.trigger(WALK);
        }
    }

    public void stop(Direction direction) {
        directions.remove(direction);
        if (directions.isEmpty()) {
            fsm.trigger(STOP);
        }
    }

    public void update() {
        fsm.update();
        super.update();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        fsm.render(g);
    }

    public Set<Direction> getDirections() {
        return directions;
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

    @Override
    public Dimension getSize(){
        return shape.size;
    }

    @Override
    public Dimension getBodyOffset() {
        return shape.bodyOffset;
    }

    @Override
    public Dimension getBodySize() {
        return shape.bodySize;
    }

    @Override
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){
        System.out.printf("collision22\n");
        if (from instanceof HealthPointSprite && to instanceof HealthPointSprite) {
            Rectangle body = from.getBody();
            int offsetLeft = to.getX() - body.x;
            int offsetRight = body.x + body.width - to.getX();
            if (offsetLeft < 0) {
                to.setLocation(new Point(to.getX() - (to.getRange().width + offsetLeft) / 3, to.getY()));
            } else {
                to.setLocation(new Point(to.getX() + offsetRight / 3, to.getY()));
            }

        }
    }



}
