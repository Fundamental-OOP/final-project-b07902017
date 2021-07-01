package model;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


// import static java.util.Arrays.stream;
import java.util.*;

// import model.Sprite;

import stairs.*;


import border.*;
import child.Child;

// generates random stairs
import java.util.Random;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class World {
    private final List<Sprite> players = new CopyOnWriteArrayList<>();
    private final List<Stair> stairs = new CopyOnWriteArrayList<>();
    private StairGenerator generator;
    // private final CollisionHandler collisionHandler;
    private int height = 800;
    private int width = 800;
    private Random r1 = new Random(System.currentTimeMillis());
    private List <Border> borders;
    private int levelCount = 0;
    // set seed = 0 for debugging

    // public World(CollisionHandler collisionHandler, Sprite... players) {
  
    public World(int playernum, ArrayList<Stair> stairs, int height, int width, ArrayList<Border> borders) {
        this.height = height;
        this.width = width;
        this.borders = borders;
        for (int i = 0; i < playernum; i++){
            Sprite player = new Child(new Point(200, 100));
            this.players.add(player);
            players.get(i).setWorld(this);
        }
        for (int i = 0; i < stairs.size(); i++){
            this.stairs.add(stairs.get(i));
            stairs.get(i).setWorld(this);
        }
        int[] pos = {5,1,1,1,1,1};
        this.generator = new StairGenerator(pos);
    }

    public void update() {
        while(stairs.get(0).location.getY() < -100) {
            stairs.remove(0);
            levelCount++;
            if (levelCount%10==0){
                int l = levelCount/10;
                int[] pos = {50+3*l,20+5*l,10+l,10+l,10+l,10+l};
                generator.setpossibility(pos);
            }

            
        }
        while(stairs.size() < 20) {
            int dy = 50 + r1.nextInt(30);
            int x = r1.nextInt(width-100);
            stairs.add(generator.getStair(new Point(x, stairs.get(stairs.size()-1).getY() + dy)));
        }

        for (Sprite from : players){
            from.update();
        }
        for (Stair stair : stairs){
            stair.update();
        }

        for (Sprite from : players) {
            Rectangle body = from.getBody();
            Point originalLocation = new Point(from.getLocation());

            for (Stair stair : stairs){
                if (body.intersects(stair.getBody())){
                    stair.collisionHandle(originalLocation, stair, from);
                }
            }

            for(Border border:borders) {
                if (body.intersects(border.getBody())){
                    border.collisionHandle(originalLocation, border, from);
                }
            }
        }        
    }
    public void removeSprite(Sprite sprite) {
        players.remove(sprite);
        sprite.setWorld(null);
    }

    public void move(Sprite from, Dimension offset) {
        from.getLocation().translate(offset.width, offset.height);
        Point originalLocation = new Point(from.getLocation());
        Rectangle body = from.getBody();
        for (Sprite to : players) {
            if (to != from && body.intersects(to.getBody())) {
                to.collisionHandle(originalLocation, from, to);
            }
        }

    }

    public Sprite getPlayer(int index) {
        return players.get(index);
    }

    public List<Sprite> getPlayers() {
        return players;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void render(Graphics g) {
        for (Sprite sprite : players) {
            sprite.render(g);
        }
        for (Stair stair : stairs){
            stair.render(g);
        }
        for (Border border : borders) {
            border.render(g);
        }
    }
}
