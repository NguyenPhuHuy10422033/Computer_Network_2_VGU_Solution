/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package UDP_multithread_assignment_2;

/**
 *
 * @author DELL
 */
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            DatagramSocket cl = new DatagramSocket();
            Scanner bp = new Scanner(System.in);

            System.out.print("Nhap so 1: ");
            int so1 = bp.nextInt();
            gui(String.valueOf(so1), "localhost", 1234, cl);

            System.out.print("Nhap so 2: ");
            int so2 = bp.nextInt();
            gui(String.valueOf(so2), "localhost", 1234, cl);

            System.out.print("Nhap so 3: ");
            int so3 = bp.nextInt();
            gui(String.valueOf(so3), "localhost", 1234, cl);

            String BSCNN = nhan(cl);
            System.out.println("BSCNN cua 3 so la: " + BSCNN);

            cl.close();
            bp.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gui(String message, String host, int port, DatagramSocket socket) throws Exception {
        byte[] data = message.getBytes();
        InetAddress ip = InetAddress.getByName(host);
        DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
        socket.send(packet);
    }

    public static String nhan(DatagramSocket socket) throws Exception {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        return new String(packet.getData(), 0, packet.getLength());
    }
}
