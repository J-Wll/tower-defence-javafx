package TowerDefenceGame;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author user
 */
public class App extends Application {

    private final static int LEVELCOUNT = 3;
//    1152 = 64*18
    private final static int WIDTH = 1152, HEIGHT = 830;
    private final static int STARTHP = 100, STARTGOLD = 150;
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

    /**
     *
     * @param to
     * @param text
     */
    public void setLevel(int to, String text) {
        if (to > LEVELCOUNT) {
            setLevel(1, "All levels completed\nWell done\nRestarting.....");
        }

        currentLevel = to;
//        Cleanup
        GameWindow gameWindow = GameWindow.getInstance();
        gameWindow.clean();
        root.getChildren().clear();
        GameWindow.deleteInstance();
        gameWindow = null;

//        End screen
        var label = new Label(text);
        label.setStyle("-fx-text-fill: white");
        label.setLayoutX(50);
        label.setLayoutY(50);
        var endLevelCanvas = new Canvas(WIDTH, HEIGHT);
        var endLevelGc = endLevelCanvas.getGraphicsContext2D();
        endLevelGc.clearRect(0, 0, WIDTH, HEIGHT);
        endLevelGc.setFill(Color.BLACK);
        endLevelGc.fillRect(0, 0, WIDTH, HEIGHT);
        root.getChildren().add(endLevelCanvas);
        root.getChildren().add(label);

//        Way of adding delay without causing issues (in contrast to thread.sleep)
//        Done so the end screen can be read 
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                root.getChildren().clear();
                addGameWindow();
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}
