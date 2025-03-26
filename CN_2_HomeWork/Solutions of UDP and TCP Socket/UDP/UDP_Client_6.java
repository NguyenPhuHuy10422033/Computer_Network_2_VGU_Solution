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
 * Chương trình UDP Client thực hiện gửi và nhận dữ liệu giữa client và server.
 * Tác giả: HUY.TD
 */
public class UDP_Client_6 {

    // Phương thức gửi dữ liệu từ client tới server
    static void gui(String data, String add, int port, DatagramSocket s) throws IOException {
        // Chuyển đổi chuỗi dữ liệu thành mảng byte
        byte buff[] = data.getBytes();
        // Lấy địa chỉ IP của server
        InetAddress add_sv = InetAddress.getByName(add);
        // Tạo gói dữ liệu để gửi
        DatagramPacket p = new DatagramPacket(buff, buff.length, add_sv, port);
        // Gửi gói dữ liệu qua socket
        s.send(p);
    }

    // Phương thức nhận dữ liệu từ server
    static String nhan(DatagramSocket s) throws IOException {
        // Tạo mảng byte để chứa dữ liệu nhận
        byte buff[] = new byte[256];
        // Tạo gói dữ liệu để nhận
        DatagramPacket p = new DatagramPacket(buff, buff.length);
        // Nhận gói dữ liệu từ server
        s.receive(p);
        // Chuyển đổi dữ liệu nhận được thành chuỗi và loại bỏ khoảng trắng
        String data = new String(p.getData()).trim();
        return data;
    }

    // Phương thức main thực hiện logic chính của chương trình
    public static void main(String[] args) {
        try {
            // Tạo socket Datagram cho client
            DatagramSocket cl = new DatagramSocket();
            
            // Nhập dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input two integers:");
            String data1 = keyboard.nextLine();
            String data2 = keyboard.nextLine();

            // Gửi dữ liệu tới server
            gui(data1, "localhost", 1234, cl); 
            gui(data2, "localhost", 1234, cl);
            
            int count = 0;

            do {
                // Nhận dữ liệu từ server
                String number = nhan(cl);
                String s[] = number.split("_");

                // Kiểm tra điều kiện dữ liệu nhận được
                if (Integer.parseInt(s[0]) == 0) {
                    System.out.println("No number");
                    break;
                } else {
                    System.out.print(Integer.parseInt(s[1]) + ",");
                    count++;
                    // Kết thúc nếu đã nhận đủ số lượng
                    if (count == Integer.parseInt(s[0])) {
                        break;
                    }
                }
            } while (true);
            
            // Đóng socket
            cl.close();
        } catch (IOException e) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }    
}
