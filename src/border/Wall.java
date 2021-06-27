package border;

import java.awt.*;
import model.SpriteShape;
import model.Sprite;
import knight.Knight;
public class Wall extends Border {
    public Wall(int x1, int x2, int height) {
        super(new Point(x1, 0), new SpriteShape(
            new Dimension(x2 - x1, height),
            new Dimension(x1, 0),
            new Dimension(x2 - x1, height)
        ));
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
