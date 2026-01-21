import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kiosk Launcher");

        // Layout vertical
        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 20; -fx-background-color: #111;");

        // Botón ejemplo: abrir RetroArch
        Button retroBtn = new Button("Abrir RetroArch");
        retroBtn.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        retroBtn.setOnAction(e -> {
            try {
                // Cambia la ruta según tu instalación
                new ProcessBuilder("retroarch").start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        // Botón ejemplo: abrir navegador
        Button browserBtn = new Button("Abrir Chromium");
        browserBtn.setStyle("-fx-font-size: 24px; -fx-text-fill: white;");
        browserBtn.setOnAction(e -> {
            try {
                new ProcessBuilder("chromium-browser", "--kiosk", "https://www.netflix.com").start();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        root.getChildren().addAll(retroBtn, browserBtn);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

        // Fullscreen
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
