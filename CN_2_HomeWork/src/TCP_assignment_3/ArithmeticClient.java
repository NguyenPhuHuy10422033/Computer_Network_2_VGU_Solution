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
import java.util.Scanner;

public class ArithmeticClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            // Get user input
            System.out.print("Enter first integer: ");
            int a = scanner.nextInt();
            System.out.print("Enter second integer: ");
            int b = scanner.nextInt();
            System.out.print("Enter operation (+, -, *, /): ");
            char operation = scanner.next().charAt(0);

            // Send data to server
            out.writeInt(a);
            out.writeInt(b);
            out.writeChar(operation);

            // Receive result from server
            double result = in.readDouble();
            System.out.println("Result received from server: " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Enter first integer: 10
Enter second integer: 2
Enter operation (+, -, *, /): /
Result received from server: 5.0
*/