/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_assignment_1;

/**
 *
 * @author DELL
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static final int PORT = 9876;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP Server is running on port " + PORT + "...");

            while (true) {
                // Nhận dữ liệu từ client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Mỗi yêu cầu client sẽ được xử lý trong một thread riêng biệt
                new ClientHandler(serverSocket, receivePacket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Thread xử lý từng client riêng lẻ
class ClientHandler extends Thread {
    private DatagramSocket socket;
    private DatagramPacket receivePacket;

    public ClientHandler(DatagramSocket socket, DatagramPacket packet) {
        this.socket = socket;
        this.receivePacket = packet;
    }

    @Override
    public void run() {
        try {
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();

            // Lấy dữ liệu từ packet và chuyển thành chữ hoa
            String receivedText = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from " + clientAddress + ":" + clientPort + " -> " + receivedText);
            String upperCaseText = receivedText.toUpperCase();

            // Gửi lại kết quả cho client
            byte[] sendData = upperCaseText.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

