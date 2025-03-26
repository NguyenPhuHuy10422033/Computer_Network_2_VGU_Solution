/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.*; // Import các lớp xử lý nhập/xuất
import java.net.Socket; // Import lớp Socket để thực hiện kết nối mạng
import java.util.Scanner; // Import lớp Scanner để nhập dữ liệu từ bàn phím

/**
 *
 * @author HUY.TD
 */
public class TCP_Client_2 {
    public static void main(String[] args) {
        try {
            // Tạo socket kết nối đến server tại địa chỉ "localhost" và cổng 1234
            Socket s = new Socket("localhost", 1234);
            
            // Tạo luồng đọc dữ liệu từ server
            DataInputStream Network_in = new DataInputStream(s.getInputStream());
            
            // Tạo luồng ghi dữ liệu gửi đến server
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream());
            
            // Tạo đối tượng Scanner để đọc dữ liệu từ bàn phím
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input two integers:"); // Yêu cầu người dùng nhập hai số nguyên
            
            // Đọc hai số nguyên từ bàn phím
            int data1 = keyboard.nextInt();
            int data2 = keyboard.nextInt();
            
            // Gửi hai số nguyên vừa nhập tới server
            Network_out.writeInt(data1);
            Network_out.writeInt(data2);
            
            // Nhận kết quả từ server (Ước số chung nhỏ nhất - LCM)
            int LCM = Network_in.readInt();
            System.out.println("LCM of two integer is: " + LCM); // Hiển thị kết quả LCM
            
            // Đóng kết nối socket
            s.close();
        } catch (Exception e) {
            // Bắt lỗi, nhưng không xử lý gì trong đoạn này
        }
    }
}
