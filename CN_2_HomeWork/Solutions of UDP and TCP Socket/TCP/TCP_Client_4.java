/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.*; // Import các lớp xử lý nhập/xuất dữ liệu
import java.net.Socket; // Import lớp Socket để tạo kết nối mạng
import java.util.Scanner; // Import lớp Scanner để nhận dữ liệu đầu vào từ người dùng

/**
 *
 * @author HUY.TD
 */
public class TCP_Client_4 {
    public static void main(String[] args) {
        try {
            // Tạo socket kết nối đến server tại địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);

            // Tạo luồng đọc dữ liệu từ server
            BufferedReader Network_in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Tạo luồng ghi dữ liệu gửi tới server
            BufferedWriter Network_out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // Tạo đối tượng Scanner để nhập dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            
            // Vòng lặp vô hạn cho phép người dùng nhập nhiều chuỗi liên tiếp
            while (true) {
                System.out.println("Please input a string:"); // Yêu cầu người dùng nhập chuỗi
                String theLine = keyboard.nextLine(); // Đọc chuỗi từ bàn phím
                
                // Gửi chuỗi dữ liệu tới server
                Network_out.write(theLine + "\r\n");       
                Network_out.flush(); // Đảm bảo dữ liệu được gửi ngay lập tức
                
                // Kiểm tra nếu người dùng nhập dấu "." thì thoát khỏi vòng lặp
                if (theLine.equals(".")) break;
                
                // Nhận và hiển thị phản hồi từ server
                System.out.println(Network_in.readLine());     
            }
        } catch (Exception e) {
            // Bắt lỗi, nhưng không xử lý gì trong đoạn mã này
        }
    }
}
