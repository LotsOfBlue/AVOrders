import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static WindowController mainWindowController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Pane mainWindow = loader.load(getClass().getResource("window.fxml").openStream());

        mainWindowController = loader.getController();

        primaryStage.setTitle("Beställningar");
        primaryStage.setScene(new Scene(mainWindow, 800, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        OrderUtils.loadFromFile();
        launch(args);
    }
}
