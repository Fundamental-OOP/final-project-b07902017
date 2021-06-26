import controller.Game;

import knight.Knight;
import knight.KnightCollisionHandler;
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

/**
 * Demo route: Main, GameView, Game, GameLoop, World, Sprite, Knight, FiniteStateMachine
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        addAudioByFilePath(Walking.AUDIO_STEP1, new File("assets/audio/step1.wav"));
        addAudioByFilePath(Walking.AUDIO_STEP2, new File("assets/audio/step2.wav"));
        addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_1, new File("assets/audio/sword-clash1.wav"));
        addAudioByFilePath(Attacking.AUDIO_SWORD_CLASH_2, new File("assets/audio/sword-clash2.wav"));
        addAudioByFilePath(HealthPointSprite.AUDIO_DIE, new File("assets/audio/die.wav"));

        // initialization procedure
        int pNum = 3;
        ArrayList<Sprite> players = new ArrayList<Sprite>();
        if (pNum == 1)
            players.add(new Knight(100, new Point(200, 200)));
        else if (pNum == 2){
            players.add(new Knight(100, new Point(0, 0)));
            players.add(new Knight(150, new Point(300, 300)));
        }
        else if (pNum == 3){
            players.add(new Knight(100, new Point(0, 0)));
            players.add(new Knight(150, new Point(300, 300)));
            players.add(new Knight(150, new Point(600, 600)));

        }
         
        int height = 800;
        int width = 800;

        World world = new World(new KnightCollisionHandler(), players, height, width);  // model
        Game game = new Game(world, players);  // controller

        GameView view = new GameView(game, height, width);  // view
        game.start();  // run the game and the game loop
        view.launch(); // launch the GUI
    }
}
