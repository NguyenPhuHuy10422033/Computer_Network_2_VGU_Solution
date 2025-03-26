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
import java.util.Scanner;

public class EvenNumberClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            // Get user input
            System.out.print("Enter first integer (a): ");
            int a = scanner.nextInt();
            System.out.print("Enter second integer (b): ");
            int b = scanner.nextInt();

            // Send numbers to server
            out.writeInt(a);
            out.writeInt(b);

            // Receive and print even numbers
            System.out.println("Even numbers in range [" + a + ", " + b + "]:");
            int number;
            while ((number = in.readInt()) != -1) {
                System.out.println(number);
            }

            System.out.println("All numbers received. Closing connection.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Enter first integer (a): 2
Enter second integer (b): 9
Even numbers in range [2, 9]:
2
4
6
8
All numbers received. Closing connection.
*/