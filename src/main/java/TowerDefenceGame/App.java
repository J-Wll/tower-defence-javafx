package TowerDefenceGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class App extends Application {

//    1152 = 64*18
    private final static int WIDTH = 1152, HEIGHT = 800;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();
        final var screen = new GameWindow(WIDTH, HEIGHT);
        root.getChildren().add(screen);

        Button button = new Button("Click Me");
        button.setLayoutY(750);
        root.getChildren().add(button);

        final var scene = new Scene(root, WIDTH, HEIGHT);

        screen.start();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
