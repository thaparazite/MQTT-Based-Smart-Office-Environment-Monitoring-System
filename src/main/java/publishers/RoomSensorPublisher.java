package publishers;

import java.util.Random;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;


public class RoomSensorPublisher {

public static void main(String[] args) {
   String broker = "tcp://broker.hivemq.com:1883";
   String clientId = "RoomSensorPublisher";
   MqttClient mqttClient = null;// create a new MQTT client object
   try{
      // create a new MQTT client object with the broker and client ID
      mqttClient = new MqttClient(broker, clientId);
      // create a new connection options object and set the will message to be sent when the client disconnects
      MqttConnectOptions options = new MqttConnectOptions();
      options.setWill("floor/room/disconnect", "Room sensor disconnected".getBytes(), 0, false);
      mqttClient.connect(options);// connect the client to the broker with the connection options

      Random random = new Random();// create a new random object

      while(true){
         int temperature = random.nextInt(50);// generate a random temperature value
         int humidity = random.nextInt(100);// generate a random humidity value

         String temperatureMessage = "floor/room/temperature";// create a topic for the temperature value
         String humidityMessage = "floor/room/humidity";// create a topic for the humidity value

         // publish the temperature and humidity values to the respective topics
         RoomSensorPublisher.publishMessage(mqttClient, temperatureMessage,temperature + " °C");

         // publish the humidity value to the humidity topic
         publishMessage(mqttClient, humidityMessage, humidity + " %");

         Thread.sleep(1000);// sleep for 1 second before publishing the next message
      }// end of while loop
   // handle exceptions that may occur during the execution of the program
   }catch (MqttException | InterruptedException e){
      if (mqttClient != null) {// check if the MQTT client object is not null before disconnecting
         String errorTopic = "floor/room/error";// create a topic for error messages
         String errorMessage = e.getMessage();// get the error message
         try {
            // publish the error message to the error topic using the publishMessage method
            publishMessage(mqttClient, errorTopic, errorMessage);
         } catch (MqttException me) {// handle any exceptions that may occur during the publishing of the error message
            me.printStackTrace();
         }// end of try-catch block
      }// end of if block
   }// end of try-catch block

}// end of main method
   /*
    * This method publishes a message to a given topic
    */
   private static void publishMessage(MqttClient mqttClient, String topic, String payload) throws MqttException {
      // create a new message object
      MqttMessage message = new MqttMessage();
      message.setPayload(payload.getBytes());// set the payload of the message
      mqttClient.publish(topic, message);// publish the message to the topic
      // print the message to the console
      System.out.println("Published message: " + payload + " to " + topic);
   }// end of publishMessage method

}// end of class RoomSensorPublisher