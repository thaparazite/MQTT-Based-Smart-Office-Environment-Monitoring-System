package environment_monitor;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.eclipse.paho.client.mqttv3.*;

public class SmartOfficeApp extends Application {

   private MqttClient mqttClient;
   private Label temperatureLabel;
   private Label humidityLabel;
   private Label lightLabel;
   private Label windowLabel;

   @Override
   public void start(Stage primaryStage) {
      primaryStage.setTitle("MQTT Subscriber");
      primaryStage.setResizable(false);

      VBox root = new VBox(10);
      root.setPrefSize(300, 250);

      temperatureLabel = new Label("Temperature: ");
      humidityLabel = new Label("Humidity: ");
      lightLabel = new Label("Light Status: ");
      windowLabel = new Label("Window Status: ");

      Button connectButton = new Button("Connect");
      connectButton.setOnAction(e -> connectToBroker());

      Button disconnectButton = new Button("Disconnect");
      disconnectButton.setOnAction(e -> disconnectFromBroker());

      root.getChildren().addAll(temperatureLabel, humidityLabel, lightLabel, windowLabel, connectButton, disconnectButton);

      Scene scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.show();
   }

   private void connectToBroker() {
      if (mqttClient == null || !mqttClient.isConnected()) {
         String broker = "tcp://broker.hivemq.com:1883";

         try {
            mqttClient = new MqttClient(broker, MqttClient.generateClientId());
            mqttClient.setCallback(new MqttCallback() {
               @Override
               public void connectionLost(Throwable cause) {
                  cause.printStackTrace();
               }

               @Override
               public void messageArrived(String topic, MqttMessage message) {
                  Platform.runLater(() -> {
                     // Update labels based on received messages
                     if (topic.equals("floor/room/temperature")) {
                        temperatureLabel.setText("Temperature: " + message.toString());
                     } else if (topic.equals("floor/room/humidity")) {
                        humidityLabel.setText("Humidity: " + message.toString());
                     } else if (topic.equals("floor/light/ID")) {
                        lightLabel.setText("Light Status: " + message.toString());
                     } else if (topic.equals("floor/window/status")) {
                        windowLabel.setText("Window Status: " + message.toString());
                     } else {
                        System.out.println("Received message for unknown topic: " + topic);
                     }
                  });
               }

               @Override
               public void deliveryComplete(IMqttDeliveryToken token) {
                  // Not used in this example
               }
            });

            mqttClient.connect();
            mqttClient.subscribe("floor/room/temperature");
            mqttClient.subscribe("floor/room/humidity");
            mqttClient.subscribe("floor/light/ID");
            mqttClient.subscribe("floor/window/status");

         } catch (MqttException ex) {
            ex.printStackTrace();
         }
      }
   }

   private void disconnectFromBroker() {
      if (mqttClient != null && mqttClient.isConnected()) {
         try {
            mqttClient.disconnect();
            mqttClient.close();
         } catch (MqttException e) {
            e.printStackTrace();
         }
      }
   }

   public static void main(String[] args) {
      launch(args);
   }
}// end of SmartOfficeApp class
