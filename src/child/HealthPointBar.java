package child;

import model.Sprite;

import java.awt.*;

/**
 * reference: https://github.com/Johnny850807/Java-Game-Programming-with-FSM-and-MVC
 */
public class HealthPointBar extends Sprite {
    private final int maxHp;
    private Sprite owner;
    private int hp;

    public HealthPointBar(int hp) {
        this.maxHp = this.hp = hp;
    }

    public void setOwner(Sprite owner) {
        this.owner = owner;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void onHealed(int heal) {
        this.hp = Math.min(hp + heal, maxHp);
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        Rectangle range = getRange();
        int width = (int) (hp * owner.getRange().getWidth() / maxHp);
        g.setColor(Color.RED);
        g.fillRect(range.x, range.y, (int) owner.getRange().getWidth(), range.height);
        g.setColor(Color.GREEN);
        g.fillRect(range.x, range.y, width, range.height);
    }

    public void onDamaged(int damage) {
        this.hp = Math.max(hp - damage, 0);
    }

    @Override
    public Rectangle getRange() {
        return new Rectangle(owner.getX(), owner.getY() - 15, (int) owner.getRange().getWidth(), 10);
    }

    @Override
    public Dimension getBodyOffset() {
        return new Dimension();
    }

    @Override
    public Dimension getBodySize() {
        return new Dimension();
    }

    public boolean isDead() {
        return hp <= 0;
    }
    public void collisionHandle(Point originalLocation, Sprite from, Sprite to){

    }

}
