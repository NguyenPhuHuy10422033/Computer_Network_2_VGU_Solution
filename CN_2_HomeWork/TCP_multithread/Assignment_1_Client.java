/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Chương trình TCP Client thực hiện gửi chuỗi dữ liệu tới server và nhận phản hồi.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Client {

    public static void main(String[] args) {
        try {
            // Kết nối tới server thông qua địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);
            
            // Tạo các luồng đọc/ghi để giao tiếp với server
            BufferedReader Network_in = new BufferedReader(new InputStreamReader(s.getInputStream())); // Nhận dữ liệu từ server
            BufferedWriter Network_out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream())); // Gửi dữ liệu tới server
            
            // Sử dụng Scanner để nhập dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input a string:"); // Yêu cầu người dùng nhập chuỗi
            String data = keyboard.nextLine(); // Nhập chuỗi từ bàn phím
            
            // Gửi dữ liệu tới server
            Network_out.write(data + "\r\n"); // Ghi chuỗi và thêm ký tự xuống dòng
            Network_out.flush(); // Đẩy dữ liệu đi ngay lập tức
            
            // Nhận phản hồi từ server
            String result = Network_in.readLine(); // Đọc chuỗi phản hồi từ server
            System.out.println("Data from Server:" + result); // In phản hồi ra màn hình
            
            // Đóng kết nối
            s.close();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu xảy ra lỗi
        }
    }
}
