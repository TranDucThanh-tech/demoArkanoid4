import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import com.example.demoarkanoid4.VARIABLES;
import com.example.demoarkanoid4.manager.*;
import javafx.scene.layout.*;
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GameManager gameManager = new GameManager();

        Scene scene = new Scene(gameManager, VARIABLES.WIDTH, VARIABLES.HEIGHT);

        stage.getIcons().add(new Image(getClass().getResource("/images/icon.png").toExternalForm())); //add window's icon
        stage.setTitle("Brick Breaker");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        InputHandler inputHandler = new InputHandler(
                gameManager.getPaddle(), gameManager.getBall()
        );
        inputHandler.input(scene);

        gameManager.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}