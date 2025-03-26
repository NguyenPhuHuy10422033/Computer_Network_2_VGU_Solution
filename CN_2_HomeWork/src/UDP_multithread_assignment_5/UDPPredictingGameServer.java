/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_multithread_assignment_5;

/**
 *
 * @author DELL
 */
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class UDPPredictingGameServer {
    private static final int SERVER_PORT = 9876;
    private static final int TIMEOUT_SECONDS = 180; // 3 minutes
    private static final List<ClientInfo> clients = Collections.synchronizedList(new ArrayList<>());
    private static final Random random = new Random();
    private static final int secretNumber = random.nextInt(101); // Random number [0,100]
    private static volatile boolean gameOver = false;

    public static void main(String[] args) {
        System.out.println("UDP Predicting Game Server is running...");
        System.out.println("Secret Number: " + secretNumber);

        try (DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)) {
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.schedule(() -> {
                if (!gameOver) {
                    sendToAll(serverSocket, "Time out. No one guessed correctly. Server exiting.");
                    System.exit(0);
                }
            }, TIMEOUT_SECONDS, TimeUnit.SECONDS);

            while (!gameOver) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                addClient(clientAddress, clientPort);

                try {
                    int guess = Integer.parseInt(message.trim());
                    System.out.println("Client " + clientAddress + ":" + clientPort + " guessed: " + guess);

                    if (guess == secretNumber) {
                        sendToClient(serverSocket, clientAddress, clientPort, "Congratulation! You won!");
                        sendToOthers(serverSocket, clientAddress, clientPort, "You lose.");
                        gameOver = true;
                        System.exit(0);
                    } else {
                        sendToClient(serverSocket, clientAddress, clientPort, "Please predict again.");
                    }
                } catch (NumberFormatException e) {
                    sendToClient(serverSocket, clientAddress, clientPort, "Invalid input. Enter an integer.");
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
                    return;
                }
            }
            clients.add(new ClientInfo(address, port));
        }
    }

    private static void sendToClient(DatagramSocket socket, InetAddress address, int port, String message) {
        try {
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            socket.send(sendPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendToOthers(DatagramSocket socket, InetAddress winnerAddress, int winnerPort, String message) {
        synchronized (clients) {
            for (ClientInfo client : clients) {
                if (!client.address.equals(winnerAddress) || client.port != winnerPort) {
                    sendToClient(socket, client.address, client.port, message);
                }
            }
        }
    }

    private static void sendToAll(DatagramSocket socket, String message) {
        synchronized (clients) {
            for (ClientInfo client : clients) {
                sendToClient(socket, client.address, client.port, message);
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

