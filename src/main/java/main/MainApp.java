package main;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        URL url = new File("src/main/java/view/ImtView.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Kalkulator IMT");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    // Kelas peluncur khusus agar lolos dari error "runtime components missing"
    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(MainApp.class, args);
        }
    }

    public static void main(String[] args) {
        Launcher.main(args);
    }
}