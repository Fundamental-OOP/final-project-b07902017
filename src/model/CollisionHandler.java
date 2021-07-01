package model;
import java.awt.*;

public interface CollisionHandler {
    public abstract void collisionHandle(Point originalLocation, Sprite from, Sprite to);
}
