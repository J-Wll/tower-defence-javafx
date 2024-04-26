package TowerDefenceGame;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Display extends Pane {

    private static int WIDTH, HEIGHT;
    private final Level level = new Level();
    private final Save save = new Save();
    private final Canvas canvas;
    private final GraphicsContext gc;

    public Display(int x, int y) {
        WIDTH = x;
        HEIGHT = y;
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
        save.loadSave(level, new File("./src/main/resources/level1.save"));

    }

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

                gc.clearRect(0, 0, WIDTH, HEIGHT);

                gc.setFill(Color.GREY);
                gc.fillRect(0, 0, WIDTH, HEIGHT);

                level.render(gc);

                gc.setFill(Color.BLUE);
                gc.fillRect(100 + t * 10, 100, 100, 100);

            }
        }.start();

    }
}
