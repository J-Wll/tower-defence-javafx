package TowerDefenceGame;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class GameWindow extends Pane {

    private static int WIDTH, HEIGHT;
    private final Level level;
    private final Save save = new Save();
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final Canvas gameOverCanvas;
    private final GraphicsContext gameOverGc;
    private Boolean gameOverShown = false;
    private final Textures textures = new Textures();
    private Boolean mouseItemActive = false;
    private Tower mouseTower;
    private double mouseX, mouseY;
    private final GameStatePublisher gameManager = GameStatePublisher.getInstance();
    private Group root;

    /**
     *
     * @param x
     * @param y
     */
    public GameWindow(int x, int y, Group root) {
        WIDTH = x;
        HEIGHT = y;
        canvas = new Canvas(WIDTH, HEIGHT);
        gameOverCanvas = new Canvas(WIDTH, HEIGHT);
        gameOverGc = gameOverCanvas.getGraphicsContext2D();

        this.root = root;

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("Handling event " + event.getEventType());
                mouseX = event.getX();
                mouseY = event.getY();

//                If it's a click and in place mode
                if ("MOUSE_CLICKED".equals(event.getEventType().toString()) && mouseItemActive && mouseY < 640) {
                    Tile[][] tilegrid = level.getTileGrid();
                    Tile clickedTile = tilegrid[(int) (mouseY / 64)][(int) (mouseX / 64)];
                    if (clickedTile.getGroundID() != Values.path && clickedTile.getAirID() == Values.empty) {
                        System.out.println(clickedTile.getGroundID());
                        System.out.println(clickedTile.getAirID());
                        clickedTile.setTower(mouseTower);
                        mouseItemActive = false;
                    }
                }
            }
        };

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, handler);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        gc = canvas.getGraphicsContext2D();
        level = new Level(gc, textures);

//        (calling get children of the pane)
        getChildren().add(canvas);
        save.loadSave(level, new File("./src/main/resources/level1.save"));
    }

    public void setMouseItem(Boolean mouseItem, Tower mouseTower) {
        this.mouseItemActive = mouseItem;
        this.mouseTower = mouseTower;
    }

    public Boolean getMouseItemActive() {
        return mouseItemActive;
    }

    public Textures getTextures() {
        return this.textures;
    }

    private void showGameOver() {
        if (!gameOverShown) {
            var label = new Label("GAME OVER\nThanks for playing");
            label.setStyle("-fx-text-fill: white");
            label.setLayoutX(50);
            label.setLayoutY(50);
            root.getChildren().add(gameOverCanvas);
            gameOverShown = true;
            root.getChildren().add(label);
        }
        gameOverGc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.setFill(Color.WHITE);
        gameOverGc.fillRect(0, 0, WIDTH, HEIGHT);
    }

    /**
     *
     */
    public void start() {
        final long startNanoTime = System.nanoTime();
//        testing for fiding filepaths
//        File file = new File("./src/main/resources");
//        for (String fileNames : file.list()) {
//            System.out.println(fileNames);
//        }

//        game loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
//                time in seconds
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//                System.out.println(t);

                gc.clearRect(0, 0, WIDTH, HEIGHT);

//                background
                gc.setFill(Color.LIGHTGREY);
                gc.fillRect(0, 0, WIDTH, HEIGHT);

                if (gameManager.getHp() <= 0) {
                    showGameOver();
                } else {
                    level.render();
                }

                if (mouseItemActive) {
                    gc.drawImage(textures.getText().get(mouseTower.getValue()), mouseX - 32, mouseY - 32);
                }
            }
        }.start();

    }
}
