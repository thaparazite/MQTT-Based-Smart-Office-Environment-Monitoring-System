# MQTT-Based Smart Office Environment Monitoring System

## Project Overview:
The MQTT-Based Smart Office Environment Monitoring System is a distributed system designed to monitor various environmental parameters in an office setting, such as temperature, humidity, light status, and window status. The system consists of multiple components, including publishers for floor and room sensors, as well as a subscriber application for displaying the collected data in real-time. MQTT (Message Queuing Telemetry Transport) protocol is used for communication between the components, facilitating lightweight and efficient data exchange.

## Components:

### Room Sensor Publisher:
- **Description:** Java application responsible for simulating a room sensor that measures temperature and humidity.
- **Functionality:** Generates random temperature and humidity values and publishes them to specific MQTT topics.
- **Dependencies:** Requires the Eclipse Paho MQTT client library.
- **Usage:** Execute the `RoomSensorPublisher` class to start publishing simulated sensor data.

### Floor Publisher:
- **Description:** Java application responsible for simulating floor-level environmental parameters such as light and window status.
- **Functionality:** Generates random light and window status values and publishes them to specific MQTT topics.
- **Dependencies:** Requires the Eclipse Paho MQTT client library.
- **Usage:** Execute the `FloorPublisher` class to start publishing simulated floor sensor data.

### Smart Office App (Subscriber):
- **Description:** JavaFX application responsible for subscribing to MQTT topics and displaying real-time environmental data.
- **Functionality:** Connects to the MQTT broker, subscribes to relevant topics, and updates the UI with received sensor data.
- **Dependencies:** Requires the Eclipse Paho MQTT client library and JavaFX.
- **Usage:** Execute the `SmartOfficeApp` class to launch the subscriber application.

## Installation Steps:
### Note:
Ensure that Java Development Kit (JDK) is installed on your system before proceeding with the installation.

1. Clone the repository to your local machine.
2. Make sure you have the Eclipse Paho MQTT client library added to your project dependencies.
3. Compile the Java files using `javac` command or your preferred IDE.
4. Execute each component separately by running the corresponding Java classes:
   - `RoomSensorPublisher`
   - `FloorPublisher`
   - `SmartOfficeApp`

## Support and Assistance:
If you encounter any issues or have questions about the project, feel free to open an issue on GitHub or reach out to the project maintainer via email x22195092@student.ncirl.ie.

## Project Maintenance and Contributors:
This project is maintained and contributed to by [Flaviu Vanca](https://github.com/thaparazite). Contributions are welcome via pull requests, and all contributors are encouraged to follow the project's contribution guidelines outlined in the CONTRIBUTING.md file.

## License:
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

## Disclaimer:
This project is for educational and informational purposes only. It is not intended for commercial use or deployment in critical environments without proper validation and testing. Use at your own risk.
