package stairs;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import model.Sprite;
import model.HealthPointSprite;
import java.util.*;

public class Conveyor extends Stair {
    private static final int speed = 3;
    private Set<HealthPointSprite> touched;
    private int heal;
    private int direction; // 1: RIGHT, -1: LEFT 
    private static Image getImage(String file_name) {
        try{
            return ImageIO.read(new File(file_name));
        }catch(Exception e){
            return null;
        }
    }
    private static Image image_right = getImage("assets/conveyor_right.png");
    private static Image image_left = getImage("assets/conveyor_left.png");

    private static Image selectImage(int direction){
        return direction == 1 ? image_right : image_left;
    }
    public Conveyor(Point location, int heal, int direction) {
        super(location, selectImage(direction).getWidth(null), selectImage(direction).getHeight(null));
        this.touched = new HashSet<>();
        this.heal = heal;
        this.direction = direction;
    }
    @Override
    public void render(Graphics g) {
        g.drawImage(selectImage(direction), location.x, location.y, null);
    }
    @Override 
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){
        super.collisionHandle(originalLocation, from, to);
        if (from instanceof Stair && to instanceof HealthPointSprite) {
            HealthPointSprite tmp = (HealthPointSprite) to;
            Dimension size = to.getBodySize();
            if ( to.getLocation().y + size.height - from.getLocation().y < 10) {
                to.setLocation(new Point(to.getLocation().x + direction*speed, to.getLocation().y-1));
                if (!touched.contains(tmp)){
                    tmp.onHealed(heal);
                    touched.add(tmp);
                }
            }
        }
    }
    @Override
    public Stair makeNew(Point point) {
        // TODO Auto-generated method stub
        return new Conveyor(point, 1, direction);
    }

}
