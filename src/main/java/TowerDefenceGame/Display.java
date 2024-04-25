package TowerDefenceGame;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Display extends Pane {

    private static int WIDTH, HEIGHT;
    private final Level level = new Level();
    private final Canvas canvas;
    private final GraphicsContext gc;

    public Display(int x, int y) {
        WIDTH = x;
        HEIGHT = y;
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        getChildren().add(canvas);
    }

    public void start() {
        final long startNanoTime = System.nanoTime();

//        game loop
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
//                time in seconds
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                gc.clearRect(0, 0, WIDTH, HEIGHT);

                gc.setFill(Color.GREY);
                gc.fillRect(0, 0, WIDTH, HEIGHT);

                level.render(gc);

                gc.setFill(Color.BLUE);
                gc.fillRect(100 + t * 10, 100, 100, 100);

                System.out.println(t);

            }
        }.start();

    }
}
