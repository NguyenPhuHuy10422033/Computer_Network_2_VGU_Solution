/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.net.*; // Import các lớp hỗ trợ thao tác mạng
import java.io.*; // Import các lớp hỗ trợ nhập/xuất dữ liệu
import java.util.*; // Import các lớp tiện ích nếu cần thiết

/**
 *
 * @author HUY.TD
 */
public class TCP_Server_2 {
    public static void main(String[] args) {
        try {
            // Tạo một ServerSocket lắng nghe trên cổng 1234
            ServerSocket ss = new ServerSocket(1234);

            // Chờ client kết nối đến và chấp nhận kết nối
            Socket con = ss.accept();

            // Tạo luồng đọc dữ liệu từ client
            DataInputStream in = new DataInputStream(con.getInputStream());

            // Tạo luồng gửi dữ liệu tới client
            DataOutputStream out = new DataOutputStream(con.getOutputStream());

            // Đọc hai số nguyên từ client
            int number1 = in.readInt(); // Số nguyên đầu tiên
            int number2 = in.readInt(); // Số nguyên thứ hai

            // Tính Ước số chung nhỏ nhất (LCM)
            int LCM = 1;
            while (LCM % number1 != 0 || LCM % number2 != 0) LCM++; // Tìm LCM bằng vòng lặp

            // Gửi kết quả LCM cho client
            out.writeInt(LCM);

            // Đóng kết nối client và server
            con.close();
            ss.close();
        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn này
        }
    }
}
