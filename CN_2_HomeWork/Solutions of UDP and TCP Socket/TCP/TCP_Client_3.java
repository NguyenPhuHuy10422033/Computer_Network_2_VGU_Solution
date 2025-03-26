/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.DataInputStream; // Import lớp DataInputStream để đọc dữ liệu từ server
import java.io.DataOutputStream; // Import lớp DataOutputStream để gửi dữ liệu tới server
import java.net.Socket; // Import lớp Socket để tạo kết nối tới server
import java.util.Scanner; // Import lớp Scanner để nhận dữ liệu từ người dùng

/**
 *
 * @author HUY.TD
 */
public class TCP_Client_3 {
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
            int number1 = keyboard.nextInt(); // Đọc số nguyên thứ nhất từ bàn phím
            int number2 = keyboard.nextInt(); // Đọc số nguyên thứ hai từ bàn phím
            
            // Yêu cầu người dùng nhập phép toán cần thực hiện
            System.out.println("Please input an operation:");
            char c = keyboard.next().charAt(0); // Đọc ký tự đại diện cho phép toán
            
            // Gửi hai số nguyên và phép toán đến server
            Network_out.writeInt(number1);
            Network_out.writeInt(number2);
            Network_out.writeChar(c);
            
            // Nhận kết quả trả về từ server dưới dạng chuỗi
            String result = Network_in.readUTF();
            System.out.println("Result is: " + result); // Hiển thị kết quả tính toán
            
            // Đóng kết nối socket
            s.close();
        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn mã này
        }
    }
}
