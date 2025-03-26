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
public class UDP_Client_3 {
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

        // Nhập số nguyên thứ nhất từ người dùng
        System.out.print("Please enter the first integer:");
        String so1 = bp.nextLine();
        so1 = "1 " + so1; // Định dạng dữ liệu gửi đi với tiền tố "1"

        // Nhập số nguyên thứ hai từ người dùng
        System.out.print("Please enter the second integer:");
        String so2 = bp.nextLine();
        so2 = "2 " + so2; // Định dạng dữ liệu gửi đi với tiền tố "2"

        // Nhập phép toán từ người dùng
        System.out.print("Please enter the operation:");
        String so3 = bp.nextLine();
        so3 = "3 " + so3; // Định dạng dữ liệu gửi đi với tiền tố "3"

        // Gửi các dữ liệu đã nhập tới server
        gui(so2, "localhost", 1234, cl);
        gui(so3, "localhost", 1234, cl);
        gui(so1, "localhost", 1234, cl);

        // Nhận kết quả phản hồi từ server
        String BSCNN = nhan(cl);
        System.out.println("Result is: " + BSCNN); // Hiển thị kết quả

        // Đóng socket UDP
        cl.close();
    }
}
