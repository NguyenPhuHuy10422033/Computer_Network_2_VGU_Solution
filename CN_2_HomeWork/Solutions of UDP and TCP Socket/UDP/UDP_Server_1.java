/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Chương trình UDP Server nhận dữ liệu từ client, xử lý và gửi phản hồi.
 * Tác giả: HUY.TD
 */
public class UDP_Server_1 {
    public static void main(String[] s) {
        try {
            // Tạo socket Datagram trên server với cổng 1234
            DatagramSocket sv = new DatagramSocket(1234);  

            // Khởi tạo bộ đệm byte để nhận dữ liệu từ client
            byte buff1[] = new byte[256];
            DatagramPacket q = new DatagramPacket(buff1, buff1.length);

            // Nhận dữ liệu từ client
            sv.receive(q);

            // Chuyển đổi dữ liệu nhận được thành chuỗi và loại bỏ khoảng trắng
            String data = new String(q.getData()).trim();

            // Xử lý dữ liệu: chuyển chuỗi sang chữ hoa
            String kq = data.toUpperCase();

            // Chuẩn bị dữ liệu đã xử lý để gửi lại client
            byte buff2[] = kq.getBytes();
            InetAddress addcl = q.getAddress(); // Lấy địa chỉ IP của client
            int portcl = q.getPort(); // Lấy số cổng của client

            // Tạo gói dữ liệu chứa phản hồi
            DatagramPacket k = new DatagramPacket(buff2, buff2.length, addcl, portcl);

            // Gửi phản hồi tới client
            sv.send(k);

            // Đóng socket
            sv.close();
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình gửi hoặc nhận dữ liệu
        }
    }   
}
