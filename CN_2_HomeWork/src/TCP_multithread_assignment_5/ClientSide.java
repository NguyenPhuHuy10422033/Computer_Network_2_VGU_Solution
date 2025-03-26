package TCP_multithread_assignment_5;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class ClientSide
{
 public static void main(String[] args) throws IOException
 {
   System.out.println("This is Number Guessing Game. \nChoose any number between 1 to 10 : ");
   Scanner keyboard = new Scanner(System.in);
   int attempt = 0;
   try
   {
     attempt = keyboard.nextInt();
     if(attempt < 1 || attempt > 9)
     {
       System.out.println("Your number is too large/small, please make a guess between 1 to 10");
       attempt = keyboard.nextInt();
      }
    }
catch(NumberFormatException nfe)
{
  System.out.println("Just choose numbers! Try again");
  attempt = keyboard.nextInt();
}

try
        {
            Socket server = new Socket("localhost", 1234);
            System.out.println("Connecting...");

DataOutputStream out = new DataOutputStream(new BufferedOutputStream(server.getOutputStream()));
DataInputStream in = new DataInputStream(new BufferedInputStream(server.getInputStream()));

out.writeInt(attempt);
out.flush();
System.out.println("Our server is still running...");
            String result = in.readUTF();
boolean correct = in.readBoolean();
System.out.println(result);
while (!correct)
{
attempt = keyboard.nextInt();
out.writeInt(attempt);
out.flush();
System.out.println("Our server is still running...");
result = in.readUTF();
System.out.println(result);
correct = in.readBoolean();
}

server.close();
keyboard.close();
System.out.println("Finish. Thank you");
System.exit(0);

}
catch(IOException ioe)
{
System.err.println(ioe);
}
    }
}
