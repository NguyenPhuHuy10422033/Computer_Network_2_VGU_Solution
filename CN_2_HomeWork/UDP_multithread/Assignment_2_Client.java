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
 * Chương trình UDP Client gửi 3 số nguyên tới server để tính Bội số chung nhỏ nhất (BSCNN).
 * Tác giả: HUY.TD
 */
public class Assignment_2_Client {

    // Phương thức gửi dữ liệu tới server
    static void gui(String data, String add, int port, DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm dữ liệu
        buff = data.getBytes(); // Chuyển chuỗi dữ liệu thành mảng byte
        InetAddress add_sv = InetAddress.getByName(add); // Lấy địa chỉ IP của server
        DatagramPacket p = new DatagramPacket(buff, buff.length, add_sv, port); // Tạo gói dữ liệu để gửi
        s.send(p); // Gửi gói dữ liệu qua socket
    }

    // Phương thức nhận dữ liệu từ server
    static String nhan(DatagramSocket s) throws IOException {
        byte buff[] = new byte[256]; // Tạo bộ đệm để lưu dữ liệu nhận
        DatagramPacket p = new DatagramPacket(buff, buff.length); // Tạo gói dữ liệu để nhận
        s.receive(p); // Nhận dữ liệu qua socket
        String data = new String(p.getData()).trim(); // Chuyển dữ liệu nhận được thành chuỗi và loại bỏ khoảng trắng
        return data; // Trả về dữ liệu nhận được dưới dạng chuỗi
    }

    // Phương thức main thực hiện logic chính của chương trình
    public static void main(String[] s) throws IOException {
        DatagramSocket cl = new DatagramSocket(); // Tạo socket Datagram cho client
        Scanner bp = new Scanner(System.in); // Sử dụng Scanner để nhập dữ liệu từ bàn phím

        // Nhập số thứ nhất từ bàn phím và gửi tới server
        System.out.print("Nhập số 1:");
        int so1 = bp.nextInt();
        gui(String.valueOf(so1), "localhost", 1234, cl);

        // Nhập số thứ hai từ bàn phím và gửi tới server
        System.out.print("Nhập số 2:");
        int so2 = bp.nextInt();
        gui(String.valueOf(so2), "localhost", 1234, cl);

        // Nhập số thứ ba từ bàn phím và gửi tới server
        System.out.print("Nhập số 3:");
        int so3 = bp.nextInt();
        gui(String.valueOf(so3), "localhost", 1234, cl);

        // Nhận kết quả BSCNN từ server
        String BSCNN = nhan(cl);
        System.out.println("BSCNN của 3 số là: " + BSCNN); // In kết quả ra màn hình

        cl.close(); // Đóng socket
    }
}
