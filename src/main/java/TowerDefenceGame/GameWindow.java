package TowerDefenceGame;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private final Textures textures = new Textures();
    private Boolean mouseItemActive = false;
    private Image mouseImage;
    private double mouseX, mouseY;

    /**
     *
     * @param x
     * @param y
     */
    public GameWindow(int x, int y) {
        WIDTH = x;
        HEIGHT = y;
        canvas = new Canvas(WIDTH, HEIGHT);

        EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("Handling event " + event.getEventType());
                mouseX = event.getX();
                mouseY = event.getY();
            }
        };

        canvas.addEventHandler(MouseEvent.MOUSE_MOVED, handler);
        gc = canvas.getGraphicsContext2D();
        level = new Level(gc, textures);

//        (calling get children of the pane)
        getChildren().add(canvas);
        save.loadSave(level, new File("./src/main/resources/level1.save"));
    }

    public void setMouseItem(Boolean mouseItem, Image mouseImage) {
        this.mouseItemActive = mouseItem;
        this.mouseImage = mouseImage;
    }

    public Boolean getMouseItemActive() {
        return mouseItemActive;
    }

    public Textures getTextures() {
        return this.textures;
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

                level.render();

                if (mouseItemActive) {
                    gc.drawImage(mouseImage, mouseX - 32, mouseY - 32);
                }
            }
        }.start();

    }
}
