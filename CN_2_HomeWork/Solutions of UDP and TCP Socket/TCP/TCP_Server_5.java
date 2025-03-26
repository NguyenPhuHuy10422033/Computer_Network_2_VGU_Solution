/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.BufferedReader; // Import lớp BufferedReader để đọc dữ liệu từ client
import java.io.BufferedWriter; // Import lớp BufferedWriter để gửi dữ liệu đến client
import java.io.InputStreamReader; // Import lớp InputStreamReader để xử lý luồng đầu vào
import java.io.OutputStreamWriter; // Import lớp OutputStreamWriter để xử lý luồng đầu ra
import java.net.ServerSocket; // Import lớp ServerSocket để tạo server lắng nghe kết nối từ client
import java.net.Socket; // Import lớp Socket để quản lý kết nối với client
import java.util.Scanner; // Import lớp Scanner để đọc dữ liệu từ người dùng (server)

/**
 *
 * @author HUY.TD
 */
public class TCP_Server_5 {
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

            // Tạo đối tượng Scanner để nhập dữ liệu từ bàn phím (phía server)
            Scanner keyboard = new Scanner(System.in);

            // Vòng lặp trao đổi tin nhắn giữa client và server
            while (true) {
                // Hiển thị tin nhắn từ client
                System.out.println("Client:");
                String rdata = in.readLine(); // Đọc tin nhắn từ client
                System.out.println(rdata); // Hiển thị tin nhắn của client lên console

                // Nếu client gửi "bye", kết thúc vòng lặp
                if (rdata.equals("bye")) break;

                // Yêu cầu server nhập tin nhắn để gửi lại client
                System.out.println("Server:");
                String sdata = keyboard.nextLine(); // Đọc tin nhắn từ server (bàn phím)
                out.write(sdata + "\r\n"); // Gửi tin nhắn tới client
                out.flush(); // Đảm bảo dữ liệu được gửi ngay lập tức

                // Nếu server gửi "bye", kết thúc vòng lặp
                if (sdata.equals("bye")) break;
            }

        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn này
        }
    }
}
