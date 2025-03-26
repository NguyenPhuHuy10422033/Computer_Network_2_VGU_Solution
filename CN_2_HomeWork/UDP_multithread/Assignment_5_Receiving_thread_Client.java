/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Luồng nhận dữ liệu từ server trong chương trình UDP Client đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_5_Receiving_thread_Client extends Thread {

    private DatagramSocket s; // Socket Datagram để nhận dữ liệu từ server

    // Constructor để khởi tạo đối tượng với socket Datagram
    Assignment_5_Receiving_thread_Client(DatagramSocket ds) {
        s = ds;
    }

    // Phương thức run thực thi logic nhận dữ liệu từ server
    public void run() {
        try {
            while (true) {
                // Tạo bộ đệm để lưu dữ liệu nhận
                byte buff[] = new byte[1024];
                DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu
                s.receive(p); // Nhận dữ liệu qua socket

                // Chuyển dữ liệu từ byte sang chuỗi và hiển thị
                String data = new String(p.getData()).trim();
                System.out.println(data);

                // Nếu nhận được các thông báo kết thúc, thoát khỏi vòng lặp
                if (data.equals("You win") || data.equals("You lose") || data.equals("Time out")) {
                    break;
                }
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình nhận dữ liệu
        }
    }
}
