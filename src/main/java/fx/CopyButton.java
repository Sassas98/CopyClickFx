package fx;

import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class CopyButton extends Button {
    public CopyButton(String label, String value) {
        super(label);
        double w = 80 + (label.length() * 10);
        setPrefSize(w, 100);
        setStyle(
            "-fx-font-size: 22px;" +
            "-fx-text-fill: white;" +
            "-fx-background-color: black;"
        );
        
        setOnMousePressed(e -> setStyle("-fx-background-color: #222222; -fx-text-fill: white; -fx-font-size: 22px;"));
        setOnMouseReleased(e -> setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 22px;"));
        setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(value);
            clipboard.setContent(content);
        });
    }

}
