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
 * Chương trình UDP Client, sử dụng đa luồng để giao tiếp với server.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Client {
    public static void main(String s[]) throws IOException {
        // Tạo một socket Datagram để giao tiếp với server
        DatagramSocket cl = new DatagramSocket();

        // Gửi thông điệp khởi đầu "hello" tới server
        byte hello[] = "hello".getBytes();
        InetAddress addsv = InetAddress.getByName("127.0.0.1"); // Địa chỉ server (localhost)
        int portsv = 1234; // Cổng server
        DatagramPacket p = new DatagramPacket(hello, hello.length, addsv, portsv); // Tạo gói dữ liệu "hello"
        cl.send(p); // Gửi gói dữ liệu tới server qua socket

        // Tạo luồng gửi dữ liệu từ client tới server
        Assignment_4_Sending_thread_Client sending = new Assignment_4_Sending_thread_Client(cl, addsv, portsv);

        // Tạo luồng nhận dữ liệu từ server tới client
        Assignment_4_Receiving_thread_Client receiving = new Assignment_4_Receiving_thread_Client(cl);

        // Khởi chạy cả hai luồng
        sending.start(); // Khởi chạy luồng gửi
        receiving.start(); // Khởi chạy luồng nhận
    }
}
