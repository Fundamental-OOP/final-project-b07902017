package border;

import model.CollisionHandler;
import model.Sprite;
import java.awt.*;
import model.SpriteShape;

public abstract class Border extends Sprite implements CollisionHandler{
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
    public void onHealed(Rectangle damageArea, int heal){};
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

}
