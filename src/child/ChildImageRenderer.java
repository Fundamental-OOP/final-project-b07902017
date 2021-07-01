package child;

import fsm.ImageRenderer;
import model.Direction;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ChildImageRenderer implements ImageRenderer {
    protected Child child;

    public ChildImageRenderer(Child child) {
        this.child = child;
    }

    @Override
    public void render(Image image, Graphics g) {
        Direction face = child.getFace();
        Rectangle range = child.getRange();
        // Rectangle body = child.getBody();
        if (face == Direction.LEFT) {
            g.drawImage(image, range.x + range.width, range.y, -range.width, range.height, null);
        } else {
            g.drawImage(image, range.x, range.y, range.width, range.height, null);
        }
        //  g.setColor(Color.RED);
        //  g.drawRect(body.x, body.y, body.width, body.height);
    }
}
