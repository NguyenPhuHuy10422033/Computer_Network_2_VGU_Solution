/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Luồng gửi dữ liệu từ client tới server trong chương trình UDP đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_3_Sending_thread extends Thread {
    private DatagramSocket s; // Socket Datagram để gửi dữ liệu tới server
    InetAddress addsv; // Địa chỉ server
    int portsv; // Cổng server

    // Constructor khởi tạo luồng gửi với socket, địa chỉ server, và cổng server
    Assignment_3_Sending_thread(DatagramSocket ds, InetAddress server, int portserver) {
        s = ds;
        addsv = server;
        portsv = portserver;
    }

    // Phương thức run thực thi logic gửi dữ liệu
    public void run() {
        Scanner bp = new Scanner(System.in); // Tạo đối tượng Scanner để đọc dữ liệu từ bàn phím

        try {
            while (true) {
                // Nhập dữ liệu từ bàn phím
                String tam2 = bp.nextLine();
                byte buff[] = tam2.getBytes(); // Chuyển dữ liệu chuỗi thành mảng byte

                // Tạo gói dữ liệu để gửi tới server
                DatagramPacket p = new DatagramPacket(buff, buff.length, addsv, portsv);
                s.send(p); // Gửi gói dữ liệu qua socket

                // Kiểm tra nếu chuỗi nhập là "bye" để thoát
                if (tam2.equals("bye")) {
                    s.close(); // Đóng socket
                    break; // Thoát khỏi vòng lặp
                }
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi khi gửi dữ liệu
        }
    }
}
