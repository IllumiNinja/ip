package talkative;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Talkative talkative = new Talkative();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window of the application.
     * <p>
     * This method is automatically called by JavaFX after the FXML components
     * have been loaded. It binds the scroll pane to always scroll to the bottom
     * as new dialog boxes are added, and displays the initial welcome message
     * from the application.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(talkative.getWelcomeMessage(), dukeImage)
        );
    }

    /** Injects the Talkative instance */
    public void setDuke(Talkative d) {
        talkative = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Talkative's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = talkative.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userImage),
            DialogBox.getDukeDialog(response, dukeImage)
        );

        if (response.contains("Bye")) {
            Platform.exit();
        }

        userInput.clear();
    }
}
