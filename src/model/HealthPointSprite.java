package model;

import child.HealthPointBar;
import media.AudioPlayer;

import java.awt.*;

/**
 * reference: https://github.com/Johnny850807/Java-Game-Programming-with-FSM-and-MVC
 */
public abstract class HealthPointSprite extends Sprite {
    public static final String AUDIO_DIE = "Die";
    protected static final double acceleration = 0.15;
    protected static final double speedlimit = 3;
    protected double speed;
    
    protected HealthPointBar hpBar;
    public int score;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
    }

    public void onHealed(int heal) {
        hpBar.onHealed(heal);
    }

    public void onDamaged(int damage) {
        hpBar.onDamaged(damage);
        if (hpBar.isDead()) {
            world.removeSprite(this);
            AudioPlayer.playSounds(AUDIO_DIE);
        }
    }

    public abstract Dimension getSize();

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }

    public void setspeed(int newspeed){
        this.speed = newspeed;
    }
    @Override
    public void update() {
        this.score = this.world.getLevelCount();
        this.location.translate(0, (int) speed);
        if (speed < speedlimit) 
            speed += acceleration;
    }
}
