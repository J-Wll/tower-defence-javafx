package TowerDefenceGame;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 *
 * @author user
 */
public class GameWindow extends Pane {

    private static int WIDTH, HEIGHT;
    private final Level level = new Level();
    private final Save save = new Save();
    private final Canvas canvas;
    private final GraphicsContext gc;

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
                System.out.println("Handling event " + event.getEventType());
            }
        };

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler);
        gc = canvas.getGraphicsContext2D();

//        (calling get children of the pane)
        getChildren().add(canvas);
        save.loadSave(level, new File("./src/main/resources/level1.save"));
    }

    /**
     *
     */
    public void start() {
        final long startNanoTime = System.nanoTime();
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

                Monster mon = new Monster(level);

                gc.clearRect(0, 0, WIDTH, HEIGHT);

                gc.setFill(Color.LIGHTGREY);
                gc.fillRect(0, 0, WIDTH, HEIGHT);

                level.render(gc);
                mon.render(gc);

                gc.setFill(Color.BLUE);
                gc.fillRect(0 + (t * 50), 0, 10, 30);

            }
        }.start();

    }
}
