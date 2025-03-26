/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP; // Khai báo gói UDP

import java.io.IOException; // Import lớp IOException để xử lý các lỗi liên quan đến vào/ra
import java.net.DatagramPacket; // Import lớp DatagramPacket để tạo gói dữ liệu UDP
import java.net.DatagramSocket; // Import lớp DatagramSocket để gửi/nhận gói dữ liệu UDP
import java.net.InetAddress; // Import lớp InetAddress để định danh địa chỉ IP
import java.util.Scanner; // Import lớp Scanner để nhận dữ liệu từ người dùng

/**
 *
 * @author HUY.TD
 */
public class UDP_Client_5 {
    // Phương thức gửi dữ liệu đến server qua UDP
    static void gui(String data, String add, int port, DatagramSocket s) throws IOException {
        byte buff[] = data.getBytes(); // Chuyển chuỗi thành mảng byte
        InetAddress add_sv = InetAddress.getByName(add); // Xác định địa chỉ IP của server
        DatagramPacket p = new DatagramPacket(buff, buff.length, add_sv, port); // Tạo gói dữ liệu UDP
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    // Phương thức nhận dữ liệu từ server qua UDP
    static String nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm để nhận dữ liệu
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói nhận dữ liệu
        s.receive(p); // Nhận gói dữ liệu từ server
        String data = new String(p.getData()).trim(); // Chuyển đổi dữ liệu thành chuỗi và loại bỏ khoảng trắng thừa
        return data; // Trả về dữ liệu nhận được
    }

    public static void main(String[] args) {
        try {
            // Tạo socket UDP để gửi và nhận dữ liệu
            DatagramSocket cl = new DatagramSocket();

            // Tạo đối tượng Scanner để nhập dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);

            // Vòng lặp để trao đổi tin nhắn giữa client và server
            while (true) {   
                // Hiển thị yêu cầu nhập tin nhắn từ người dùng
                System.out.println("Client:");
                String sdata = keyboard.nextLine(); // Đọc dữ liệu từ người dùng

                // Gửi tin nhắn tới server
                gui(sdata, "localhost", 1234, cl);

                // Nếu người dùng nhập "bye", thoát khỏi vòng lặp
                if (sdata.equals("bye")) break;

                // Hiển thị phản hồi từ server
                System.out.println("Server:");
                String rdata = nhan(cl); // Nhận dữ liệu từ server
                System.out.println(rdata); // Hiển thị dữ liệu từ server

                // Nếu server trả về "bye", thoát khỏi vòng lặp
                if (rdata.equals("bye")) break;
            }
        } catch (IOException e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn này
        }
    }
}
