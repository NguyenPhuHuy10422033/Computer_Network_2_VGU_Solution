/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP; // Khai báo gói UDP

import java.io.IOException; // Import lớp IOException để xử lý lỗi vào/ra
import java.net.DatagramPacket; // Import lớp DatagramPacket để tạo gói dữ liệu UDP
import java.net.DatagramSocket; // Import lớp DatagramSocket để gửi/nhận gói dữ liệu UDP
import java.net.InetAddress; // Import lớp InetAddress để định danh địa chỉ IP
import java.util.Scanner; // Import lớp Scanner để nhận dữ liệu từ người dùng

/**
 *
 * @author HUY.TD
 */
public class UDP_Client_2 {
    // Phương thức gửi dữ liệu đến server qua UDP
    static void gui(String data, String add, int port, DatagramSocket s) throws IOException {
        byte buff[] = data.getBytes(); // Chuyển dữ liệu thành mảng byte
        InetAddress add_sv = InetAddress.getByName(add); // Xác định địa chỉ IP của server
        DatagramPacket p = new DatagramPacket(buff, buff.length, add_sv, port); // Tạo gói dữ liệu UDP
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    // Phương thức nhận dữ liệu từ server qua UDP
    static String nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm để nhận dữ liệu
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói nhận dữ liệu
        s.receive(p); // Nhận gói dữ liệu từ server
        String data = new String(p.getData()).trim(); // Chuyển đổi dữ liệu thành chuỗi và loại bỏ khoảng trắng
        return data; // Trả về dữ liệu nhận được
    }

    public static void main(String[] s) throws IOException {
        // Tạo socket UDP để gửi và nhận dữ liệu
        DatagramSocket cl = new DatagramSocket();

        // Sử dụng Scanner để nhập dữ liệu từ bàn phím
        Scanner bp = new Scanner(System.in);

        // Nhập số đầu tiên từ người dùng
        System.out.print("Nhap so 1:");
        int so1 = bp.nextInt();
        gui(String.valueOf(so1), "localhost", 1234, cl); // Gửi số đầu tiên đến server

        // Nhập số thứ hai từ người dùng
        System.out.print("Nhap so 2:");
        int so2 = bp.nextInt();
        gui(String.valueOf(so2), "localhost", 1234, cl); // Gửi số thứ hai đến server

        // Nhập số thứ ba từ người dùng
        System.out.print("Nhap so 3:");
        int so3 = bp.nextInt();
        gui(String.valueOf(so3), "localhost", 1234, cl); // Gửi số thứ ba đến server

        // Nhận kết quả từ server (Ước số chung nhỏ nhất - BSCNN)
        String BSCNN = nhan(cl);
        System.out.println("BSCNN cua 3 so la: " + BSCNN); // Hiển thị kết quả nhận được

        // Đóng socket UDP
        cl.close();
    }
}
