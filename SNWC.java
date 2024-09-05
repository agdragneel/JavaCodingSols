import java.io.*;
import java.net.*;
import java.util.*;
public class SNWC
{
    public static void main(String args[])throws IOException
    {
        Socket connectionSocket=new Socket("localhost",15001);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of frames");
        int number=sc.nextInt();
        outToClient.writeBytes(String.valueOf(number)+'\n');
        for(int i=0;i<number;i++)
        {
            System.out.println("Enter the frame message");
            String msg=sc.next();
            System.out.println("Frame "+i+" sent with message "+msg);
            outToClient.writeBytes(msg+'\n');
            String res=inFromServer.readLine();
            if(res=="Acknowledged");
            {
                System.out.println("Correct frame received. Enter next frame");
            }
        }
        System.out.println("All frames sent");
    }
}
