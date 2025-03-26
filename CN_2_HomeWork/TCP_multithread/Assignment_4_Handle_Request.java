/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TCP_multithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author HUY.TD
 */
public class Assignment_4_Handle_Request implements Runnable{
    
    private static CopyOnWriteArrayList<Socket> Clients = new CopyOnWriteArrayList<>();
    Socket s;
    Assignment_4_Handle_Request(Socket cl)
    {
        s= cl;
        Clients.add(s);
    }
    public void run()
    {
       try{
            DataInputStream in = new DataInputStream(s.getInputStream());
            
            while(true)
            {
                String data = in.readUTF();
                int position = -1;
                for(int i = 0; i< Clients.size(); i++)
                {
                   Socket tempt = Clients.get(i);
                   if(!tempt.equals(s)) 
                   {
                        DataOutputStream out = new DataOutputStream(tempt.getOutputStream());
                        out.writeUTF(data);
                       
                   }
                   else position = i;
                   
                }
                if(data.equals("bye"))
                {
                    Clients.remove(position);
                    in.close();
                    s.close();
                    break;
                }
            }
       }catch(Exception e){}
    }
    
}
