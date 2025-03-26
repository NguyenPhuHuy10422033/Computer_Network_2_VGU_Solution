/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_5;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        int port = 5000; // Define port number

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chat Server is running on port " + port);
            System.out.println("Waiting for client...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))) {

                System.out.println("Client connected. Start chatting...");

                String clientMessage, serverMessage;
                while (true) {
                    // Receive and display client message
                    clientMessage = in.readLine();
                    if (clientMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Client ended the chat.");
                        break;
                    }
                    System.out.println("Client: " + clientMessage);

                    // Server inputs and sends message
                    System.out.print("Server: ");
                    serverMessage = serverInput.readLine();
                    out.println(serverMessage);

                    if (serverMessage.equalsIgnoreCase("bye")) {
                        System.out.println("Chat ended by server.");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
/*
Chat Server is running on port 5000
Waiting for client...
Client connected. Start chatting...
Client: hi
Server: hello
Client: how are you
Server: bye
Chat ended by server.
*/
