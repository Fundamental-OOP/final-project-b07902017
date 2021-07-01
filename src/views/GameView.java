package views;

import controller.Game;
import controller.GameLoop;
import model.Direction;
import model.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import border.*;
import child.*;
import stairs.*;
import model.Sprite;
import java.io.File;
import javax.imageio.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class GameView extends JFrame {
    public static int HEIGHT = 800;
    public static int WIDTH = 800;
    public static final int P0 = 0;
    public static final int P1 = 1;
    public static final int P2 = 2;

    private final Canvas canvas = new Canvas();
    private Game game;

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        game.setView(canvas);
    }

    public GameView(Game game, int height, int width) throws HeadlessException {
        GameView.HEIGHT = height;
        GameView.WIDTH = width;
        this.game = game;
        game.setView(canvas);
    
    }

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

    public void setKeyAdapter() {
         // Keyboard listener
         addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.moveKnight(P0, Direction.LEFT);
                        game.moveChild(P0, Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.moveKnight(P0, Direction.RIGHT);
                        game.moveChild(P0, Direction.RIGHT);
                        break;    

                    case KeyEvent.VK_A:
                        game.moveKnight(P1, Direction.LEFT);
                        game.moveChild(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.moveKnight(P1, Direction.RIGHT);
                        game.moveChild(P1, Direction.RIGHT);
                        break;

                    // case KeyEvent.VK_J:
                    //     game.moveKnight(P2, Direction.LEFT);
                    //     game.moveChild(P2, Direction.LEFT);
                    //     break;
                    // case KeyEvent.VK_L:
                    //     game.moveKnight(P2, Direction.RIGHT);
                    //     game.moveChild(P2, Direction.RIGHT);
                    //     break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.stopKnight(P0, Direction.LEFT);
                        game.stopChild(P0, Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.stopKnight(P0, Direction.RIGHT);
                        game.stopChild(P0, Direction.RIGHT);
                        break;  

                    case KeyEvent.VK_A:
                        game.stopKnight(P1, Direction.LEFT);
                        game.stopChild(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.stopKnight(P1, Direction.RIGHT);
                        game.stopChild(P1, Direction.RIGHT);
                        break;
                    // case KeyEvent.VK_J:
                    //     game.stopKnight(P2, Direction.LEFT);
                    //     game.stopChild(P2, Direction.LEFT);
                    //     break;
                    // case KeyEvent.VK_L:
                    //     game.stopKnight(P2, Direction.RIGHT);
                    //     game.stopChild(P2, Direction.RIGHT);
                    //     break;
                }
            }
        });
    }

    public void launch() {
        Game menu = this.game;
        // GUI Stuff
        setTitle("小朋友下樓梯");
        // canvas.setLayout(new GridLayout(3, 3, 50, 50));
        // JLabel jlabel = new JLabel("小朋友下樓梯");
        // jlabel.setFont(new Font("Verdana",1,20));
        // canvas.add(jlabel);

        CardLayout card = new CardLayout();
        JPanel canvases = new JPanel(card);
        JButton btn1 = new JButton("1 player");
        btn1.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList <Border> borders = setBorders(HEIGHT, WIDTH);
                ArrayList<Stair> stairs = new ArrayList<Stair>();
                stairs.add(new NormalStair(new Point(200, 300), 1));
                ArrayList<Sprite> players = new ArrayList<Sprite>();
                players.add(new Child(new Point(200, 100)));
                World world = new World(players, stairs, HEIGHT, WIDTH, borders);  // model
                Game newGame = new Game(world, players, stairs, false);
                Canvas newCanvas = new Canvas();
                newGame.setView(newCanvas);
                game = newGame;
                newGame.start();
                canvases.add("1 player", newCanvas);
                card.show(canvases, "1 player");
                setKeyAdapter();
                requestFocus();
            }
        });
        canvas.add(btn1);

        JButton btn2 = new JButton("2 players");
        btn2.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList <Border> borders = setBorders(HEIGHT, WIDTH);
                ArrayList<Stair> stairs = new ArrayList<Stair>();
                stairs.add(new NormalStair(new Point(200, 300), 1));
                ArrayList<Sprite> players = new ArrayList<Sprite>();
                players.add(new Child(new Point(200, 100)));
                players.add(new Child(new Point(200, 100)));
                World world = new World(players, stairs, HEIGHT, WIDTH, borders);  // model
                Game newGame = new Game(world, players, stairs, false);
                Canvas newCanvas = new Canvas();
                newGame.setView(newCanvas);
                game = newGame;
                newGame.start();
                canvases.add("2 players", newCanvas);
                card.show(canvases, "2 players");
                setKeyAdapter();
                requestFocus();
            }
        });
        canvas.add(btn2);

        canvas.setSize(WIDTH, HEIGHT);
        canvases.add(canvas, "menu");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(canvases);
        setSize(WIDTH, HEIGHT);
        setContentPane(canvases);
        setVisible(true);
        while(true) {
            do{
                // System.out.println(this.game.isEnd());)
                try{
                    Thread.sleep(500);
                }catch(Exception e) {

                }
            }while(!this.game.isEnd());
            this.game.stop();
            card.show(canvases, "menu");
            this.game = menu;
        }
    }

    public static class Canvas extends JPanel implements GameLoop.View {
        private World world;

        @Override
        public void render(World world) {
            this.world = world;
            repaint(); // ask the JPanel to repaint, it will invoke paintComponent(g) after a while.
        }

        @Override
        protected void paintComponent(Graphics g /*paintbrush*/) {
            super.paintComponent(g);
            // Now, let's paint
            g.setColor(Color.BLACK); // paint background with all white
            g.fillRect(0, 0, GameView.WIDTH, GameView.HEIGHT);

            world.render(g); // ask the world to paint itself and paint the sprites on the canvas
        }
    }
}
