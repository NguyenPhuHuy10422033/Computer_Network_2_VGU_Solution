/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UDP_multithread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 *
 * @author HUY.TD
 */
public class Assignment_5_Sending_thread_Client extends Thread{
    
     private DatagramSocket s;
    InetAddress addsv;
    int portsv;
    Assignment_5_Sending_thread_Client(DatagramSocket ds, InetAddress server, int portserver)
    {
    s = ds;
    addsv = server;
    portsv = portserver;
    }
    
   
    public void run()
    {
    
      Scanner bp = new Scanner(System.in); 
      
        try {
            
            while(true)
              {
                  System.out.println("Please enter an integer for predicting:");
                  String tam2 = bp.nextLine();
                  byte buff[] = tam2.getBytes();
                  DatagramPacket p = new DatagramPacket(buff, buff.length, addsv, portsv);
                  s.send(p);
        
              }              
        } catch (IOException ex) {
            
        }
            
    }
    
}
