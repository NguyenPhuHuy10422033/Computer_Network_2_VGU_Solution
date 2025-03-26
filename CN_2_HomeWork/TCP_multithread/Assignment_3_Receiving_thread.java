/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luồng nhận dữ liệu từ server trong chương trình TCP Client đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_3_Receiving_thread extends Thread {
    
    Socket s; // Biến lưu socket kết nối với server

    // Hàm khởi tạo, nhận socket từ client
    Assignment_3_Receiving_thread(Socket cl) {
        s = cl;
    }

    // Phương thức run thực thi logic nhận dữ liệu từ server
    public void run() {
        DataInputStream nhan; // Luồng nhận dữ liệu
        try {
            nhan = new DataInputStream(s.getInputStream()); // Lấy luồng đầu vào từ socket
            while (true) {
                // Nhận và hiển thị dữ liệu từ server
                String tam = nhan.readUTF(); // Đọc chuỗi dữ liệu từ server
                System.out.println(tam); // In chuỗi nhận được ra màn hình
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình nhận dữ liệu
            Logger.getLogger(Assignment_3_Receiving_thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
