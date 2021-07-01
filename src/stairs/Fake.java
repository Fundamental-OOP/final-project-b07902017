package stairs;
import java.awt.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;

import model.HealthPointSprite;
import model.Sprite;


public class Fake extends Stair {
    private Set<HealthPointSprite> touched;
    private int heal;
    private int remaintime;
    private boolean  touchedFlag;
    private static Image getImage() {
        try{
            return ImageIO.read(new File("assets/fake.png"));
        }catch(Exception e){
            return null;
        }
    }
    private static Image image = getImage();
    public Fake(Point location, int heal) {
        super(location, image.getWidth(null), image.getHeight(null));
        this.heal = heal;
        this.remaintime = 60;
        this.touchedFlag = false;
        this.touched = new HashSet<>();
    }
    @Override
    public void render(Graphics g) {
        if(remaintime >=0)
            g.drawImage(image, location.x, location.y, null);
    }

    public void update(){
        super.update();
        if (this.touchedFlag){
            remaintime--;
        }
    }

    @Override 
    public void collisionHandle(Point originalLocation, Sprite to){
        this.touchedFlag = true;
        if (remaintime >=0){
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
            
        
    }

    @Override
    public Stair makeNew(Point point) {
        // TODO Auto-generated method stub
        return new Fake(point, 1);
    }
}
