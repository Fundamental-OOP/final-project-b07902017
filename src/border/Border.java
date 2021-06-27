package border;

import model.Sprite;
import java.awt.*;
import knight.Knight;
import model.SpriteShape;

public abstract class Border extends Sprite {
    private final Point location;
    private final SpriteShape shape;
    public Border(Point location, SpriteShape shape) {
        super();
        this.location = location;
        this.shape = shape;
    }
    @Override
    public void update(){};
    @Override
    public void render(Graphics g){};
    @Override
    public void onDamaged(Rectangle damageArea, int damage){};
    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(location, shape.size);
    }

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
        if (from instanceof Border && to instanceof Knight) {
            Rectangle body = from.getBody();
            int offsetLeft = to.getX() - body.x;
            int offsetRight = body.x + body.width - to.getX();
            if (offsetLeft < 0) {
                to.setLocation(new Point(to.getX() - (to.getRange().width + offsetLeft), to.getY()));
            } else {
                to.setLocation(new Point(to.getX() + offsetRight, to.getY()));
            }
        }
    }
}
