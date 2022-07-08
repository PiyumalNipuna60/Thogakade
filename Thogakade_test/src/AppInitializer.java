import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
//        Parent parent = FXMLLoader.load(getClass().getResource("views/DashBoardForm.fxml"));
//        primaryStage.setScene(new Scene(parent));
//        primaryStage.show();

        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("views/DashBoardForm.fxml"))));
        primaryStage.show();
    }
}
