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
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to Echo Server. Type messages to send:");

            while (true) {
                System.out.print("You: ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Closing connection...");
                    break;
                }

                out.println(message); // Send message
                String response = in.readLine(); // Receive echo
                System.out.println("Server Echo: " + response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Connected to Echo Server. Type messages to send:
You: hi 
Server Echo: hi 
You: this is an echo test
Server Echo: this is an echo test
*/