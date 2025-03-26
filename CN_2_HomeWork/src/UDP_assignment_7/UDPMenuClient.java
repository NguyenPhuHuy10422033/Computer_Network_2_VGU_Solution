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
import java.util.Scanner;

public class UDPMenuClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;
            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // Receive menu from server
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);
                String menu = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println(menu);

                // User enters choice
                System.out.print("Your choice: ");
                String choice = scanner.nextLine();

                // Send choice to server
                sendBuffer = choice.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                // Handle case where user chooses to input numbers
                if (choice.equals("1")) {
                    clientSocket.receive(receivePacket);
                    System.out.println(new String(receivePacket.getData(), 0, receivePacket.getLength()));

                    // User enters two numbers
                    System.out.print("Enter two numbers: ");
                    String numbers = scanner.nextLine();

                    // Send numbers to server
                    sendBuffer = numbers.getBytes();
                    sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                    clientSocket.send(sendPacket);
                }

                // Receive response from server
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + response);

                // If user chose to exit
                if (choice.equals("4")) {
                    System.out.println("Client exiting...");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

