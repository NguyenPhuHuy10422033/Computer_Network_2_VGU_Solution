/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Chương trình TCP Client gửi ba số nguyên tới server để tính bội số chung nhỏ nhất (LCM) và nhận kết quả phản hồi.
 * Tác giả: HUY.TD
 */
public class Assignment_2_Client {

    public static void main(String[] args) {
        try {
            // Kết nối tới server thông qua địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);

            // Tạo các luồng nhập/xuất để giao tiếp với server
            DataInputStream Network_in = new DataInputStream(s.getInputStream()); // Nhận dữ liệu từ server
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream()); // Gửi dữ liệu tới server

            // Sử dụng Scanner để nhập ba số nguyên từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input three integers:"); // Yêu cầu người dùng nhập ba số nguyên
            int data1 = keyboard.nextInt(); // Nhập số nguyên đầu tiên
            int data2 = keyboard.nextInt(); // Nhập số nguyên thứ hai
            int data3 = keyboard.nextInt(); // Nhập số nguyên thứ ba

            // Gửi ba số nguyên tới server
            Network_out.writeInt(data1);
            Network_out.writeInt(data2);
            Network_out.writeInt(data3);

            // Nhận kết quả từ server: bội số chung nhỏ nhất (LCM)
            int LCM = Network_in.readInt();
            System.out.println("LCM of three integers is: " + LCM); // In kết quả ra màn hình

            // Đóng kết nối
            s.close();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }
}
