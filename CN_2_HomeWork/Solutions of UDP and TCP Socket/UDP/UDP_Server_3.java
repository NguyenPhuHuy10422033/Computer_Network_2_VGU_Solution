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
 * Chương trình UDP Server thực hiện xử lý phép tính dựa trên dữ liệu nhận được từ client
 * Tác giả: HUY.TD
 */
public class UDP_Server_3 {

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
    public static void main(String[] s) throws IOException {
        DatagramSocket sv = new DatagramSocket(1234); // Tạo socket Datagram trên server với cổng 1234
        
        // Nhận ba gói dữ liệu từ client
        DatagramPacket p1 = nhan(sv);
        String a = new String(p1.getData()).trim();
        DatagramPacket p2 = nhan(sv);
        String b = new String(p2.getData()).trim();
        DatagramPacket p3 = nhan(sv);
        String c = new String(p3.getData()).trim();

        int number1 = 0, number2 = 0;
        char op = '0';

        // Xử lý gói dữ liệu đầu tiên
        switch (a.charAt(0)) {
            case '1' -> number1 = Integer.parseInt(a.substring(2)); // Lấy số thứ nhất
            case '2' -> number2 = Integer.parseInt(a.substring(2)); // Lấy số thứ hai
            case '3' -> op = a.charAt(2); // Lấy toán tử
            default -> System.out.println("error1");
        }

        // Xử lý gói dữ liệu thứ hai
        switch (b.charAt(0)) {
            case '1' -> number1 = Integer.parseInt(b.substring(2));
            case '2' -> number2 = Integer.parseInt(b.substring(2));
            case '3' -> op = b.charAt(2);
            default -> System.out.println("error2");
        }

        // Xử lý gói dữ liệu thứ ba
        switch (c.charAt(0)) {
            case '1' -> number1 = Integer.parseInt(c.substring(2));
            case '2' -> number2 = Integer.parseInt(c.substring(2));
            case '3' -> op = c.charAt(2);
            default -> System.out.println("error3");
        }

        // Xử lý phép toán dựa trên toán tử
        String result = null;
        switch (op) {
            case '+' -> {
                int sum = number1 + number2;
                result = String.valueOf(sum); // Phép cộng
            }
            case '-' -> {
                int sub = number1 - number2;
                result = String.valueOf(sub); // Phép trừ
            }
            case '*' -> {
                int mul = number1 * number2;
                result = String.valueOf(mul); // Phép nhân
            }
            case '/' -> {
                float div = (float) number1 / number2;
                result = String.valueOf(div); // Phép chia
            }
            default -> result = "error"; // Lỗi nếu toán tử không hợp lệ
        }

        // Gửi kết quả về cho client
        gui(result, p1.getAddress(), p1.getPort(), sv);

        // Đóng socket
        sv.close();
    }
}
