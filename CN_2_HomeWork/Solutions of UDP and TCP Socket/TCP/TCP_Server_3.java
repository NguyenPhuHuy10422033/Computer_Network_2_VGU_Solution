/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP; // Khai báo gói TCP

import java.io.DataInputStream; // Import lớp DataInputStream để đọc dữ liệu từ client
import java.io.DataOutputStream; // Import lớp DataOutputStream để gửi dữ liệu đến client
import java.net.ServerSocket; // Import lớp ServerSocket để tạo server
import java.net.Socket; // Import lớp Socket để thực hiện kết nối mạng

/**
 *
 * @author HUY.TD
 */
public class TCP_Server_3 {
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

            // Đọc số nguyên đầu tiên từ client
            int number1 = in.readInt();

            // Đọc số nguyên thứ hai từ client
            int number2 = in.readInt();

            // Đọc ký tự đại diện cho phép toán từ client
            char c = in.readChar();

            // Biến để lưu kết quả tính toán
            String result = null;

            // Xử lý các phép toán dựa trên ký tự nhận được
            switch (c) {
                case '+': // Phép cộng
                    int sum = number1 + number2;
                    result = String.valueOf(sum);
                    break;
                case '-': // Phép trừ
                    int sub = number1 - number2;
                    result = String.valueOf(sub);
                    break;
                case '*': // Phép nhân
                    int mul = number1 * number2;
                    result = String.valueOf(mul);
                    break;
                case '/': // Phép chia
                    float div = (float) number1 / number2;
                    result = String.valueOf(div);
                    break;
                default: // Phép toán không hợp lệ
                    result = "error";
            }

            // Gửi kết quả tính toán về cho client
            out.writeUTF(result);

            // Đóng kết nối với client
            con.close();

            // Đóng server socket
            ss.close();
        } catch (Exception e) {
            // Bắt lỗi nhưng không xử lý gì trong đoạn này
        }
    }
}
