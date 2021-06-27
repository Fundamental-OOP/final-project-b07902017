package border;

import java.awt.*;
import model.SpriteShape;

public class Wall extends Border {
    public Wall(int x1, int x2, int height) {
        super(new Point(x1, 0), new SpriteShape(
            new Dimension(x2 - x1, height),
            new Dimension(x1, 0),
            new Dimension(x2 - x1, height)
        ));
    }
}
