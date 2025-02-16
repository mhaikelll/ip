package huhuhuharis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Huhuhuharis huhuhuharis;
    private Ui ui = new Ui();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image huhuhuharisImage = new Image(this.getClass().getResourceAsStream("/images/huhuhuharis.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setHuhuhuharis(Huhuhuharis h) {
        huhuhuharis = h;
        dialogContainer.getChildren().addAll(DialogBox.getHuhuhuharisDialog(ui.stringWelcomeMessage(), "huhuhuharis", huhuhuharisImage));
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = huhuhuharis.chatResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, "You", userImage),
                DialogBox.getHuhuhuharisDialog(response, "huhuhuharis", huhuhuharisImage)
        );
        userInput.clear();
        if (input.equals("bye")) {
            System.exit(0);
        }
    }
}

