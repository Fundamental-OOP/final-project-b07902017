package border;

import java.awt.*;

import model.HealthPointSprite;
import model.Sprite;
import model.SpriteShape;

public class Floor extends Border {
    public Floor(int y1, int y2, int width) {
        super(new Point(0, y1), new SpriteShape(
            new Dimension(width, y2 - y1),
            new Dimension(0, y1),
            new Dimension(width, y2 - y1)
        ));
    }

    public void collisionHandle(Point originalLocation, Sprite from, Sprite to) {
        if (from instanceof Border && to instanceof HealthPointSprite) {
            HealthPointSprite tmp = (HealthPointSprite) to;
            tmp.onDamaged(Integer.MAX_VALUE);
        }
    }
}
