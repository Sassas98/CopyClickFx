package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color:rgb(31, 27, 27);");
        Circle circle = new Circle(400, 50, 350);
        circle.setFill(Color.BLACK);
        GaussianBlur blur = new GaussianBlur(100);
        circle.setEffect(blur);
        pane.getChildren().add(circle);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0.95); 
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void run(String[] args) {
        launch();
    }
}

