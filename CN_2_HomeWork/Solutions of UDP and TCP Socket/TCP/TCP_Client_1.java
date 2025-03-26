/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP
import java.net.*; // Import thư viện cho các lớp hỗ trợ xử lý mạng
import java.io.*; // Import thư viện hỗ trợ xử lý nhập xuất
import java.util.*; // Import thư viện hỗ trợ các công cụ tiện ích

/**
 *
 * @author HUY.TD
 */
public class TCP_Client_1 {
    public static void main(String[] args) {
        try {
            // Tạo socket kết nối tới server tại địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);

            // Tạo bộ đệm để đọc dữ liệu từ server
            BufferedReader Network_in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Tạo bộ đệm để gửi dữ liệu đến server
            BufferedWriter Network_out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // Sử dụng Scanner để lấy dữ liệu nhập từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input a string:"); // Hiển thị yêu cầu nhập chuỗi

            // Đọc chuỗi dữ liệu từ bàn phím
            String data = keyboard.nextLine();

            // Gửi dữ liệu vừa nhập tới server
            Network_out.write(data + "\r\n");
            Network_out.flush();

            // Đọc dữ liệu phản hồi từ server
            String result = Network_in.readLine();
            System.out.println("Data from Server:" + result); // Hiển thị dữ liệu nhận từ server

            // Đóng kết nối socket
            s.close();
        } catch (Exception e) {
            // Bắt lỗi (trường hợp có lỗi xảy ra nhưng không xử lý gì trong đoạn mã này)
        }
    }
}
