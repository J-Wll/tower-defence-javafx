package game;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Display extends Pane {
    
    private static int width, height;
    private final Canvas canvas;
    private final GraphicsContext gc;

    public Display(int width, int height, Canvas canvas, GraphicsContext gc) {
        this.width = width;
        this.height = height;
        this.canvas = canvas;
        this.gc = gc;
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

                gc.clearRect(0, 0, width, height);
                gc.setFill(Color.BLUE);
                gc.fillRect(100 + t * 10, 100, 100, 100);

                System.out.println(t);

            }
        }.start();

    }
}
