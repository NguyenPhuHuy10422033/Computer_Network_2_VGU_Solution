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
 * Chương trình UDP Server nhận hai số nguyên từ client, tính số lượng các số chẵn giữa hai số này,
 * sau đó gửi kết quả và các số chẵn về cho client.
 * Tác giả: HUY.TD
 */
public class UDP_Server_6 {

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
            DatagramSocket sv = new DatagramSocket(1234); // Tạo socket Datagram trên server với cổng 1234

            // Nhận hai số nguyên từ client
            DatagramPacket p1 = nhan(sv);
            DatagramPacket p2 = nhan(sv);
            int number1 = Integer.parseInt(new String(p1.getData()).trim());
            int number2 = Integer.parseInt(new String(p2.getData()).trim());

            // Xác định số nhỏ nhất và số lớn nhất
            int min, max;
            if (number1 < number2) {
                min = number1;
                max = number2;
            } else {
                min = number2;
                max = number1;
            }

            // Đếm số lượng các số chẵn giữa min và max
            int count = 0;
            for (int i = min; i <= max; i++) {
                if (i % 2 == 0) count++;
            }

            // Gửi kết quả số lượng hoặc danh sách các số chẵn về cho client
            if (count == 0) {
                gui(String.valueOf(count), p1.getAddress(), p1.getPort(), sv); // Không có số chẵn
            } else {
                for (int i = min; i <= max; i++) {
                    String st = count + "_" + i; // Định dạng kết quả
                    if (i % 2 == 0) gui(String.valueOf(st), p1.getAddress(), p1.getPort(), sv); // Gửi số chẵn
                }
            }
            sv.close(); // Đóng socket
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }
}
