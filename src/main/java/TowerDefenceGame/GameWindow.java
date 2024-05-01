package TowerDefenceGame;

import java.io.File;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author user
 */
public class GameWindow extends Pane {

    private static int WIDTH, HEIGHT;
    private Level level;
    private int currentLevel = 1;
    private Save save = new Save();
    private Canvas canvas;
    private GraphicsContext gc;
    private Boolean endLevelShown = false;
    private Textures textures = new Textures();
    private Boolean mouseItemActive = false;
    private Tower mouseTower;
    private double mouseX, mouseY;
    private GameStatePublisher gameManager = null;
    private Shop shop = null;
    private Group root;
    private App app;

    private Timeline gameLoop;
    EventHandler<MouseEvent> handler;
    private int endLevelCounter = 0;

    private static GameWindow instance = null;

    private GameWindow() {

    }

    public static GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }

    /**
     *
     */
    public static void deleteInstance() {
        instance = null;
        GameStatePublisher.deleteInstance();
    }

    /**
     *
     */
    public void clean() {
        gameLoop.stop();
        handler = null;
    }

    public void init(int x, int y, Group root, int STARTHP, int STARTGOLD, App app, int currentLevel) {
        WIDTH = x;
        HEIGHT = y;
        canvas = new Canvas(WIDTH, HEIGHT);
        this.currentLevel = currentLevel;
        this.app = app;

        this.root = root;

//        Manages health and gold values, sends events relating to them
//        these two are rendered at the bottom of start so they are on top of canvas
        this.gameManager = GameStatePublisher.getInstance();
        gameManager.init(STARTHP, STARTGOLD, currentLevel);
//        Can't use singleton because not intialised yet
        this.shop = new Shop(this);

        handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseX = event.getX();
                mouseY = event.getY();

//                If it's a click and in place mode
                if ("MOUSE_CLICKED".equals(event.getEventType().toString()) && mouseItemActive && mouseY < 640) {
                    Tile[][] tilegrid = level.getTileGrid();
                    Tile clickedTile = tilegrid[(int) (mouseY / 64)][(int) (mouseX / 64)];
//                    If valid to place and enough gold
                    if (clickedTile.getGroundID() != Values.path && clickedTile.getAirID() == Values.empty && gameManager.getGold() >= mouseTower.getCost()) {
                        clickedTile.setTower(mouseTower);
                        mouseTower.setPos(clickedTile.getX(), clickedTile.getY());
                        gameManager.decreaseGold(mouseTower.getCost());
//                        If not shift click or not enough gold, reset the placing, otherwise enable placing multiple
                        if (!event.isShiftDown() || gameManager.getGold() < mouseTower.getCost()) {
                            mouseItemActive = false;
                        } else {
//                            This line is needed otherwise it will place the same tower over and over again
                            mouseTower = shop.createTower(mouseTower.getValue());
                        }
                    }
                }
            }
        };

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, handler);

        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, handler);
        gc = canvas.getGraphicsContext2D();
        level = new Level(gc, textures, currentLevel);
        this.endLevelCounter = 0;

//        (calling get children of the pane)
        getChildren().add(canvas);

        File saveFile = new File("./src/main/resources/level" + currentLevel + ".save");
        save.loadSave(level, saveFile);

    }

    /**
     *
     * @param mouseItem
     * @param mouseTower
     */
    public void setMouseItem(Boolean mouseItem, Tower mouseTower) {
        this.mouseItemActive = mouseItem;
        this.mouseTower = mouseTower;
    }

    /**
     *
     * @return
     */
    public Boolean getMouseItemActive() {
        return mouseItemActive;
    }

    /**
     *
     * @return
     */
    public Textures getTextures() {
        return this.textures;
    }

    /**
     *
     * @return
     */
    public GraphicsContext getGc() {
        return gc;
    }

    /**
     *
     * @param level
     * @param text
     */
    public void newLevel(int level, String text) {
        app.setLevel(level, text);

    }

    /**
     *
     */
    public void update() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);

//      background
        gc.setFill(Color.LIGHTGREY);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        if (gameManager.getHp() >= 0) {
            level.render();
        }

        if (mouseItemActive) {
            gc.drawImage(textures.getText().get(mouseTower.getValue()), mouseX - 32, mouseY - 32);
        }
    }

    /**
     *
     */
    public void start() {
        final long startNanoTime = System.nanoTime();

//        game loop
        gameLoop = new Timeline(new KeyFrame(Duration.millis(1000.0 / 60), u -> update()));
        gameLoop.setCycleCount(Animation.INDEFINITE);
        gameLoop.play();
        shop.render(root);
        gameManager.render(root);
    }
}
