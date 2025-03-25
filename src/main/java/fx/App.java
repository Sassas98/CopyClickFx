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
        String label = getLabel();
        double w = 100 + (label.length() * 10);
        Button copyButton = new Button(label);
        copyButton.setPrefSize(w, 100);
        copyButton.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-text-fill: white;" +
            "-fx-background-color: black;"
        );
        
        copyButton.setOnMousePressed(e -> copyButton.setStyle("-fx-background-color: #222222; -fx-text-fill: white; -fx-font-size: 20px;"));
        copyButton.setOnMouseReleased(e -> copyButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20px;"));
        copyButton.setOnAction(event -> {
            if (args.length > 0) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(getValue());
                clipboard.setContent(content);
            }
        });

        VBox vbox = new VBox(0, copyButton);
        Scene scene = new Scene(vbox, w, 100);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0.95); 
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String getLabel(){
        String s = "";
        boolean get = false;
        boolean first = false;
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("--label") || args[i].equals("-l")){
                get = true;
                first = true;
                continue;
            }
            if(!get) continue;
            s += (first ? "" : " ") + args[i];
            first = false;
        }
        return s.equals("") ? "Copy" : s;
    }

    private String getValue(){
        String s = "";
        for(int i = 0; i < args.length; i++){
            if(args[i].equals("--label") || args[i].equals("-l"))
                break;
            s += (i == 0 ? "" : " ") + args[i];
        }
        return s;
    }

    public static String[] args;

    public static void run(String[] args) throws Exception {
        if(args.length>0){
            Path path = Paths.get(args[0]);
            List<String> lines = Files.readAllLines(path);
            App.args = lines.get(0).split(" ");
        }else{
            App.args = args;
        }
        launch();
    }
}
