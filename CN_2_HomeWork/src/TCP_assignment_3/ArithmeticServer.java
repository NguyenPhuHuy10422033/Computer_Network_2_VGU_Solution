/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_assignment_3;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.*;

public class ArithmeticServer {
    public static void main(String[] args) {
        int port = 5000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Client connected.");

                    // Receive numbers and operation
                    int a = in.readInt();
                    int b = in.readInt();
                    char operation = in.readChar();
                    System.out.println("Received: " + a + " " + operation + " " + b);

                    // Perform calculation
                    double result = performOperation(a, b, operation);

                    // Send result back
                    out.writeDouble(result);
                    System.out.println("Sent result: " + result);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Perform arithmetic operation
    private static double performOperation(int a, int b, char operation) {
        switch (operation) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return (b != 0) ? (double) a / b : Double.NaN; // Handle division by zero
            default: return Double.NaN; // Invalid operation
        }
    }
}

/*
Server is running on port 5000
Client connected.
Received: 10 / 2
Sent result: 5.0
*/
