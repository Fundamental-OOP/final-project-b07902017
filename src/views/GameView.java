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
    private int numPlayer;

    private final Canvas canvas = new Canvas();
    private Game game;

    private int score1, score2;

    public GameView(Game game) throws HeadlessException {
        this.game = game;
        game.setView(canvas);
        this.numPlayer = game.numPlayer();
        score1 = score2 = 0;
    }

    public GameView(Game game, int height, int width) throws HeadlessException {
        GameView.HEIGHT = height;
        GameView.WIDTH = width;
        this.game = game;
        game.setView(canvas);
        this.numPlayer = game.numPlayer();
        score1 = score2 = 0;
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
                        game.moveChild(P0, Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.moveChild(P0, Direction.RIGHT);
                        break;    

                    case KeyEvent.VK_A:
                        game.moveChild(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.moveChild(P1, Direction.RIGHT);
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        game.stopChild(P0, Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.stopChild(P0, Direction.RIGHT);
                        break;  

                    case KeyEvent.VK_A:
                        game.stopChild(P1, Direction.LEFT);
                        break;
                    case KeyEvent.VK_D:
                        game.stopChild(P1, Direction.RIGHT);
                        break;
                }
            }
        });
    }

    public void launch() {
        Game menu = this.game;
        // GUI Stuff
        setTitle("小朋友下樓梯");
        canvas.setLayout(new GridBagLayout());
        JLabel jlabel = new JLabel("小朋友下樓梯");
        Font font = new Font("Verdana",1,64);
        jlabel.setFont(font);
        jlabel.setForeground(Color.LIGHT_GRAY);
        
        GridBagConstraints cons1 = new GridBagConstraints();
        cons1.gridx = 0;
        cons1.gridy = 0;
        GridBagConstraints cons2 = new GridBagConstraints();
        cons2.gridx = 0;
        cons2.gridy = 1;
        GridBagConstraints cons3 = new GridBagConstraints();
        cons3.gridx = 0;
        cons3.gridy = 2;
        canvas.add(jlabel, cons1);

        GridBagConstraints cons4 = new GridBagConstraints();
        cons4.gridx = 0;
        cons4.gridy = 3;
        GridBagConstraints cons5 = new GridBagConstraints();
        cons5.gridx = 0;
        cons5.gridy = 4;
        JLabel scoreboard1 = new JLabel("Player 1's score: 0");
        JLabel scoreboard2 = new JLabel("Player 2's score: 0");
        JLabel historyScoreboard1 = new JLabel("Player 1's score: " + Integer.toString(score1));
        JLabel historyScoreboard2 = new JLabel("Player 2's score: " + Integer.toString(score2));
        scoreboard1.setFont(new Font("Verdana",1,20));
        scoreboard1.setForeground(Color.LIGHT_GRAY);
        scoreboard2.setFont(new Font("Verdana",1,20));
        scoreboard2.setForeground(Color.LIGHT_GRAY);
        historyScoreboard1.setFont(new Font("Verdana",1,20));
        historyScoreboard1.setForeground(Color.LIGHT_GRAY);
        historyScoreboard2.setFont(new Font("Verdana",1,20));
        historyScoreboard2.setForeground(Color.LIGHT_GRAY);
        canvas.add(historyScoreboard1, cons4);
        canvas.add(historyScoreboard2, cons5);

        CardLayout card = new CardLayout();
        JPanel canvases = new JPanel(card);
        JButton btn1 = new JButton(" 1 player ");
        btn1.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) {
                score1 = score2 = 0;
                scoreboard1.setText("Player 1's score: 0");
                ArrayList <Border> borders = setBorders(HEIGHT, WIDTH);
                ArrayList<Stair> stairs = new ArrayList<Stair>();
                stairs.add(new NormalStair(new Point(200, 300), 1));
                World world = new World(1, stairs, HEIGHT, WIDTH, borders);  // model
                Game newGame = new Game(world, stairs, false);
                Canvas newCanvas = new Canvas();
                newGame.setView(newCanvas);
                newCanvas.add(scoreboard1);
                game = newGame;
                numPlayer = 1;
                newGame.start();
                canvases.add("1 player", newCanvas);
                card.show(canvases, "1 player");
                setKeyAdapter();
                requestFocus();
            }
        });
        canvas.add(btn1, cons2);

        JButton btn2 = new JButton("2 players");
        btn2.addActionListener(new ActionListener(){  
            @Override
            public void actionPerformed(ActionEvent e) {
                score1 = score2 = 0;
                scoreboard1.setText("Player 1's score: 0");
                scoreboard2.setText("Player 2's score: 0");
                ArrayList <Border> borders = setBorders(HEIGHT, WIDTH);
                ArrayList<Stair> stairs = new ArrayList<Stair>();
                stairs.add(new NormalStair(new Point(200, 300), 1));
                World world = new World(2, stairs, HEIGHT, WIDTH, borders);  // model
                Game newGame = new Game(world, stairs, false);
                Canvas newCanvas = new Canvas();
                newCanvas.add(scoreboard1);
                newCanvas.add(scoreboard2);
                newGame.setView(newCanvas);
                numPlayer = 2;
                game = newGame;
                newGame.start();
                canvases.add("2 players", newCanvas);
                card.show(canvases, "2 players");
                
                setKeyAdapter();
                requestFocus();
            }
        });
        canvas.add(btn2, cons3);

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
                    if(numPlayer >= 1) {
                        scoreboard1.setText(
                            "Player 1's score: "
                            + Integer.toString(game.getPlayer(P0).score)
                        );
                        score1 = game.getPlayer(P0).score;
                    }
                    if(numPlayer >= 2) {
                        scoreboard2.setText("Player 2's score: "
                            + Integer.toString(game.getPlayer(P1).score));
                        score2 = game.getPlayer(P1).score;
                    }
                    
                }catch(Exception e) {

                }
            }while(!this.game.isEnd());
            this.game.stop();
            card.show(canvases, "menu");
            historyScoreboard1.setText("Player 1's score: "+Integer.toString(score1));
            historyScoreboard2.setText("Player 2's score: "+Integer.toString(score2));
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
