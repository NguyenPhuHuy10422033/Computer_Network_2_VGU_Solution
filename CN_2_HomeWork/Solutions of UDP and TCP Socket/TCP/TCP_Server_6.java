/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.DataInputStream; // Import lớp DataInputStream để đọc dữ liệu từ client
import java.io.DataOutputStream; // Import lớp DataOutputStream để gửi dữ liệu đến client
import java.net.ServerSocket; // Import lớp ServerSocket để tạo server
import java.net.Socket; // Import lớp Socket để quản lý kết nối với client

/**
 *
 * @author HUY.TD
 */
public class TCP_Server_6 {
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

            // Xác định giá trị nhỏ nhất (min) và lớn nhất (max) giữa hai số
            int min, max;
            if (number1 < number2) {
                min = number1;
                max = number2;
            } else {
                min = number2;
                max = number1;
            }

            // Gửi các số chẵn trong khoảng [min, max] về client
            for (int i = min; i <= max; i++) {
                if (i % 2 == 0) out.writeInt(i); // Nếu i là số chẵn, gửi về client
            }

            // Gửi giá trị -1 để báo hiệu kết thúc danh sách
            out.writeInt(-1);

            // Đóng kết nối với client
            con.close();

            // Đóng server socket
            ss.close();
        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn mã này
        }
    }
}
