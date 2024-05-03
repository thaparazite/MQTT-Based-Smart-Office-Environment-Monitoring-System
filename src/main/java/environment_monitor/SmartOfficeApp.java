package environment_monitor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SmartOfficeApp extends Application {

   public static void main(String[] args) {

      // start the application
      Application.launch(args);
   }// end of main method

   public void start(Stage primaryStage){
      primaryStage.setTitle("Simple JavaFX App");

      StackPane root = new StackPane();
      Button btn = new Button();

     root.getChildren().add(btn);
      btn.setText("Click Me!");
      btn.setOnAction(event -> {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Message");
         alert.setHeaderText(null);
         alert.setContentText("Hello, JavaFX!");
         alert.showAndWait();
      });
      primaryStage.setScene(new Scene(root, 300, 250));
      primaryStage.show();

   }// end of start method

}// end of SmartOfficeApp class