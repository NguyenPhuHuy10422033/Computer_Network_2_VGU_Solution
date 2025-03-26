/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_assignment_5;

/**
 *
 * @author DELL
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPChatServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(9876);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Server is running and waiting for messages...");

            byte[] receiveBuffer = new byte[1024];
            byte[] sendBuffer;

            while (true) {
                // Receive message from client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + receivedMessage);

                if (receivedMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected. Chat ended.");
                    break;
                }

                // Get server's response
                System.out.print("You: ");
                String response = scanner.nextLine();

                sendBuffer = response.getBytes();
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);

                if (response.equalsIgnoreCase("bye")) {
                    System.out.println("You ended the chat.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
