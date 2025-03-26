/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_2;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class LCMServer {
    public static void main(String[] args) {
        int port = 5000; // Server port

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Client connected.");

                    // Read two integers from the client
                    int a = in.readInt();
                    int b = in.readInt();
                    System.out.println("Received numbers: " + a + " and " + b);

                    // Compute LCM
                    int lcm = computeLCM(a, b);

                    // Send result to client
                    out.writeInt(lcm);
                    System.out.println("Sent LCM: " + lcm);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to calculate LCM
    private static int computeLCM(int a, int b) {
        return (a * b) / computeGCD(a, b);
    }

    // Function to calculate GCD using Euclidean algorithm
    private static int computeGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}

