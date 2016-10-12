package avorders;

import avorders.controllers.WindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import avorders.utils.OrderIO;

public class Main extends Application {

    private static WindowController mainWindowController;
    
    public static WindowController getMainWindowController() {
        return mainWindowController;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("assets/window.fxml"));
        Pane mainWindow = loader.load();

        mainWindowController = loader.getController();

        primaryStage.setTitle("Beställningar");
        primaryStage.setScene(new Scene(mainWindow, 750, 700));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        OrderIO.loadFromFile();
        launch(args);
    }
}
