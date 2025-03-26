package fx;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        double w = 80 + (label.length() * 10);
        Button copyButton = new Button(label);
        copyButton.setPrefSize(w, 100);
        copyButton.setStyle(
            "-fx-font-size: 22px;" +
            "-fx-text-fill: white;" +
            "-fx-background-color: black;"
        );
        
        copyButton.setOnMousePressed(e -> copyButton.setStyle("-fx-background-color: #222222; -fx-text-fill: white; -fx-font-size: 22px;"));
        copyButton.setOnMouseReleased(e -> copyButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 22px;"));
        copyButton.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(value);
            clipboard.setContent(content);
        });

        VBox vbox = new VBox(0, copyButton);
        Scene scene = new Scene(vbox, w, 100);
        primaryStage.initStyle(StageStyle.UTILITY);
        double x = getXY(0);
        double y = getXY(1);
        if(xy_valid){
            primaryStage.setX(x);
            primaryStage.setX(y);
        }
        primaryStage.setOpacity(0.9); 
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double getXY(int i){
        try {
            String[] split = xy.split("_");
            return Double.parseDouble(split[i]);
        } catch (Exception e) {
            xy_valid = false;
            return 0;
        }
    }

    public static String value = "", label = "Copy", xy = "0_0";
    private boolean xy_valid = true;

    public static void run(String[] args) throws Exception {
        if(args.length > 0){
            Path path = Paths.get(args[0]);
            List<String> lines = Files.readAllLines(path);
            App.value = lines.get(0);
            App.label = lines.get(1);
            App.xy = lines.get(2);
        }
        launch();
    }
}
