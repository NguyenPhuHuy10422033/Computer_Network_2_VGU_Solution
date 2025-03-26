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

public class UDPChatClient {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket();
             Scanner scanner = new Scanner(System.in)) {

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            byte[] sendBuffer;
            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // Client sends message first
                System.out.print("You: ");
                String message = scanner.nextLine();

                sendBuffer = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                if (message.equalsIgnoreCase("bye")) {
                    System.out.println("You ended the chat.");
                    break;
                }

                // Receive response from server
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + response);

                if (response.equalsIgnoreCase("bye")) {
                    System.out.println("Server ended the chat.");
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

