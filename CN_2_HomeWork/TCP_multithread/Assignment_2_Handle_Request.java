/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lớp xử lý yêu cầu từ client trong chương trình TCP đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_2_Handle_Request implements Runnable {
    Socket con; // Biến lưu kết nối socket với client

    // Hàm khởi tạo, nhận socket từ client
    public Assignment_2_Handle_Request(Socket cl) {
        con = cl;
    }

    // Phương thức run thực thi logic xử lý yêu cầu của client
    public void run() {
        try {
            // Tạo các luồng nhập/xuất để giao tiếp với client
            DataInputStream in = new DataInputStream(con.getInputStream()); // Nhận dữ liệu từ client
            DataOutputStream out = new DataOutputStream(con.getOutputStream()); // Gửi dữ liệu tới client
            
            // Đọc ba số nguyên từ client
            int number1 = in.readInt();
            int number2 = in.readInt();
            int number3 = in.readInt();
            
            // Tính bội số chung nhỏ nhất (LCM) của ba số nguyên
            int LCM = 1;
            while (LCM % number1 != 0 || LCM % number2 != 0 || LCM % number3 != 0) {
                LCM++;
            }
            
            // Gửi kết quả (LCM) tới client
            out.writeInt(LCM);
            out.flush(); // Đẩy dữ liệu đi ngay lập tức

            // Đóng các luồng và socket
            out.close();
            in.close();
            con.close();
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình xử lý
            Logger.getLogger(Assignment_2_Handle_Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
