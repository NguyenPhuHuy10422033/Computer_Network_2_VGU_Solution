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
 * Chương trình UDP Server nhận ba số nguyên từ client, tính bội số chung nhỏ nhất và gửi kết quả về lại client.
 * Tác giả: HUY.TD
 */
public class UDP_Server_2 {

    // Phương thức tính bội số chung nhỏ nhất (BSCNN) của ba số nguyên
    static int BSCNN(int a, int b, int c) {
        int i = a;
        // Vòng lặp tìm giá trị BSCNN
        while (!(i % a == 0 && i % b == 0 && i % c == 0)) {
            i++;
        }
        return i;
    }

    // Phương thức gửi dữ liệu từ server tới client
    static void gui(String data, InetAddress add, int port, DatagramSocket s) throws IOException {
        // Chuyển đổi chuỗi dữ liệu thành mảng byte
        byte buff[] = data.getBytes();
        // Tạo gói dữ liệu và gửi qua socket
        DatagramPacket p = new DatagramPacket(buff, buff.length, add, port);
        s.send(p);
    }

    // Phương thức nhận dữ liệu từ client
    static DatagramPacket nhan(DatagramSocket s) throws IOException {
        // Tạo mảng byte để chứa dữ liệu nhận
        byte buff[] = new byte[256];
        // Tạo gói dữ liệu và nhận qua socket
        DatagramPacket p = new DatagramPacket(buff, buff.length);
        s.receive(p);
        return p;
    }

    // Phương thức main thực hiện logic chính của chương trình
    public static void main(String[] s) throws IOException {
        // Tạo socket Datagram trên server với cổng 1234
        DatagramSocket sv = new DatagramSocket(1234);

        // Nhận ba số nguyên từ client
        DatagramPacket p1 = nhan(sv);
        String a = new String(p1.getData()).trim();
        int so1 = Integer.parseInt(a);

        DatagramPacket p2 = nhan(sv);
        String b = new String(p2.getData()).trim();
        int so2 = Integer.parseInt(b);

        DatagramPacket p3 = nhan(sv);
        String c = new String(p3.getData()).trim();
        int so3 = Integer.parseInt(c);

        // Tính bội số chung nhỏ nhất (BSCNN) của ba số
        int BSC = BSCNN(so1, so2, so3);

        // Gửi kết quả về cho client
        gui(String.valueOf(BSC), p1.getAddress(), p1.getPort(), sv);

        // Đóng socket
        sv.close();
    }
}
