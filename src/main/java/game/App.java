package game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class App extends Application {

    private static int width = 600;
    private static int height = 600;
    private final Canvas canvas = new Canvas(width, height);
    private final GraphicsContext gc = canvas.getGraphicsContext2D();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();
        final var screen = new Display(width, height, canvas, gc);
        root.getChildren().add(screen);
        final var scene = new Scene(root, 600, 600);

        screen.start();
        stage.setScene(scene);
        stage.show();

        final var level = new Level();
    }

    public static void main(String[] args) {
        launch();
    }

}
