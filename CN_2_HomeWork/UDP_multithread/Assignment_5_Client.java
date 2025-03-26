/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Chương trình UDP Client trong hệ thống đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_5_Client {

    public static void main(String s[]) throws IOException {
        // Tạo một socket Datagram để giao tiếp với server
        DatagramSocket cl = new DatagramSocket();

        // Địa chỉ server (localhost) và cổng server
        InetAddress addsv = InetAddress.getByName("localhost");
        int portsv = 1234;

        // Tạo các luồng gửi và nhận dữ liệu
        Assignment_5_Sending_thread_Client sending = new Assignment_5_Sending_thread_Client(cl, addsv, portsv);
        Assignment_5_Receiving_thread_Client receiving = new Assignment_5_Receiving_thread_Client(cl);

        // Khởi chạy luồng gửi dữ liệu
        sending.start();
        // Khởi chạy luồng nhận dữ liệu
        receiving.start();
    }
}
