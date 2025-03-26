/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.DataInputStream; // Import lớp DataInputStream để đọc dữ liệu từ server
import java.io.DataOutputStream; // Import lớp DataOutputStream để gửi dữ liệu đến server
import java.io.IOException; // Import lớp IOException để xử lý các lỗi vào/ra
import java.net.Socket; // Import lớp Socket để tạo kết nối mạng
import java.util.Scanner; // Import lớp Scanner để nhập dữ liệu từ bàn phím

/**
 *
 * @author HUY.TD
 */
public class TCP_Client_6 {
    public static void main(String[] args) {
        try {
            // Tạo socket kết nối đến server tại địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);

            // Tạo luồng đọc dữ liệu từ server
            DataInputStream Network_in = new DataInputStream(s.getInputStream());

            // Tạo luồng ghi dữ liệu gửi đến server
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream());

            // Tạo đối tượng Scanner để nhập dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            
            // Yêu cầu người dùng nhập hai số nguyên
            System.out.println("Please input two integers:");
            int data1 = keyboard.nextInt(); // Đọc số nguyên đầu tiên từ bàn phím
            int data2 = keyboard.nextInt(); // Đọc số nguyên thứ hai từ bàn phím

            // Gửi hai số nguyên đến server
            Network_out.writeInt(data1);
            Network_out.writeInt(data2);

            // Vòng lặp để nhận và in các số từ server
            while (true) {
                int number = Network_in.readInt(); // Đọc số nguyên từ server
                if (number == -1) break; // Nếu nhận được -1, kết thúc vòng lặp
                System.out.print(number + ","); // In số nguyên nhận được
            }

            // Đóng kết nối socket
            s.close();
        } catch (IOException e) {
            // Bắt lỗi IOException, nhưng không xử lý gì trong đoạn mã này
        }
    }
}
