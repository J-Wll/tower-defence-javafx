package j.tower.defence.javafx;

import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Display extends Scene {

    public Display(Parent p, double x, double y) {
        super(p, x, y);
        setFill(Color.BLUE);
    }

    public void start() {
        final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
//                time in seconds
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                System.out.println(t);
            }
        }.start();

    }
}
