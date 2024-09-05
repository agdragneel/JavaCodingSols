import java.io.*;
import java.net.*;
public class GBS {
    public static void main(String args[])throws IOException
    {
        
            ServerSocket skt=new ServerSocket(15001);
            Socket connectionSocket=skt.accept();
            BufferedReader inFromClient=new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient=new DataOutputStream(connectionSocket.getOutputStream());
            int number=Integer.parseInt(inFromClient.readLine());
            String msg[]=new String[number];
            for(int i=0;i<number;)
            {
                int index=Integer.parseInt(inFromClient.readLine());
                String x=inFromClient.readLine();
                if(i==index)
                {
                    msg[i]=x;
                    outToClient.writeBytes("Acknowledged"+'\n');
                    i++;
                }
                else
                {
                    outToClient.writeBytes("Incorrect"+'\n');

                }

            }
            System.out.println("All frames received. Frames are: ");
            for(int i=0;i<number;i++)
            {
                System.out.println("[ "+msg[i]+" ]");
            }
    }
    
}
