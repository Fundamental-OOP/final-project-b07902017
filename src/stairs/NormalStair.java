package stairs;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import model.Sprite;
import model.HealthPointSprite;
import java.util.*;

public class NormalStair extends Stair {
    private Set<HealthPointSprite> touched;
    private int heal;
    private static Image getImage() {
        try{
            return ImageIO.read(new File("assets/normal.png"));
        }catch(Exception e){
            return null;
        }
    }
    private static Image image = getImage();
    public NormalStair(Point location, int heal) {
        super(location, image.getWidth(null), image.getHeight(null));
        this.touched = new HashSet<>();
        this.heal = heal;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, location.x, location.y, null);
    }
    @Override 
    public void collisionHandle(Point originalLocation, Sprite to){
        super.collisionHandle(originalLocation, to);
        if (to instanceof HealthPointSprite) {
            HealthPointSprite tmp = (HealthPointSprite) to;
            Dimension size = to.getBodySize();
            if (!touched.contains(tmp) && to.getLocation().y + size.height - getLocation().y < 10) {
                tmp.onHealed(heal);
                touched.add(tmp);
            }
        }
    }

    @Override
    public Stair makeNew(Point point) {
        // TODO Auto-generated method stub
        return new NormalStair(point, 1);
    }
}
