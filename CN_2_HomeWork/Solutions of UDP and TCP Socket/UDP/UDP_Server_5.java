/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Chương trình UDP Server thực hiện trao đổi dữ liệu hai chiều với client. 
 * Dừng khi server hoặc client nhập "bye".
 * Tác giả: HUY.TD
 */
public class UDP_Server_5 {

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
            Scanner keyboard = new Scanner(System.in); // Khởi tạo đối tượng Scanner để nhập từ bàn phím

            while (true) {
                // Hiển thị nội dung nhận từ client
                System.out.println("Client:");
                DatagramPacket p = nhan(sv);
                String rdata = new String(p.getData()).trim();
                System.out.println(rdata); // In dữ liệu nhận được
                if (rdata.equals("bye")) break; // Dừng nếu client gửi "bye"

                // Nhập nội dung từ server và gửi lại cho client
                System.out.println("Server:");
                String sdata = keyboard.nextLine(); 
                gui(sdata, p.getAddress(), p.getPort(), sv);
                if (sdata.equals("bye")) break; // Dừng nếu server nhập "bye"
            }
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }
}
