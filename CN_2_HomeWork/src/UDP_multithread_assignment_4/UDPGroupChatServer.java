/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_multithread_assignment_4;

/**
 *
 * @author DELL
 */
import java.net.*;
import java.io.*;
import java.util.*;

public class UDPGroupChatServer {
    private static final int SERVER_PORT = 9876;
    private static final List<ClientInfo> clients = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("UDP Group Chat Server is running on port " + SERVER_PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                // Handle new clients joining
                if (message.equalsIgnoreCase("hello")) {
                    addClient(clientAddress, clientPort);
                } else {
                    forwardMessage(message, clientAddress, clientPort, serverSocket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addClient(InetAddress address, int port) {
        synchronized (clients) {
            for (ClientInfo client : clients) {
                if (client.address.equals(address) && client.port == port) {
                    return; // Client already exists
                }
            }
            clients.add(new ClientInfo(address, port));
            System.out.println("New client joined: " + address + ":" + port);
        }
    }

    private static void forwardMessage(String message, InetAddress senderAddress, int senderPort, DatagramSocket socket) {
        synchronized (clients) {
            for (ClientInfo client : clients) {
                if (!client.address.equals(senderAddress) || client.port != senderPort) {
                    try {
                        byte[] sendData = message.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.address, client.port);
                        socket.send(sendPacket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class ClientInfo {
    InetAddress address;
    int port;

    public ClientInfo(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }
}

