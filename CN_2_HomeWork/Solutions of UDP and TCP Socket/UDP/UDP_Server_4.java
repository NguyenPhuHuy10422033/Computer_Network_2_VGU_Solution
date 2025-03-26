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
 * Chương trình UDP Server thực hiện chức năng nhận và phản hồi dữ liệu từ client. 
 * Dừng hoạt động khi nhận được ký tự "." từ client.
 * Tác giả: HUY.TD
 */
public class UDP_Server_4 {

    // Phương thức gửi dữ liệu từ server tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        byte buff[] = data.getBytes(); // Chuyển đổi chuỗi dữ liệu thành mảng byte
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port); // Tạo gói dữ liệu
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    // Phương thức nhận dữ liệu từ client
    static DatagramPacket nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm để lưu dữ liệu nhận
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
        s.receive(p); // Nhận dữ liệu qua socket
        return p;
    }

    // Phương thức main thực hiện logic chính của chương trình
    public static void main(String[] args) {
        try {
            // Tạo socket Datagram trên server với cổng 1234
            DatagramSocket sv = new DatagramSocket(1234);

            // Vòng lặp nhận và xử lý dữ liệu
            while (true) {
                DatagramPacket p = nhan(sv); // Nhận dữ liệu từ client
                String rdata = new String(p.getData()).trim(); // Chuyển đổi dữ liệu nhận được thành chuỗi và loại bỏ khoảng trắng
                System.out.println(rdata); // In dữ liệu ra màn hình
                if (rdata.equals(".")) break; // Dừng chương trình nếu nhận được ký tự "."
                gui(rdata, p.getAddress(), p.getPort(), sv); // Phản hồi lại dữ liệu nhận được cho client
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình gửi hoặc nhận dữ liệu
        }
    }
}
