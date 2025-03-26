/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Luồng nhận dữ liệu từ server trong chương trình UDP đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Receiving_thread_Client extends Thread {

    private DatagramSocket s; // Socket Datagram dùng để nhận dữ liệu từ server

    // Constructor nhận socket Datagram từ client
    Assignment_4_Receiving_thread_Client(DatagramSocket ds) {
        s = ds;
    }

    // Phương thức run thực thi logic nhận dữ liệu từ server
    public void run() {
        try {
            while (true) {
                // Tạo bộ đệm dữ liệu để lưu thông tin nhận từ server
                byte buff[] = new byte[1024];
                DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
                s.receive(p); // Nhận dữ liệu qua socket
                
                // Chuyển đổi dữ liệu từ byte sang chuỗi và hiển thị trên màn hình
                String data = new String(p.getData()).trim();
                System.out.println(data); // In dữ liệu nhận được từ server
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu có lỗi trong quá trình nhận dữ liệu
        }
    }
}
