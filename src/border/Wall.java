package border;

import java.awt.*;
import model.SpriteShape;
import model.HealthPointSprite;
import model.Sprite;
public class Wall extends Border {
    private Image image;
    private int height;
    private Type type;
    public Wall(int x1, int x2, int height, Image image, Type type) {
        super(new Point(x1, 0), new SpriteShape(
            new Dimension(x2 - x1, height),
            new Dimension(x1, 0),
            new Dimension(x2 - x1, height)
        ));
        this.image = image;
        this.height = height;
        this.type = type;
    }
    
    public enum Type{
        LEFT, RIGHT;
    }
    @Override
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){
        if (from instanceof Border && to instanceof HealthPointSprite) {
            Rectangle body = from.getBody();
            int offsetLeft = to.getX() - body.x;
            int offsetRight = body.x + body.width - to.getX();
            if (type == Type.RIGHT) {
                to.setLocation(new Point(to.getX() - (to.getRange().width + offsetLeft), to.getY()));
            } else {
                to.setLocation(new Point(to.getX() + offsetRight, to.getY()));
            }
        }
    }
    @Override 
    public void render(Graphics g) {
        Point location = this.getLocation();
        for(int i = 0; i * image.getHeight(null) < height; i++)
        g.drawImage(image, (int)location.getX(), (int)location.getY() + i * image.getHeight(null), null);
    }
}
