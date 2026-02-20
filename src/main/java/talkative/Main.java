package talkative;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Talkative using FXML.
 */
public class Main extends Application {
    /**
     * The Talkative instance that handles user input and generates responses.
     */
    private Talkative talkative = new Talkative();

    /**
     * Starts the JavaFX application by loading the main window layout
     * and displaying it on the given stage.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            stage.setScene(scene);
            stage.setTitle("Talkative Bot");

            fxmlLoader.<MainWindow>getController().setDuke(talkative); // inject the Talkative instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
