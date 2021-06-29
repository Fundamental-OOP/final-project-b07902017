package controller;

import knight.Knight;
import child.Child;
import child.HealthPointBar;
import model.Direction;
import model.World;
import model.HealthPointSprite;

import java.util.*;

import model.Sprite;
import stairs.Stair;
/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Game extends GameLoop {
    // private final Knight p1;
    // private final Knight p2;
    private ArrayList<Sprite> knights;
    private ArrayList<Stair> stairs;
    private final World world;

    public Game(World world, ArrayList<Sprite> knights, ArrayList<Stair> stairs) {
        this.knights = new ArrayList<Sprite>();
        for (int i = 0; i < knights.size(); i++)
            this.knights.add(knights.get(i));

        this.world = world;
        // this.stairs = stairs;
        this.stairs = new ArrayList<Stair>();
        for (int i = 0; i < stairs.size(); i++)
            this.stairs.add(stairs.get(i));
    }

    // public Game(World world, Knight p1, Knight p2) {
    //     this.p1 = p1;
    //     this.p2 = p2;
    //     this.world = world;
    // }

    public void moveKnight(int playerNumber, Direction direction) {
        HealthPointSprite sprite = getPlayer(playerNumber);
        if(sprite instanceof Knight)
            ((Knight) sprite).move(direction);
    }

    public void stopKnight(int playerNumber, Direction direction) {
        HealthPointSprite sprite = getPlayer(playerNumber);
        if(sprite instanceof Knight)
            ((Knight) sprite).stop(direction);
    }

    public void attack(int playerNumber) {
        HealthPointSprite sprite = getPlayer(playerNumber);
        if(sprite instanceof Knight)
            ((Knight) sprite).attack();
    }

    public void moveChild(int playerNumber, Direction direction) {
        HealthPointSprite sprite = getPlayer(playerNumber);
        if(sprite instanceof Child)
            ((Child) sprite).move(direction);
    }

    public void stopChild(int playerNumber, Direction direction) {
        HealthPointSprite sprite = getPlayer(playerNumber);
        if(sprite instanceof Child)
            ((Child) sprite).stop(direction);
    }


    // public Knight getPlayer(int playerNumber) {
    //     return playerNumber == 1 ? p1 : p2;
    // }

    public HealthPointSprite getPlayer(int playerNumber) {
        // return playerNumber == 1 ? knights.get(0) : knights.get(1);
        return (HealthPointSprite) knights.get(playerNumber);
    }

    @Override
    protected World getWorld() {
        return world;
    }
}
