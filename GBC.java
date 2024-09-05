import java.io.*;
import java.net.*;
import java.util.*;
public class GBC {
    public static void main(String args[])throws IOException{
        Socket connectionSocket=new Socket("localhost",15001);
        BufferedReader inFromServer=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToServer=new DataOutputStream(connectionSocket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the number of frames");
        int number=sc.nextInt();
        outToServer.writeBytes(String.valueOf(number)+'\n');
        for(int i=0;i<number;)
        {
            System.out.println("Enter the frame to be sent and the msg");
            int index=sc.nextInt();
            String msg=sc.next();
            outToServer.writeBytes(String.valueOf(index)+'\n');
            outToServer.writeBytes(msg+'\n');
            String res=inFromServer.readLine();
            if(res.equals("Incorrect"))
            {
                System.out.println("Enter frame again");
            }
            else
            {
                i++;
            }
        }
        System.out.println("All frames sent");
    }
}
