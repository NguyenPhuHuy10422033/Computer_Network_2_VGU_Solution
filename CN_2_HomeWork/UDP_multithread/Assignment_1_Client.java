/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Chương trình UDP Client gửi dữ liệu tới server và nhận phản hồi.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Client {

    // Phương thức gửi dữ liệu tới server
    static void gui(String data, String add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển chuỗi thành mảng byte
        InetAddress add_sv = InetAddress.getByName(add); // Lấy địa chỉ IP của server
        DatagramPacket p = new DatagramPacket(buff, buff.length, add_sv, port); // Tạo gói dữ liệu để gửi
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    // Phương thức nhận dữ liệu từ server
    static String nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm để lưu dữ liệu nhận
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
        s.receive(p); // Nhận dữ liệu qua socket
        String data = new String(p.getData()).trim(); // Chuyển dữ liệu nhận được thành chuỗi
        return data; // Trả về dữ liệu dưới dạng chuỗi
    }

    // Phương thức main thực hiện logic chính của chương trình
    public static void main(String[] s) throws IOException {
        DatagramSocket cl = new DatagramSocket(); // Tạo socket Datagram cho client
        Scanner bp = new Scanner(System.in); // Sử dụng Scanner để nhập dữ liệu từ bàn phím
        System.out.print("Please enter a string:"); // Yêu cầu nhập chuỗi
        String str = bp.nextLine(); // Nhập chuỗi từ bàn phím

        // Gửi chuỗi tới server
        gui(str, "localhost", 1234, cl);

        // Nhận phản hồi từ server
        String result = nhan(cl);
        System.out.println("Result from Server:" + result); // In kết quả phản hồi từ server

        cl.close(); // Đóng socket
    }
}
