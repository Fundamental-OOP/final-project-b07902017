package border;

import java.awt.*;
import model.SpriteShape;
import knight.Knight;
import model.Sprite;

public class Ceiling extends Border {
    private int damage;
    public Ceiling(int y1, int y2, int width, int damage) {
        super(new Point(0, y1), new SpriteShape(
            new Dimension(width, y2 - y1),
            new Dimension(0, y1),
            new Dimension(width, y2 - y1)
        ));
        this.damage = damage;
    }
    @Override
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to) {
        if (from instanceof Border && to instanceof Knight) {
            Knight tmp = (Knight) to;
            tmp.onDamaged(
                tmp.getArea(new Dimension(87, 70),new Dimension(55, 88)),
                damage
            );
            Rectangle body = from.getBody();
            int offsetUp = to.getY() - body.y;
            int offsetDown = body.y + body.height - to.getY();
            if (offsetUp < 0) {
                to.setLocation(new Point(to.getX(), to.getY() - (to.getRange().width + offsetUp)));
            } else {
                to.setLocation(new Point(to.getX(), to.getY() + offsetDown));
            }
        }
    }
}
