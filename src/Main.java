import controller.Game;

import knight.Walking;
import knight.Attacking;

import model.HealthPointSprite;
import model.World;
import views.GameView;

import java.awt.*;
import java.io.File;

import static media.AudioPlayer.addAudioByFilePath;

import java.util.*; 
import model.Sprite;

import stairs.*;

import border.*;

import javax.imageio.ImageIO;

import child.Child;

/**
 * Demo route: Main, GameView, Game, GameLoop, World, Sprite, Knight, FiniteStateMachine
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static ArrayList <Border> setBorders(int height, int width) {
        ArrayList <Border> borders = new ArrayList<>();
        try{        
            File file = new File("assets/ceiling.png");
            Image image = ImageIO.read(file);
            Ceiling ceiling = new Ceiling(0, image.getHeight(null), width, 5, image);
            // System.out.println(image.getHeight(null));
            borders.add(ceiling);
        }catch(Exception e) {}
        try{
            File file = new File("assets/wall.png");
            Image image = ImageIO.read(file);
            Wall leftWall = new Wall(0, image.getWidth(null), width, image, Wall.Type.LEFT);
            Wall rightWall = new Wall(width - image.getWidth(null), width, width, image, Wall.Type.RIGHT);
            // System.out.println(image.getHeight(null));
            borders.add(leftWall);
            borders.add(rightWall);
        }catch(Exception e) {}
        Floor floor = new Floor(height, height+50, width);
        borders.add(floor);
        return borders;
    }
    public static void main(String[] args) {
        addAudioByFilePath(Walking.AUDIO_STEP1, new File("assets/audio/step1.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP2, new File("assets/audio/step2.wav"));
        addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_1, new File("assets/audio/sword-clash1.wav"));
        addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_2, new File("assets/audio/sword-clash2.wav"));
        addAudioByFilePath(HealthPointSprite.AUDIO_DIE, new File("assets/audio/die.wav"));

        // initialization procedure
        ArrayList<Sprite> players = new ArrayList<Sprite>();
        // players.add(new Child(new Point(200, 100)));
        
        int height = 800;
        int width = 800;
        ArrayList <Border> borders = setBorders(height, width);
        // borders.clear();

        // 這邊其實是加方塊不是家player
        ArrayList<Stair> stairs = new ArrayList<Stair>();
        stairs.add(new NormalStair(new Point(200, 300), 1));


        World world = new World(players, stairs, height, width, borders);  // model
        Game game = new Game(world, players, stairs, true);  // controller

        GameView view = new GameView(game, height, width);  // view
        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
    }
}
