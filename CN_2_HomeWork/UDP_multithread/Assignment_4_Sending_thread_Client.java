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
public class Assignment_4_Sending_thread_Client extends Thread {

    private DatagramSocket s; // Socket Datagram dùng để gửi dữ liệu
    InetAddress addsv; // Địa chỉ server
    int portsv; // Cổng server

    // Constructor khởi tạo luồng với socket, địa chỉ server và cổng server
    Assignment_4_Sending_thread_Client(DatagramSocket ds, InetAddress server, int portserver) {
        s = ds; // Gán socket
        addsv = server; // Gán địa chỉ server
        portsv = portserver; // Gán cổng server
    }

    // Phương thức run thực thi logic gửi dữ liệu
    public void run() {
        Scanner bp = new Scanner(System.in); // Tạo Scanner để đọc dữ liệu từ bàn phím

        try {
            while (true) {
                // Nhập dữ liệu từ bàn phím
                String tam2 = bp.nextLine();
                byte buff[] = tam2.getBytes(); // Chuyển dữ liệu nhập từ chuỗi thành byte

                // Tạo gói dữ liệu gửi tới server
                DatagramPacket p = new DatagramPacket(buff, buff.length, addsv, portsv);
                s.send(p); // Gửi gói dữ liệu qua socket

                // Nếu chuỗi nhập vào là "bye", đóng socket và thoát khỏi vòng lặp
                if (tam2.equals("bye")) {
                    s.close(); // Đóng socket
                    break; // Thoát vòng lặp
                }
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình gửi dữ liệu
        }
    }
}
