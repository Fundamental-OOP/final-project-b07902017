package stairs;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

import model.Sprite;
import model.HealthPointSprite;
public class Trampoline extends Stair {
    private static final int force = -5;
    private int heal;
    private static Image getImage() {
        try{
            return ImageIO.read(new File("assets/trampoline.png"));
        }catch(Exception e){
            return null;
        }
    }
    private static Image image = getImage();
    public Trampoline(Point location, int heal) {
        super(location, image.getWidth(null), image.getHeight(null));
        this.heal = heal;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, location.x, location.y, null);
    }
    @Override 
    public void collisionHandle(Point originalLocation, Sprite to){

        HealthPointSprite tmp = (HealthPointSprite) to;
        Dimension size = to.getBodySize();
        if (to.getLocation().y + size.height - getLocation().y < 10) {
            tmp.onHealed(heal);
            tmp.setLocation(new Point(tmp.getLocation().x, this.location.y - ((HealthPointSprite) tmp).getSize().height-10));
            tmp.setspeed(force);
        }
    }

    @Override
    public Stair makeNew(Point point) {
        // TODO Auto-generated method stub
        return new Trampoline(point, 1);
    }
}
