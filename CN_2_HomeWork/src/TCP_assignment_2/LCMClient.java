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
import java.util.Scanner;

public class LCMClient {
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

            // Send numbers to server
            out.writeInt(a);
            out.writeInt(b);

            // Receive LCM from server
            int lcm = in.readInt();
            System.out.println("Least Common Multiple (LCM) received from server: " + lcm);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

