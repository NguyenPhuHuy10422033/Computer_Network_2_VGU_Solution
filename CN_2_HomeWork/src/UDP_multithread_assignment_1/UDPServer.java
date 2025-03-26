/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_multithread_assignment_1;

/**
 *
 * @author DELL
 */
import java.net.*;
import java.io.*;

public class UDPServer {
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP Server is running on port " + PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Handle each client request in a new thread
                new Thread(new ClientHandler(serverSocket, receivePacket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientHandler implements Runnable {
    private DatagramSocket socket;
    private DatagramPacket packet;

    public ClientHandler(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.packet = packet;
    }

    @Override
    public void run() {
        try {
            String receivedText = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Received: " + receivedText);

            String upperCaseText = receivedText.toUpperCase();
            byte[] sendData = upperCaseText.getBytes();

            InetAddress clientAddress = packet.getAddress();
            int clientPort = packet.getPort();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);

            System.out.println("Sent: " + upperCaseText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

