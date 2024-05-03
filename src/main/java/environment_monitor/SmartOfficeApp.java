package environment_monitor;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class SmartOfficeApp extends Application {

   public static void main(String[] args) {
      System.out.println("Hi From ui package!");
      // start the application
      Application.launch(args);
   }// end of main method

   public void start(Stage primaryStage){
      primaryStage.setTitle("Simple JavaFX App");
      Button btn = new Button();
      btn.setText("Click Me!");
      btn.setOnAction(new EventHandler<ActionEvent>() {

         @Override
         public void handle(ActionEvent event) {
            displayMessage("Hello, JavaFX!");
         }
      });

      StackPane root = new StackPane();
      root.getChildren().add(btn);
      primaryStage.setScene(new Scene(root, 300, 250));
      primaryStage.show();

   }// end of start method

   private void displayMessage(String message) {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Message");
      alert.setHeaderText(null);
      alert.setContentText(message);
      alert.showAndWait();
   }

}// end of SmartOfficeApp class