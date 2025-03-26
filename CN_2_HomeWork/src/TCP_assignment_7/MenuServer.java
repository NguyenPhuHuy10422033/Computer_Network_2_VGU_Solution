/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_7;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class MenuServer {
    public static void main(String[] args) {
        int port = 5000;
        int num1 = 0, num2 = 0;
        boolean hasNumbers = false;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Client connected.");

                    while (true) {
                        // Send menu
                        out.writeUTF("\nMenu:\n1. Input two integer numbers\n2. Maximum value of two numbers\n3. Minimum value of two numbers\n4. Exit\nEnter your choice:");

                        int choice = in.readInt();
                        System.out.println("Client selected: " + choice);

                        if (choice == 1) {
                            // Receive two integers
                            num1 = in.readInt();
                            num2 = in.readInt();
                            hasNumbers = true;
                            out.writeUTF("Numbers received: " + num1 + " and " + num2);
                            System.out.println("Received numbers: " + num1 + ", " + num2);

                        } else if (choice == 2) {
                            if (hasNumbers) {
                                int max = Math.max(num1, num2);
                                out.writeUTF("Maximum: " + max);
                            } else {
                                out.writeUTF("Please enter two integers firstly.");
                            }

                        } else if (choice == 3) {
                            if (hasNumbers) {
                                int min = Math.min(num1, num2);
                                out.writeUTF("Minimum: " + min);
                            } else {
                                out.writeUTF("Please enter two integers firstly.");
                            }

                        } else if (choice == 4) {
                            out.writeUTF("Exiting...");
                            System.out.println("Client exited.");
                            break;
                        } else {
                            out.writeUTF("Invalid choice. Please enter a number from 1 to 4.");
                        }
                    }
                }
                System.out.println("Waiting for a new client...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Server is running on port 5000
Client connected.
Client selected: 2
Client selected: 1
Received numbers: 10, 20
Client selected: 2
Client selected: 4
Client exited.
Waiting for a new client...
*/
