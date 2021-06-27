package border;

import java.awt.*;

import model.Sprite;
import model.SpriteShape;
import knight.Knight;

public class Floor extends Border {
    public Floor(int y1, int y2, int width) {
        super(new Point(0, y1), new SpriteShape(
            new Dimension(width, y2 - y1),
            new Dimension(0, y1),
            new Dimension(width, y2 - y1)
        ));
    }
    @Override
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to) {
        if (from instanceof Border && to instanceof Knight) {
            Knight tmp = (Knight) to;
            tmp.onDamaged(
                tmp.getArea(new Dimension(87, 70),new Dimension(55, 88)),
                Integer.MAX_VALUE
            );
        }
    }
}
