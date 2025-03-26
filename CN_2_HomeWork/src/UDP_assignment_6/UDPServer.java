/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_assignment_6;

/**
 *
 * @author DELL
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(9876)) {
            System.out.println("Server is running and waiting for data...");

            byte[] receiveBuffer = new byte[1024];

            while (true) {
                // Receive numbers from client
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String receivedData = new String(receivePacket.getData(), 0, receivePacket.getLength());
                String[] numbers = receivedData.split(" ");

                if (numbers.length == 2) {
                    int a = Integer.parseInt(numbers[0]);
                    int b = Integer.parseInt(numbers[1]);

                    InetAddress clientAddress = receivePacket.getAddress();
                    int clientPort = receivePacket.getPort();

                    System.out.println("Received range: [" + a + ", " + b + "]");

                    // Send even numbers within range [a, b]
                    for (int i = a; i <= b; i++) {
                        if (i % 2 == 0) {
                            String evenNumber = String.valueOf(i);
                            byte[] sendBuffer = evenNumber.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
                            serverSocket.send(sendPacket);

                            System.out.println("Sent: " + evenNumber);
                            Thread.sleep(500); // Adding delay to simulate sequential sending
                        }
                    }

                    // Send termination signal to indicate the end
                    String endSignal = "done";
                    byte[] endBuffer = endSignal.getBytes();
                    DatagramPacket endPacket = new DatagramPacket(endBuffer, endBuffer.length, clientAddress, clientPort);
                    serverSocket.send(endPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

