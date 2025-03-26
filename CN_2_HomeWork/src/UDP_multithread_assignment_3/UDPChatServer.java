/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_multithread_assignment_3;

/**
 *
 * @author DELL
 */
import java.net.*;
import java.io.*;

public class UDPChatServer {
    private static final int SERVER_PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("UDP Chat Server is running on port " + SERVER_PORT);

            // Start a thread to receive messages
            new Thread(new ReceiveHandler(serverSocket)).start();

            // Start a thread to send messages
            new Thread(new SendHandler(serverSocket, "Server")).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReceiveHandler implements Runnable {
    private DatagramSocket socket;

    public ReceiveHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            byte[] receiveData = new byte[1024];
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("\nClient: " + receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SendHandler implements Runnable {
    private DatagramSocket socket;
    private InetAddress clientAddress;
    private int clientPort;
    private String senderName;

    public SendHandler(DatagramSocket socket, String senderName) {
        this.socket = socket;
        this.senderName = senderName;
    }

    @Override
    public void run() {
        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type your message below:");

            while (true) {
                String message = userInput.readLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Server is shutting down...");
                    System.exit(0);
                }

                byte[] sendData = message.getBytes();

                if (clientAddress != null) {
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                    socket.send(sendPacket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setClientDetails(InetAddress address, int port) {
        this.clientAddress = address;
        this.clientPort = port;
    }
}

