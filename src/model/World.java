package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


// import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import java.util.*;

// import model.Sprite;

import stairs.Stair;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class World {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    private final List<Stair> stairs = new CopyOnWriteArrayList<>();
    // private final CollisionHandler collisionHandler;
    private int height = 1000;
    private int width = 1000;

    // public World(CollisionHandler collisionHandler, Sprite... sprites) {
  
    public World(ArrayList<Sprite> spriteList, ArrayList<Stair> stairs, int height, int width) {
        this.height = height;
        this.width = width;

        for (int i = 0; i < spriteList.size(); i++){
            this.sprites.add(spriteList.get(i));
            spriteList.get(i).setWorld(this);
        }
        for (int i = 0; i < stairs.size(); i++){
            this.stairs.add(stairs.get(i));
            stairs.get(i).setWorld(this);
        }

    }


    public void update() {
        for (Sprite from : sprites){
            from.update();
        }
        for (Stair stair : stairs){
            stair.update();
        }

        for (Sprite from : sprites) {
            // from.update();
            Rectangle body = from.getBody();
            Point originalLocation = new Point(from.getLocation());

            for (Stair stair : stairs){
                if (body.intersects(stair.getBody())){
                    stair.collisionHandle(originalLocation, stair, from);
                }
            }
        }

        // 底下要處理碰到牆壁天花版和地板
        for (Sprite sprite : sprites){
            Point location = new Point(sprite.getLocation());
            Dimension size = new Dimension(sprite.getBodySize());
            
            if (location.y < 0){
                sprite.setLocation(new Point(location.x, 0));

            }
            else if (location.y + size.height > height){

            }
            else if (location.x < 0){
                sprite.setLocation(new Point(0, location.y));

            } 
            else if (location.x + size.width > width){
                sprite.setLocation(new Point(width - size.width, location.y));
            }
        }
        
    }
    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setWorld(null);
    }

    public void move(Sprite from, Dimension offset) {
        from.getLocation().translate(offset.width, offset.height);
        Point originalLocation = new Point(from.getLocation());
        Rectangle body = from.getBody();
        for (Sprite to : sprites) {
            if (to != from && body.intersects(to.getBody())) {

                to.collisionHandle(originalLocation, from, to);
                // else if (from instanceof Stair)
                //     from.collisionHandle(originalLocation, from, to);
                // else if (to instanceof Stair)
                //     to.collisionHandle(originalLocation, to, from);
                // to.collisionHandle(originalLocation, from, to);
            }
        }

    }

    public Collection<Sprite> getSprites(Rectangle area) {
        return sprites.stream()
                .filter(s -> area.intersects(s.getBody()))
                .collect(toSet());
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    // Actually, directly couple your model with the class "java.awt.Graphics" is not a good design
    // If you want to decouple them, create an interface that encapsulates the variation of the Graphics.
    public void render(Graphics g) {
        for (Sprite sprite : sprites) {
            sprite.render(g);
        }
        for (Stair stair : stairs){
            stair.render(g);
        }
    }
}
