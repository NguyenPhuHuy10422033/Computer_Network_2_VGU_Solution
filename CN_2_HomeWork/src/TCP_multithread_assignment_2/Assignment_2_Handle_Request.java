/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TCP_multithread_assignment_2;

/**
 *
 * @author DELL
 */
import java.io.*;
import java.net.Socket;

public class Assignment_2_Handle_Request implements Runnable {
    private final Socket con;

    public Assignment_2_Handle_Request(Socket cl) {
        con = cl;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(con.getInputStream());
            DataOutputStream out = new DataOutputStream(con.getOutputStream());

            int number1 = in.readInt();
            int number2 = in.readInt();
            int number3 = in.readInt();

            int LCM = 1;
            while (LCM % number1 != 0 || LCM % number2 != 0 || LCM % number3 != 0) {
                LCM++;
            }

            out.writeInt(LCM);
            out.flush();
            con.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
