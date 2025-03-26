/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Luồng nhận dữ liệu từ server trong chương trình TCP Chat Room đa client.
 * Tác giả: HUY.TD
 */
public class Assignment_4_Receiving_thread_Client extends Thread {
    Socket s; // Biến lưu trữ socket kết nối tới server

    // Hàm khởi tạo, nhận socket từ client
    Assignment_4_Receiving_thread_Client(Socket cl) {
        s = cl;
    }

    // Phương thức run thực thi logic nhận dữ liệu từ server
    public void run() {
        DataInputStream nhan; // Luồng nhận dữ liệu
        try {
            nhan = new DataInputStream(s.getInputStream()); // Lấy luồng đầu vào từ socket
            while (true) {
                // Nhận dữ liệu từ server
                String tam = nhan.readUTF(); // Đọc chuỗi dữ liệu từ server
                System.out.println(tam); // Hiển thị dữ liệu nhận được lên màn hình
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình nhận dữ liệu
        }
    }
}
