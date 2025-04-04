package TCP_multithread_assignment_5;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ServerSide
{
public static void main(String[] args) throws IOException
{
GuessingGame game = new GuessingGame();
ServerSocket socket = null;
        Socket client = null;
        String result;
boolean correct = false;
int attempt;
try
{
socket = new ServerSocket(1234);
}
catch(IOException ioe)
{
System.err.println(ioe);
return;
}
System.out.println("Our server is still running...");
client = socket.accept();
System.out.println("Yes, our Client had connected");
DataInputStream in = new DataInputStream(new BufferedInputStream(client.getInputStream()));
DataOutputStream out = new DataOutputStream(new BufferedOutputStream(client.getOutputStream()));
while(!correct)
        {
attempt = in.readInt();
result = game.guess(attempt);
correct = game.getCorrect();
out.writeUTF(result);
out.writeBoolean(correct);
out.flush();
if(correct == false)
            {
client = socket.accept();
attempt = in.readInt();
result = game.guess(attempt);
correct = game.getCorrect();
out.writeUTF(result);
out.writeBoolean(correct);
out.flush();
            }
else
            {
client.close();
socket.close();
            }

        }
    }
}