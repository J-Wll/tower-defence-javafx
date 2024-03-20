package game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();
        final var screen = new Display(600, 600);
        root.getChildren().add(screen);
        final var scene = new Scene(root, 600, 600);

        screen.start();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
