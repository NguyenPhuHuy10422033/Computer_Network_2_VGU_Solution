/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luồng gửi dữ liệu từ client tới server trong chương trình TCP đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_3_Sending_thread extends Thread {
    private Socket s; // Biến lưu socket kết nối với server

    // Hàm khởi tạo, nhận socket từ client
    Assignment_3_Sending_thread(Socket Cl) {
        s = Cl;
    }

    // Phương thức run thực thi logic gửi dữ liệu tới server
    public void run() {
        Scanner bp = new Scanner(System.in); // Đối tượng Scanner để đọc dữ liệu từ bàn phím
        DataOutputStream gui = null; // Luồng gửi dữ liệu
        try {
            gui = new DataOutputStream(s.getOutputStream()); // Lấy luồng đầu ra từ socket
            while (true) {
                // Đọc chuỗi từ bàn phím và gửi tới server
                String tam2 = bp.nextLine(); // Nhập dữ liệu từ người dùng
                gui.writeUTF(tam2); // Gửi dữ liệu dưới dạng UTF
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu có lỗi trong quá trình gửi dữ liệu
            Logger.getLogger(Assignment_3_Sending_thread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
