/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_1;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Step 1: Create the Socket and Wait for Connection from Client
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is running and waiting for a connection...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected!");

            // Step 2: Create Data Streams
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            // Step 3: Handle Client Data
            String receivedData = in.readLine();
            System.out.println("Received from Client: " + receivedData);
            out.write(receivedData.toUpperCase() + "\r\n");
            out.flush();

            // Step 4: Close the Socket
            clientSocket.close();
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
