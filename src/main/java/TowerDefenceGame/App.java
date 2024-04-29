package TowerDefenceGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class App extends Application {

//    1152 = 64*18
    private final static int WIDTH = 1152, HEIGHT = 830;
    private final static int STARTHP = 100, STARTGOLD = 150;

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();

        final var gameWindow = GameWindow.getInstance(WIDTH, HEIGHT, root, STARTHP, STARTGOLD);
        root.getChildren().add(gameWindow);

        final var scene = new Scene(root, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        gameWindow.start();
        stage.setScene(scene);
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
