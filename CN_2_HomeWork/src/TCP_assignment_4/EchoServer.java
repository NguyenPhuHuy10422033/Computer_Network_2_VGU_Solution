/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_4;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class EchoServer {
    public static void main(String[] args) {
        int port = 5000; // RFC 862 allows any TCP port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Client connected.");

                    String receivedMessage;
                    while ((receivedMessage = in.readLine()) != null) {
                        System.out.println("Received: " + receivedMessage);
                        out.println(receivedMessage); // Echo back the message
                        System.out.println("Echoed: " + receivedMessage);
                    }

                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Echo Server is running on port 5000
Client connected.
Received: hi 
Echoed: hi 
Received: this is an echo test
Echoed: this is an echo test
*/