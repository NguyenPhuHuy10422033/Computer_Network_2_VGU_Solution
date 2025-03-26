/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP; // Khai báo gói UDP

import java.io.IOException; // Import lớp IOException để xử lý các lỗi liên quan đến vào/ra
import java.net.DatagramPacket; // Import lớp DatagramPacket để tạo gói dữ liệu
import java.net.DatagramSocket; // Import lớp DatagramSocket để gửi và nhận gói UDP
import java.net.InetAddress; // Import lớp InetAddress để định danh địa chỉ IP
import java.util.Scanner; // Import lớp Scanner để nhập dữ liệu từ người dùng

/**
 *
 * @author HUY.TD
 */
public class UDP_Client_1 {
    public static void main(String s[]) {
        try {
            // Tạo socket UDP để gửi và nhận gói dữ liệu
            DatagramSocket cl = new DatagramSocket();

            // Tạo đối tượng Scanner để nhập dữ liệu từ bàn phím
            Scanner sc = new Scanner(System.in);
            
            // Yêu cầu người dùng nhập một chuỗi dữ liệu
            System.out.println("Please input a string:");
            String st = sc.nextLine(); // Đọc chuỗi từ bàn phím
            
            // Chuyển đổi chuỗi vừa nhập thành mảng byte
            byte buff[] = st.getBytes();
            
            // Xác định địa chỉ IP của server (localhost)
            InetAddress addsv = InetAddress.getByName("localhost");
            
            // Tạo gói dữ liệu UDP để gửi đến server, cổng 1234
            DatagramPacket p = new DatagramPacket(buff, buff.length, addsv, 1234);
            cl.send(p); // Gửi gói dữ liệu đến server
            
            // Tạo gói nhận dữ liệu phản hồi từ server
            byte buff2[] = new byte[256]; // Bộ đệm nhận dữ liệu
            DatagramPacket l = new DatagramPacket(buff2, buff2.length);
            cl.receive(l); // Nhận gói dữ liệu từ server

            // Chuyển đổi dữ liệu nhận được thành chuỗi và loại bỏ khoảng trắng
            String data = new String(l.getData()).trim();
            System.out.println("Data from Server: " + data); // Hiển thị dữ liệu phản hồi từ server
            
            // Đóng socket UDP
            cl.close();
        } catch (IOException e) {
            // Bắt lỗi liên quan đến vào/ra, nhưng không xử lý trong đoạn mã này
        }
    }
}
