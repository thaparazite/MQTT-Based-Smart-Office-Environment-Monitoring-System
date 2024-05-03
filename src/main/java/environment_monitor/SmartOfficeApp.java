package environment_monitor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import org.eclipse.paho.client.mqttv3.*;

public class SmartOfficeApp extends Application {
   // MQTT client object for connecting to the broker
   private MqttClient mqttClient;
   // Labels for displaying temperature, humidity, light, and window status
   private Label temperatureLabel;
   private Label humidityLabel;
   private Label lightLabel;
   private Label windowLabel;

   @Override
   public void start(Stage primaryStage) {
      // Set the title of the primary stage
      primaryStage.setTitle("MQTT Subscriber");
      primaryStage.setResizable(false);// disable resizing of the window
      // create a vertical box layout
      VBox root = new VBox(10);
      root.setPrefSize(300, 250);
      // create labels for temperature, humidity, light, and window status
      temperatureLabel = new Label("Temperature: ");
      humidityLabel = new Label("Humidity: ");
      lightLabel = new Label("Light Status: ");
      windowLabel = new Label("Window Status: ");
      // create a connect button
      Button connectButton = new Button("Connect");
      connectButton.setOnAction(e -> connectToBroker());
      // create a disconnect button
      Button disconnectButton = new Button("Disconnect");
      disconnectButton.setOnAction(e -> disconnectFromBroker());
      // add the labels and buttons to the root node
      root.getChildren().addAll(temperatureLabel, humidityLabel, lightLabel, windowLabel, connectButton, disconnectButton);
      // create a new scene with the root node
      Scene scene = new Scene(root);
      primaryStage.setScene(scene);// set the scene for the primary stage
      primaryStage.show();// display the primary stage
   }// end of start method

   private void connectToBroker() {
      if (mqttClient == null || !mqttClient.isConnected()) {
         String broker = "tcp://broker.hivemq.com:1883";

         try {
            // create a new MQTT client object with the broker and a generated client ID
            mqttClient = new MqttClient(broker, MqttClient.generateClientId());
            // set the callback for handling messages received from the broker
            mqttClient.setCallback(new MqttCallback() {
               // Handle connection lost to the broker
               @Override
               public void connectionLost(Throwable cause) {
                  cause.printStackTrace();// print the stack trace of the exception
               }// end of connectionLost method

               // Update the UI with the received message
               @Override
               public void messageArrived(String topic, MqttMessage message) {
                  // Update the UI with the received message
                  Platform.runLater(() -> {
                     switch (topic) {
                        case "floor/room/temperature":
                           temperatureLabel.setText("Temperature: " + message.toString());
                           break;
                        case "floor/room/humidity":
                           humidityLabel.setText("Humidity: " + message.toString());
                           break;
                        case "floor/light/ID":
                           lightLabel.setText("Light Status: " + message.toString());
                           break;
                        case "floor/window/status":
                           windowLabel.setText("Window Status: " + message.toString());
                           break;
                        default:
                           System.out.println("Received message for unknown topic: " + topic);
                           break;
                     }// end of switch block
                  });// end of Platform.runLater method
               }// end of messageArrived method

               @Override
               public void deliveryComplete(IMqttDeliveryToken token) {
                  // Not used in this example
               }// end of deliveryComplete method
            });// end of setCallback method

            mqttClient.connect();// connect the client to the broker
            // Subscribe to the topics for temperature, humidity, light, and window status
            mqttClient.subscribe("floor/room/temperature");
            mqttClient.subscribe("floor/room/humidity");
            mqttClient.subscribe("floor/light/ID");
            mqttClient.subscribe("floor/window/status");

            // Show alert dialog to inform user that connection is successful
            Alert alert = new Alert(AlertType.INFORMATION);// create a new alert dialog with information type
            alert.setTitle("Information Dialog");// set the title of the alert dialog
            alert.setHeaderText(null);// set the header text of the alert dialog
            alert.setContentText("Successfully connected to the broker.");
            alert.showAndWait();// show the alert dialog

         } catch (MqttException ex) {
            ex.printStackTrace();
         }
      }else{
         // Show alert dialog to inform user that already connected to the broker
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle("Information Dialog");
         alert.setHeaderText(null);
         alert.setContentText("Already connected to the broker.");
         alert.showAndWait();
      }// end of if-else block
   }// end of connectToBroker method

   // Disconnect from the broker method
   private void disconnectFromBroker() {
      if (mqttClient != null && mqttClient.isConnected()) {
         try {
            mqttClient.disconnect();// disconnect the client from the broker
            mqttClient.close();// close the client connection
            mqttClient = null;// set the client object to null

            // Show alert dialog to inform user that disconnection is successful
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Successfully disconnected from the broker.");
            alert.showAndWait();
            // handle exceptions that may occur during the disconnection process
         } catch (MqttException e) {
            e.printStackTrace();// print the stack trace of the exception
         }// end of try-catch block
      }else {
         // Show alert dialog to inform user that not currently connected to the broker
         Alert alert = new Alert(AlertType.INFORMATION);// create a new alert dialog with information type
         alert.setTitle("Information Dialog");// set the title of the alert dialog
         alert.setHeaderText(null);// set the header text of the alert dialog
         alert.setContentText("Not currently connected to the broker.");
         alert.showAndWait();// show the alert dialog
      }// end of if-else block
   }// end of disconnectFromBroker method
   // main method
   public static void main(String[] args) {
      launch(args);// launch the JavaFX application
   }// end of main method
}// end of SmartOfficeApp class
