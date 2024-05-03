package environment_monitor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SmartOfficeApp extends Application {

   public static void main(String[] args) {

      // start the application
      Application.launch(args);
   }// end of main method

   public void start(Stage primaryStage){
      primaryStage.setTitle("Simple JavaFX App");
      Button btn = new Button();
      btn.setText("Click Me!");
      btn.setOnAction(event -> displayMessage());

      StackPane root = new StackPane();
      root.getChildren().add(btn);
      primaryStage.setScene(new Scene(root, 300, 250));
      primaryStage.show();

   }// end of start method

   private void displayMessage() {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Message");
      alert.setHeaderText(null);
      alert.setContentText("Hello, JavaFX!");
      alert.showAndWait();
   }

}// end of SmartOfficeApp class