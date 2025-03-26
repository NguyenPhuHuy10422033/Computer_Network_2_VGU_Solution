/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.BufferedReader; // Import lớp BufferedReader để đọc dữ liệu từ client
import java.io.BufferedWriter; // Import lớp BufferedWriter để gửi dữ liệu đến client
import java.io.InputStreamReader; // Import lớp InputStreamReader để xử lý luồng đầu vào
import java.io.OutputStreamWriter; // Import lớp OutputStreamWriter để xử lý luồng đầu ra
import java.net.ServerSocket; // Import lớp ServerSocket để lắng nghe kết nối từ client
import java.net.Socket; // Import lớp Socket để quản lý kết nối với client

/**
 *
 * @author HUY.TD
 */
public class TCP_Server_4 {
    public static void main(String[] args) {
        try {
            // Tạo một ServerSocket lắng nghe trên cổng 1234
            ServerSocket s = new ServerSocket(1234);

            // Chờ và chấp nhận kết nối từ client
            Socket con = s.accept();

            // Tạo luồng đọc dữ liệu từ client
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            // Tạo luồng gửi dữ liệu tới client
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));

            String rdata; // Biến để lưu trữ dữ liệu nhận từ client

            // Vòng lặp xử lý dữ liệu từ client
            while ((rdata = in.readLine()) != null) {
                // Hiển thị dữ liệu nhận được từ client trên console
                System.out.println(rdata);

                // Nếu nhận được ký tự ".", kết thúc vòng lặp
                if (rdata.equals(".")) break;

                // Gửi dữ liệu phản hồi lại cho client
                out.write(rdata + "\r\n");
                out.flush(); // Đảm bảo dữ liệu được gửi ngay lập tức
            }
        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn mã này
        }
    }
}
