package stairs;

import model.Sprite;
import knight.Knight;
import model.SpriteShape;
// import fsm.ImageRenderer;
import java.awt.*;


public class Stair extends Sprite{
    private final SpriteShape shape;
    private int width;
    private int height;

    public Stair(Point location, int width, int height) {
        this.location = location;
       
        this.width = width;
        this.height = height;

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
        g.setColor(Color.BLACK);
        g.fillRect(location.x, location.y, width, height);
        
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