/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luồng nhận dữ liệu từ server trong chương trình TCP Client sử dụng đa luồng.
 * Tác giả: dinhhuy
 */
public class Assignment_5_Receiving_thread_Client extends Thread {
    Socket s; // Biến lưu socket kết nối tới server

    // Hàm khởi tạo, nhận socket từ client
    Assignment_5_Receiving_thread_Client(Socket sv) {
        s = sv;
    }

    // Phương thức run thực thi logic nhận dữ liệu từ server
    public void run() {
        try {
            DataInputStream in; // Luồng nhận dữ liệu từ server
            in = new DataInputStream(s.getInputStream()); // Lấy luồng đầu vào từ socket
            
            while (true) {
                // Nhận dữ liệu từ server
                String kq = in.readUTF(); // Đọc chuỗi dữ liệu từ server
                System.out.println(kq); // Hiển thị dữ liệu nhận được lên màn hình
                
                // Kiểm tra nếu dữ liệu nhận được là một trong các trạng thái kết thúc
                if (kq.equalsIgnoreCase("you lose") || kq.equalsIgnoreCase("you win") || kq.equalsIgnoreCase("het gio"))
                    break; // Thoát khỏi vòng lặp nếu nhận được trạng thái kết thúc
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình nhận dữ liệu
            Logger.getLogger(Assignment_5_Receiving_thread_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
