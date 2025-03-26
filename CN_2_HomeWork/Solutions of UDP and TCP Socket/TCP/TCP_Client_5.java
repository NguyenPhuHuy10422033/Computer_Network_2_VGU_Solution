/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.BufferedReader; // Import lớp BufferedReader để đọc dữ liệu từ server
import java.io.BufferedWriter; // Import lớp BufferedWriter để gửi dữ liệu đến server
import java.io.InputStreamReader; // Import lớp InputStreamReader để đọc dữ liệu từ luồng đầu vào
import java.io.OutputStreamWriter; // Import lớp OutputStreamWriter để ghi dữ liệu đến luồng đầu ra
import java.net.Socket; // Import lớp Socket để thiết lập kết nối mạng
import java.util.Scanner; // Import lớp Scanner để đọc dữ liệu từ người dùng

/**
 *
 * @author HUY.TD
 */
public class TCP_Client_5 {
    public static void main(String[] args) {
        try {
            // Tạo socket kết nối đến server tại địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);

            // Tạo luồng đọc dữ liệu từ server
            BufferedReader Network_in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Tạo luồng ghi dữ liệu gửi tới server
            BufferedWriter Network_out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // Tạo đối tượng Scanner để đọc dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);

            // Vòng lặp cho phép trao đổi tin nhắn liên tục giữa client và server
            while (true) {   
                System.out.println("Client:"); // Yêu cầu người dùng nhập tin nhắn
                String sdata = keyboard.nextLine(); // Đọc tin nhắn từ người dùng

                // Gửi tin nhắn đến server
                Network_out.write(sdata + "\r\n");
                Network_out.flush(); // Đảm bảo tin nhắn được gửi ngay lập tức

                // Kiểm tra nếu người dùng nhập "bye" thì kết thúc vòng lặp
                if (sdata.equals("bye")) break;

                System.out.println("Server:"); // Hiển thị thông báo để nhận tin nhắn từ server
                String rdata = Network_in.readLine(); // Đọc phản hồi từ server
                System.out.println(rdata); // Hiển thị phản hồi từ server

                // Kiểm tra nếu server trả về "bye" thì kết thúc vòng lặp
                if (rdata.equals("bye")) break;
            }

        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn mã này
        }
    }
}
