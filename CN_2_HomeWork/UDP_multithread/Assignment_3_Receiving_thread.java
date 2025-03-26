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
public class Assignment_3_Receiving_thread extends Thread {

    private DatagramSocket s; // Socket Datagram để nhận dữ liệu từ server

    // Constructor nhận socket Datagram từ client
    Assignment_3_Receiving_thread(DatagramSocket ds) {
        s = ds;
    }

    // Phương thức run thực thi logic nhận dữ liệu
    public void run() {
        try {
            while (true) {
                // Tạo bộ đệm dữ liệu nhận và gói nhận
                byte buff[] = new byte[1024]; 
                DatagramPacket p = new DatagramPacket(buff, buff.length); 

                // Nhận dữ liệu qua socket
                s.receive(p); 
                String data = new String(p.getData()).trim(); // Chuyển dữ liệu thành chuỗi và loại bỏ khoảng trắng
                System.out.println(data); // Hiển thị dữ liệu nhận được lên màn hình
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình nhận dữ liệu
        }
    }
}
