package TowerDefenceGame;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class App extends Application {

//    1152 = 64*18
    private final static int WIDTH = 1152, HEIGHT = 800;

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");

        final var root = new Group();
        final var game = new GameWindow(WIDTH, HEIGHT);
        root.getChildren().add(game);

        final var shop = new Shop(root, game);
        shop.render();

        final var scene = new Scene(root, WIDTH, HEIGHT);

        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        game.start();
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
