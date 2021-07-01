package model;

import knight.HealthPointBar;
import media.AudioPlayer;

import java.awt.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class HealthPointSprite extends Sprite {
    public static final String AUDIO_DIE = "Die";
    protected static final double acceleration = 0.15;
    protected static final double speedlimit = 3;
    protected double speed;
    
    protected HealthPointBar hpBar;

    public HealthPointSprite(int hp) {
        this.hpBar = new HealthPointBar(hp);
        hpBar.setOwner(this);
    }

    public void onHealed(int heal) {
        hpBar.onHealed(null, heal);
    }

    public void onDamaged(int damage) {
        hpBar.onDamaged(null, damage);
        if (hpBar.isDead()) {
            world.removeSprite(this);
            AudioPlayer.playSounds(AUDIO_DIE);
        }
    }

    public abstract Dimension getSize();

    @Override
    public void onHealed(Rectangle damageArea, int heal) {
        hpBar.onHealed(damageArea, heal);
    }

    @Override
    public void onDamaged(Rectangle damageArea, int damage) {
        hpBar.onDamaged(damageArea, damage);
        if (hpBar.isDead()) {
            world.removeSprite(this);
            AudioPlayer.playSounds(AUDIO_DIE);
        }
    }

    @Override
    public void render(Graphics g) {
        hpBar.render(g);
    }

    public void setspeed(int newspeed){
        this.speed = newspeed;
    }
    @Override
    public void update() {
        this.location.translate(0, (int) speed);
        if (speed < speedlimit) 
            speed += acceleration;
    }
}
