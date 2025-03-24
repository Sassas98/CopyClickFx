package fx;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        TextField textField = new TextField();
        textField.setPromptText("Inserisci del testo");

        Button copyButton = new Button("Copia");

        copyButton.setOnAction(event -> {
            String textToCopy = textField.getText();

            if (!textToCopy.isEmpty()) {
                Clipboard clipboard = Clipboard.getSystemClipboard();
                ClipboardContent content = new ClipboardContent();
                content.putString(textToCopy);
                clipboard.setContent(content);
            }
        });

        VBox vbox = new VBox(10, textField, copyButton);
        
        Scene scene = new Scene(vbox, 300, 200);

        primaryStage.setTitle("Copy Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void run(String[] args) {
        launch();
    }
}
