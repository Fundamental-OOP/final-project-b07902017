package stairs;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import model.Sprite;
import model.HealthPointSprite;
import java.util.*;

public class Nails extends Stair {
    private Set<HealthPointSprite> touched;
    private int damage;
    private static Image getImage() {
        try{
            return ImageIO.read(new File("assets/nails.png"));
        }catch(Exception e){
            return null;
        }
    }
    private static Image image = getImage();
    public Nails(Point location, int damage) {
        super(location, image.getWidth(null), image.getHeight(null));
        this.touched = new HashSet<>();
        this.damage = damage;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, location.x, location.y, null);
    }
    @Override 
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){
        super.collisionHandle(originalLocation, from, to);
        if (from instanceof Stair && to instanceof HealthPointSprite) {
            HealthPointSprite tmp = (HealthPointSprite) to;
            Dimension size = to.getBodySize();
            if (!touched.contains(tmp) && to.getLocation().y + size.height - from.getLocation().y < 30) {
                tmp.onDamaged(damage);
                touched.add(tmp);
            }
        }
    }

    @Override
    public Stair makeNew(Point point) {
        // TODO Auto-generated method stub
        return new Nails(point, 5);
    }
}
