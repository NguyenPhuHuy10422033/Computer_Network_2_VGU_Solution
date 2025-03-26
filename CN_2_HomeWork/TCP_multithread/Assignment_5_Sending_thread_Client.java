/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Luồng gửi dữ liệu từ client tới server trong chương trình TCP đa luồng.
 * Tác giả: dinhhuy
 */
public class Assignment_5_Sending_thread_Client extends Thread {
    Socket s; // Biến lưu socket kết nối tới server

    // Hàm khởi tạo, nhận socket từ client
    Assignment_5_Sending_thread_Client(Socket sv) {
        s = sv;
    }

    // Phương thức run thực thi logic gửi dữ liệu tới server
    public void run() {
        try {
            DataOutputStream out; // Luồng gửi dữ liệu
            Scanner bp = new Scanner(System.in); // Đối tượng Scanner để đọc dữ liệu từ bàn phím
            out = new DataOutputStream(s.getOutputStream()); // Lấy luồng đầu ra từ socket
            
            while (true) {
                // Nhập số từ bàn phím và gửi tới server
                System.out.print("Nhập 1 số: "); 
                int x = bp.nextInt(); // Đọc số nguyên từ bàn phím
                out.writeInt(x); // Gửi số nguyên tới server
            }
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu có lỗi trong quá trình gửi dữ liệu
            Logger.getLogger(Assignment_5_Sending_thread_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
