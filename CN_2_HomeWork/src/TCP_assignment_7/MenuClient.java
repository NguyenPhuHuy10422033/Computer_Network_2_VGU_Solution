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
import java.util.Scanner;

public class MenuClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port);
             DataInputStream in = new DataInputStream(socket.getInputStream());
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the server.");

            while (true) {
                // Receive and display menu
                System.out.println(in.readUTF());

                // Get user choice
                System.out.print("Your choice: ");
                int choice = scanner.nextInt();
                out.writeInt(choice);

                if (choice == 1) {
                    // Input two numbers
                    System.out.print("Enter first integer: ");
                    int num1 = scanner.nextInt();
                    System.out.print("Enter second integer: ");
                    int num2 = scanner.nextInt();
                    out.writeInt(num1);
                    out.writeInt(num2);
                    System.out.println("Server: " + in.readUTF());

                } else if (choice == 2 || choice == 3 || choice == 4) {
                    // Receive result from server
                    String response = in.readUTF();
                    System.out.println("Server: " + response);

                    if (choice == 4) {
                        System.out.println("Exiting...");
                        break;
                    }

                } else {
                    System.out.println("Server: " + in.readUTF());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
Connected to the server.

Menu:
1. Input two integer numbers
2. Maximum value of two numbers
3. Minimum value of two numbers
4. Exit
Enter your choice:
Your choice: 2
Server: Please enter two integers firstly.

Menu:
1. Input two integer numbers
2. Maximum value of two numbers
3. Minimum value of two numbers
4. Exit
Enter your choice:
Your choice: 1
Enter first integer: 10
Enter second integer: 20
Server: Numbers received: 10 and 20

Menu:
1. Input two integer numbers
2. Maximum value of two numbers
3. Minimum value of two numbers
4. Exit
Enter your choice:
Your choice: 2
Server: Maximum: 20

Menu:
1. Input two integer numbers
2. Maximum value of two numbers
3. Minimum value of two numbers
4. Exit
Enter your choice:
Your choice: 4
Server: Exiting...
Exiting...
*/