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
    private final static int STARTHP = 1, STARTGOLD = 150;
    private int currentLevel = 1;
    final Group root = new Group();
    final Scene scene = new Scene(root, WIDTH, HEIGHT);

    /**
     *
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Tower Defence");
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        addGameWindow();
        stage.setScene(scene);
        stage.show();
    }

    private void addGameWindow() {
        GameWindow gameWindow = GameWindow.getInstance(WIDTH, HEIGHT, root, STARTHP, STARTGOLD, this, currentLevel);
        root.getChildren().add(gameWindow);
        gameWindow.start();
    }

    public void setLevel(int to) {
        currentLevel = to;
        GameWindow gameWindow = GameWindow.getInstance();
        gameWindow.clean();
        root.getChildren().clear();
        GameWindow.deleteInstance();
        gameWindow = null;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        addGameWindow();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
