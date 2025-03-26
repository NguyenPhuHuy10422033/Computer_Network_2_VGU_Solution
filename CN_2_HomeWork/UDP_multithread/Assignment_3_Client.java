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
 * Chương trình UDP Client gửi thông điệp "hello" tới server và sử dụng đa luồng để gửi/nhận dữ liệu.
 * Tác giả: HUY.TD
 */
public class Assignment_3_Client {

    public static void main(String s[]) throws IOException {
        // Tạo một socket Datagram để giao tiếp với server
        DatagramSocket cl = new DatagramSocket();

        // Chuỗi "hello" được gửi tới server
        byte hello[] = "hello".getBytes();
        InetAddress addsv = InetAddress.getByName("localhost"); // Địa chỉ server (localhost)
        int portsv = 1234; // Cổng server
        DatagramPacket p = new DatagramPacket(hello, hello.length, addsv, portsv); // Gói dữ liệu "hello"
        cl.send(p); // Gửi gói dữ liệu đến server qua socket

        // Tạo và khởi chạy luồng gửi dữ liệu
        Assignment_3_Sending_thread sending = new Assignment_3_Sending_thread(cl, addsv, portsv);

        // Tạo và khởi chạy luồng nhận dữ liệu
        Assignment_3_Receiving_thread receiving = new Assignment_3_Receiving_thread(cl);

        sending.start(); // Khởi chạy luồng gửi
        receiving.start(); // Khởi chạy luồng nhận
    }
}
