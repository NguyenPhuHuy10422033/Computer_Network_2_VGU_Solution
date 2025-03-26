/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_assignment_7;

/**
 *
 * @author DELL
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPMenuServer {
    private static Integer num1 = null;
    private static Integer num2 = null;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
            System.out.println("Server is running...");

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            while (true) {
                // Send menu to client
                String menu = "1. Input two integer numbers\n"
                            + "2. Maximum value of two numbers\n"
                            + "3. Minimum value of two numbers\n"
                            + "4. Exit\n"
                            + "Enter your choice: ";
                sendBuffer = menu.getBytes();

                // Receive client's choice
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);
                String choice = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                String response = "";

                switch (choice) {
                    case "1": // Receive two integers from client
                        response = "Enter two integers (separated by space): ";
                        sendBuffer = response.getBytes();
                        serverSocket.send(new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort));

                        // Receive the two numbers
                        serverSocket.receive(receivePacket);
                        String numbers = new String(receivePacket.getData(), 0, receivePacket.getLength());
                        String[] numArr = numbers.split(" ");

                        if (numArr.length == 2) {
                            num1 = Integer.parseInt(numArr[0]);
                            num2 = Integer.parseInt(numArr[1]);
                            response = "Numbers received: " + num1 + " and " + num2;
                        } else {
                            response = "Invalid input. Please enter two integers.";
                        }
                        break;

                    case "2": // Maximum value
                        if (num1 != null && num2 != null) {
                            response = "Maximum value: " + Math.max(num1, num2);
                        } else {
                            response = "Please enter two integers firstly.";
                        }
                        break;

                    case "3": // Minimum value
                        if (num1 != null && num2 != null) {
                            response = "Minimum value: " + Math.min(num1, num2);
                        } else {
                            response = "Please enter two integers firstly.";
                        }
                        break;

                    case "4": // Exit
                        response = "Goodbye!";
                        sendBuffer = response.getBytes();
                        serverSocket.send(new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort));
                        System.out.println("Server exiting...");
                        return;

                    default:
                        response = "Invalid choice. Please select from the menu.";
                        break;
                }

                // Send response to client
                sendBuffer = response.getBytes();
                serverSocket.send(new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

