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

public class UDPChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);

            // Start a thread to receive messages
            new Thread(new ReceiveHandler(clientSocket)).start();

            // Start a thread to send messages
            new Thread(new SendHandler(clientSocket, serverAddress, SERVER_PORT, "Client")).start();

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
                System.out.println("\nServer: " + receivedMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class SendHandler implements Runnable {
    private DatagramSocket socket;
    private InetAddress serverAddress;
    private int serverPort;
    private String senderName;

    public SendHandler(DatagramSocket socket, InetAddress serverAddress, int serverPort, String senderName) {
        this.socket = socket;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.senderName = senderName;
    }

    @Override
    public void run() {
        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Type your message below:");

            while (true) {
                String message = userInput.readLine();

                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Client is shutting down...");
                    System.exit(0);
                }

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
