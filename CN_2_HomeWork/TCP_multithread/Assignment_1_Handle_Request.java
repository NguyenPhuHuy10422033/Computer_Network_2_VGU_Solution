/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lớp xử lý yêu cầu từ client trong chương trình TCP đa luồng.
 * Tác giả: HUY.TD
 */
public class Assignment_1_Handle_Request implements Runnable {
    Socket con; // Biến lưu kết nối socket với client

    // Hàm khởi tạo, nhận socket từ client
    public Assignment_1_Handle_Request(Socket cl) {
        con = cl;
    }

    // Phương thức run thực thi logic xử lý yêu cầu của client
    public void run() {
        try {
            // Tạo luồng đọc/ghi để giao tiếp với client
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream())); // Nhận dữ liệu từ client
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(con.getOutputStream())); // Gửi dữ liệu tới client
            
            // Đọc dữ liệu từ client
            String rdata = in.readLine(); // Đọc chuỗi từ client
            System.out.println(rdata); // In chuỗi nhận được ra màn hình server
            
            // Xử lý dữ liệu: chuyển chuỗi sang chữ hoa và gửi phản hồi về cho client
            out.write(rdata.toUpperCase() + "\r\n"); // Chuyển dữ liệu thành chữ hoa
            out.flush(); // Đẩy dữ liệu đi ngay lập tức
            
            // Đóng các luồng và socket
            in.close();
            out.close();
            con.close();
        } catch (IOException ex) {
            // Xử lý ngoại lệ nếu xảy ra lỗi trong quá trình xử lý
            Logger.getLogger(Assignment_1_Handle_Request.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
