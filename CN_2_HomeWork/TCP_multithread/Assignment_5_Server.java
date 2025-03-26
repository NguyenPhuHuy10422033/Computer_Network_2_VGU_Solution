/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCP_multithread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Chương trình TCP Server với logic sử dụng các sự kiện X và Y, đồng thời kết nối với nhiều client.
 * Tác giả: dinhhuy
 */
public class Assignment_5_Server {
    public static void main(String[] args) throws IOException {
        // Tạo ServerSocket để lắng nghe kết nối từ client tại cổng 1234
        ServerSocket ss = new ServerSocket(1234);

        // Tạo số ngẫu nhiên từ 0 đến 99
        int R = (int) (Math.random() * 100);
        System.out.println("Số ngẫu nhiên: " + R);

        // Khởi tạo các đối tượng sự kiện X và Y
        Assignment_5_SukienX x = new Assignment_5_SukienX();
        Assignment_5_SukienY y = new Assignment_5_SukienY();

        // Bắt đầu chạy các luồng sự kiện X và Y
        x.start();
        y.start();

        while (true) {
            // Chấp nhận kết nối từ client
            Socket s = ss.accept();

            // Tạo luồng mới để xử lý từng client và liên kết với sự kiện X, Y
            Assignment_5_TTvoiXY t = new Assignment_5_TTvoiXY(s, R);

            // Thêm luồng client vào danh sách lắng nghe của các sự kiện X và Y
            x.add_Listener_object(t);
            y.add_Listener_object(t);

            // Khởi chạy luồng xử lý client
            t.start();
        }
    }
}
