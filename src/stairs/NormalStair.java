package stairs;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import model.Sprite;
import model.HealthPointSprite;

public class NormalStair extends Stair {
    private boolean isTouched;
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
        this.isTouched = false;
        this.heal = heal;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(image, location.x, location.y, null);
    }
    @Override 
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){
        super.collisionHandle(originalLocation, from, to);
        if (isTouched == false && from instanceof Stair && to instanceof HealthPointSprite) {
            HealthPointSprite tmp = (HealthPointSprite) to;
            Dimension size = to.getBodySize();
            if (to.getLocation().y + size.height - from.getLocation().y < 30) {
                tmp.onHealed(heal);
                isTouched = true;
            }
        }
    }

}
