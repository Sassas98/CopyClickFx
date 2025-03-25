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

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {

        Button copyButton = new Button(getLabel());
        copyButton.setPrefSize(200, 200);
        copyButton.setStyle(
            "-fx-font-size: 25px;" +
            "-fx-text-fill: white;" +
            "-fx-background-color: black;"
        );
        
        copyButton.setOnMousePressed(e -> copyButton.setStyle("-fx-background-color: #222222; -fx-text-fill: white; -fx-font-size: 25px;"));
        copyButton.setOnMouseReleased(e -> copyButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 25px;"));
        copyButton.setOnAction(event -> {
            if (args.length > 0) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(getValue());
                clipboard.setContent(content);
            }
        });

        VBox vbox = new VBox(0, copyButton);
        Scene scene = new Scene(vbox, 200, 200);
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
            App.args = new String[lines.size()];
            for (int i = 0; i < args.length; i++) {
                App.args[i] = lines.get(i);
            }
        }else{
            App.args = args;
        }
        launch();
    }
}
