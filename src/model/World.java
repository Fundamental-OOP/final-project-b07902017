package model;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import jdk.javadoc.internal.tool.Start;
import knight.Knight;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import java.util.*;

import stairs.Stair;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class World {
    private final List<Sprite> sprites = new CopyOnWriteArrayList<>();
    // private final CollisionHandler collisionHandler;
    private int height = 1000;
    private int width = 1000;

    // public World(CollisionHandler collisionHandler, Sprite... sprites) {
  
    public World(CollisionHandler collisionHandler, ArrayList<Sprite> spriteList, int height, int width) {
        this.height = height;
        this.width = width;
        // this.collisionHandler = collisionHandler;
        // addSprites(sprites);
        for (int i = 0; i < spriteList.size(); i++){
            this.sprites.add(spriteList.get(i));
            spriteList.get(i).setWorld(this);
        }
        // for (a123 : spriteList){
        //     this.sprites.add(a123);
        //     a123.setWorld(this);
        // }
    }
    // public void addSprites(ArrayList<Sprite> spri) {


    public void update() {
        // for (int i = 0; i < sprites.size(); i++){
        //     Sprite from = sprites.get(i);
        //     from.update();
        //     Rectangle body = from.getBody();
        //     for (int j = i + 1; j < sprites.size(); j++){
        //         Sprite to = sprites.get(j);
        //         if (to != from && body.intersects(to.getBody())) {
        //             Point originalLocation = new Point(from.getLocation());
        //             if (from instanceof Knight && to instanceof Knight)
        //                 to.collisionHandle(originalLocation, from, to);
        //             else if (from instanceof Stair)
        //                 from.collisionHandle(originalLocation, from, to);
        //             else if (to instanceof Stair)
        //                 to.collisionHandle(originalLocation, to, from);
        //         }
        //     }
        // }


        for (Sprite from : sprites) {
            from.update();
            Rectangle body = from.getBody();
            for (Sprite to : sprites) {
                if (to != from && body.intersects(to.getBody())) {
                    Point originalLocation = new Point(from.getLocation());
                    if (from instanceof Knight && to instanceof Knight)
                        to.collisionHandle(originalLocation, from, to);
                    else if (from instanceof Stair)
                        from.collisionHandle(originalLocation, from, to);
                    else if (to instanceof Stair)
                        to.collisionHandle(originalLocation, to, from);
                    // to.collisionHandle(originalLocation, from, to);
                }
            }
        }
    }

    // public void addSprites(Sprite... sprites) {
    //     stream(sprites).forEach(this::addSprite);
    // }

    // public void addSprite(Sprite sprite) {
    //     sprites.add(sprite);
    //     sprite.setWorld(this);
    // }

    public void removeSprite(Sprite sprite) {
        sprites.remove(sprite);
        sprite.setWorld(null);
    }

    public void move(Sprite from, Dimension offset) {
        // Point originalLocation = new Point(from.getLocation());
        from.getLocation().translate(offset.width, offset.height);


        // Rectangle body = from.getBody();
        // for (Sprite to : sprites) {
        //     if (to != from && body.intersects(to.getBody())) {
        //         to.collisionHandle(originalLocation, from, to);
        //     }
        // }
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
    }
}
