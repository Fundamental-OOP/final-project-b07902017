package controller;

import child.Child;
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
    private ArrayList<Sprite> players;
    private ArrayList<Stair> stairs;
    private final World world;
    private boolean isMenu;

    public Game(World world, ArrayList<Sprite> players, ArrayList<Stair> stairs, boolean isMenu) {
        this.players = new ArrayList<Sprite>();
        for (int i = 0; i < players.size(); i++)
            this.players.add(players.get(i));

        this.world = world;
        // this.stairs = stairs;
        this.stairs = new ArrayList<Stair>();
        for (int i = 0; i < stairs.size(); i++)
            this.stairs.add(stairs.get(i));
        this.isMenu = isMenu;
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

    public HealthPointSprite getPlayer(int playerNumber) {
        return (HealthPointSprite) players.get(playerNumber);
    }

    public boolean isEnd() {
        if(!isMenu && getWorld().getSprites().size() == 0)
            return true;
        return false;
    }

    public int numPlayer() {
        return players.size();
    }

    @Override
    protected World getWorld() {
        return world;
    }
}
