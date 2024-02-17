package j.tower.defence.javafx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();
//        final var scene = new Scene(root, 600, 600);
        final var screen = new Display(root, 600, 600);

//        var label = new Label("Hello, JavaFX ");
//        root.getChildren().add(label);
//        root.getChildren().add(screen);
        screen.start();
        stage.setScene(screen);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
