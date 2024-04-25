package TowerDefenceGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

//    1152 = 64*18
    private final static int WIDTH = 1152, HEIGHT = 800;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();
        final var screen = new Display(WIDTH, HEIGHT);
        root.getChildren().add(screen);
        final var scene = new Scene(root, WIDTH, HEIGHT);

        screen.start();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
