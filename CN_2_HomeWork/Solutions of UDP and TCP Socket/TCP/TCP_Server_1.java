/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.net.*; // Import các lớp hỗ trợ thao tác với mạng
import java.io.*; // Import các lớp hỗ trợ nhập/xuất dữ liệu
import java.util.*; // Import các lớp tiện ích nếu cần thiết

/**
 *
 * @author HUY.TD
 */
public class TCP_Server_1 {
    public static void main(String[] args) {
        String rdata; // Biến để lưu dữ liệu nhận được từ client
        try {
            // Tạo một ServerSocket lắng nghe trên cổng 1234
            ServerSocket s = new ServerSocket(1234);

            // Chờ client kết nối đến và chấp nhận kết nối
            Socket con = s.accept();

            // Tạo luồng để đọc dữ liệu từ client
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Tạo luồng để gửi dữ liệu tới client
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));

            // Đọc một dòng dữ liệu từ client
            rdata = in.readLine();
            
            // Hiển thị dữ liệu nhận được từ client trên console
            System.out.println(rdata);

            // Gửi dữ liệu phản hồi tới client (chuyển dữ liệu thành chữ in hoa)
            out.write(rdata.toUpperCase() + "\r\n");
            out.flush(); // Đảm bảo dữ liệu được gửi ngay lập tức
        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn này
        }
    }
}
