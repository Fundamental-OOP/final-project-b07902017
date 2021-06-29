package stairs;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;

import knight.Knight;
import model.Sprite;
import model.HealthPointSprite;

public class Fake extends Stair {
    private int heal;
    private int remaintime;
    private boolean  touched;
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
        this.remaintime = 30;
        this.touched = false;
    }
    @Override
    public void render(Graphics g) {
        if(remaintime >=0)
            g.drawImage(image, location.x, location.y, null);
    }

    public void update(){
        super.update();
        if (this.touched){
            remaintime--;
        }
    }

    @Override 
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){
        this.touched = true;
        if (remaintime >=0)
            super.collisionHandle(originalLocation, from, to);
        
    }

    @Override
    public Stair makeNew(Point point) {
        // TODO Auto-generated method stub
        return new Fake(point, 1);
    }
}
