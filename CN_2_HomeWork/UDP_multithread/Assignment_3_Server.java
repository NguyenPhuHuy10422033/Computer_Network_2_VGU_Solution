/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Chương trình UDP Server trong hệ thống đa luồng để giao tiếp với client.
 * Tác giả: HUY.TD
 */
public class Assignment_3_Server {
    public static void main(String s[]) throws IOException {
        // Tạo socket server lắng nghe tại cổng 1234
        DatagramSocket sv = new DatagramSocket(1234);

        // Bộ đệm và gói dữ liệu ban đầu
        byte buff[] = new byte[1024]; // Tạo bộ đệm dữ liệu với kích thước 1024 byte
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận từ client
        sv.receive(p); // Nhận gói dữ liệu từ client (chờ tín hiệu "hello")

        // Tạo luồng gửi dữ liệu tới client
        Assignment_3_Sending_thread sending = new Assignment_3_Sending_thread(sv, p.getAddress(), p.getPort());

        // Tạo luồng nhận dữ liệu từ client
        Assignment_3_Receiving_thread receiving = new Assignment_3_Receiving_thread(sv);

        // Khởi chạy cả hai luồng
        sending.start(); // Luồng gửi dữ liệu
        receiving.start(); // Luồng nhận dữ liệu
    }
}
