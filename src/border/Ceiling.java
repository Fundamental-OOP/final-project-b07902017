package border;

import java.awt.*;
import model.SpriteShape;
import knight.Knight;
import model.Sprite;
import java.io.File;
import java.awt.*;
import java.awt.image.*;


public class Ceiling extends Border {
    private int damage;
    private Image image;
    private int width;
    public Ceiling(int y1, int y2, int width, int damage, Image image) {
        super(new Point(0, y1), new SpriteShape(
            new Dimension(width, y2 - y1),
            new Dimension(0, y1),
            new Dimension(width, y2 - y1)
        ));
        this.damage = damage;
        this.image = image;
        this.width = width;
    }

    @Override
    public void render(Graphics g){
        Point location = this.getLocation();
        for(int i = 0; i * image.getWidth(null) < width; i++)
        g.drawImage(image, (int)location.getX() + i * image.getWidth(null), (int)location.getY(), null);
    }
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to) {
        if (from instanceof Border && to instanceof Knight) {
            Knight tmp = (Knight) to;
            tmp.onDamaged(
                tmp.getArea(new Dimension(87, 70),new Dimension(55, 88)),
                damage
            );
            Rectangle body = from.getBody();
            to.setLocation(new Point(to.getX(), body.y + body.height));
        }
    }
}
