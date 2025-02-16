package huhuhuharis;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Huhuhuharis huhuhuharis = new Huhuhuharis();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Huhuhuharis Chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setHuhuhuharis(huhuhuharis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



