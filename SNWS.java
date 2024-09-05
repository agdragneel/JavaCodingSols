import java.io.*;
import java.net.*;
public class SNWS
{
    public static void main(String args[])throws IOException{
        ServerSocket skt=new ServerSocket(15001);
        Socket connectionSocket=skt.accept();
        BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
        int number=Integer.parseInt(inFromClient.readLine());
        String msg[]=new String[number];
        for(int i=0;i<number;i++)
        {
            msg[i]=inFromClient.readLine();
            System.out.println("Frame "+i+" with msg "+msg[i]);
            outToClient.writeBytes("Acknowledged"+'\n');
        }
        System.out.println("All frames received");
        System.out.println("Frames are: ");
        for(int i=0;i<number;i++)
        {
            System.out.println("[ "+msg[i]+" ]");
        }
    }
}
