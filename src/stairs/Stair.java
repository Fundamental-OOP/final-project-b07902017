package stairs;

import model.Sprite;
import knight.Knight;
import model.SpriteShape;
// import fsm.ImageRenderer;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;


public class Stair extends Sprite{
    private final SpriteShape shape;
    private int width;
    private int height;
    private static Image getImage() {
        try{
            return ImageIO.read(new File("assets/normal.png"));
        }catch(Exception e){
            return null;
        }
    }
    private static Image image = getImage();

    public Stair(Point location) {
        this.location = location;
       
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);

        // public SpriteShape(Dimension size, Dimension bodyOffset, Dimension bodySize) {
        shape = new SpriteShape(new Dimension(width, height),
        new Dimension(1, 1), new Dimension(width - 2, height - 2));
        // shape = new SpriteShape(new Dimension(146, 176),
        // new Dimension(33, 38), new Dimension(66, 105));
    }

    public void update(){
        int rising_rate = -1;
        this.location.translate(0, rising_rate);
    }

    public  Rectangle getRange(){
        return new Rectangle(location, shape.size);
    }

    public  Dimension getBodyOffset(){
        return shape.bodyOffset;
    }

    public  Dimension getBodySize(){
        return shape.bodySize;
    }

    @Override
    public void onDamaged(Rectangle damageArea, int damage){
     
    }

    @Override
    public void render(Graphics g) {
        // g.setColor(Color.BLACK);
        // g.fillRect(location.x, location.y, width, height);
        g.drawImage(image, location.x, location.y, null);
    }

    @Override
    public void collisionHandle(Point originalLocation, Sprite stair, Sprite knight){
        Knight k = (Knight) knight;
        System.out.printf("Knight touch stair %d\n", k.getSize().height);
        Dimension size = knight.getBodySize();
        if (knight.getLocation().y + size.height - stair.getLocation().y < 30)
            knight.setLocation(new Point(knight.getLocation().x, this.location.y - k.getSize().height));
    }


}