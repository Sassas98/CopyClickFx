package fx;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        FlowPane flow = new FlowPane();
        flow.setStyle("-fx-background-color: black;");
        List<CopyButton> buttons = infos.stream()
            .map(pair -> new CopyButton(pair.label, pair.value)).toList();
        buttons.forEach(button -> flow.getChildren().add(button));
        double dim = buttons.size() * 60;
        Scene scene = new Scene(flow, dim, dim);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setOpacity(0.9); 
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class Pair {
        public String value;
        public String label;

        public Pair(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public static List<Pair> infos;

    public static void run(String[] args) throws Exception {
        infos = new ArrayList<>();
        if(args.length > 0){
            Path path = Paths.get(args[0]);
            List<String> lines = Files.readAllLines(path);
            for(int i = 0; i < lines.size(); i += 2){
                String value = lines.get(i);
                String label = (i + 1 < lines.size()) ? lines.get(i + 1) : value;
                infos.add(new App().new Pair(value, label));
            }
        }
        launch();
    }
}
