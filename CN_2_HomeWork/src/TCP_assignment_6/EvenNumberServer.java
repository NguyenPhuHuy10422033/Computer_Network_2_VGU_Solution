/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_6;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class EvenNumberServer {
    public static void main(String[] args) {
        int port = 5000; // Port number

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Client connected.");

                    // Receive two integers
                    int a = in.readInt();
                    int b = in.readInt();
                    System.out.println("Received range: [" + a + ", " + b + "]");

                    // Send even numbers within range
                    for (int i = a; i <= b; i++) {
                        if (i % 2 == 0) {
                            out.writeInt(i);
                            System.out.println("Sent: " + i);
                        }
                    }

                    // Send termination signal (-1)
                    out.writeInt(-1);
                    System.out.println("All even numbers sent. Connection closed.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Server is running on port 5000
Client connected.
Received range: [2, 9]
Sent: 2
Sent: 4
Sent: 6
Sent: 8
All even numbers sent. Connection closed.
*/
